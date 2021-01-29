package com.example.clerk.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;

import androidx.annotation.Nullable;

import com.example.clerk.UIfragments.Charges.NewChargeFragments.NewChargeViewModel;
import com.example.clerk.database.AccountsRepository;
import com.example.clerk.database.ReturnPOJOs.ChargeOnCandidateListDataObj;
import com.example.clerk.database.databaseEntities.PendingPayment;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PaymentGeneratingService extends Service {

    Handler databaseHandler;
    Looper looper;
    AccountsRepository repository;
    @Override
    public void onCreate() {
        super.onCreate();
        HandlerThread thread = new HandlerThread("");
        thread.start();
        repository = new AccountsRepository(this.getApplication());
        looper = thread.getLooper();
        databaseHandler = new Handler(looper);

    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        int i = super.onStartCommand(intent, flags, startId);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                fetchPending(intent.getExtras().getInt("CandidateId"));
            }
        });
        t.start();
        stopSelf();
        return i;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void fetchPending(int id){
        List<ChargeOnCandidateListDataObj> charges = AccountsRepository.getChargesOnCandidate(id);
//        Candidate candidate = AccountsRepository.getCandidateDetailsInBackground(id);
//        String joiningDAte = candidate.getJoiningDate();

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);

        PendingPayment pendingPayment = new PendingPayment();
        String todayDate = formatDate(day,month,year);
        String dueDate = incrementDate(todayDate,0.25);
        pendingPayment.setDueDate(PendingPayment.DateConverter.toDate(dueDate));
        pendingPayment.setIssueDate(PendingPayment.DateConverter.toDate(todayDate));
        pendingPayment.setStudentID(id);


        HashMap<String,Integer> pendingDescription = new HashMap<>();
        int pendingAmount = 0;

        for(ChargeOnCandidateListDataObj charge : charges){
            String[] date = charge.repaymentDate.split("/");
            System.out.println("Repayment Dates are :"+ charge.repaymentDate + " "+pendingAmount +"............................");
            String repaymentDate = formatDate(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
            while(compareDates(repaymentDate,todayDate)){
                pendingAmount= pendingAmount + charge.amount;
                pendingDescription.put(charge.chargeName,charge.amount);
                String newRepaymentDate = repaymentDate;
                NewChargeViewModel.Interval interval = NewChargeViewModel.Interval.values()[charge.interval];
                switch (interval){
                    case YEARLY:{
                        newRepaymentDate = incrementDate(newRepaymentDate,12);
                        break;
                    }
                    case HALF_YEARLY:{
                        newRepaymentDate = incrementDate(newRepaymentDate,6);
                        break;
                    }
                    case QUARTER_YEARLY:{
                        newRepaymentDate = incrementDate(newRepaymentDate,3);
                        break;
                    }
                    case MONTHLY:{
                        newRepaymentDate = incrementDate(newRepaymentDate,1);
                        break;
                    }
                    case HALF_MONTHLY:{
                        newRepaymentDate = incrementDate(newRepaymentDate,0.5);
                        break;
                    }
                    case WEEKLY:{
                        newRepaymentDate = incrementDate(newRepaymentDate,0.25);
                        break;
                    }
                    default:{
                        AccountsRepository.delChargeOnCandidate(id,charge.chargeId);
                    }
                }
                repaymentDate= newRepaymentDate;
            }
            AccountsRepository.updateRepaymentUpdate(id,charge.chargeId,repaymentDate);

        }
        pendingPayment.setDueAmount(pendingAmount);
        pendingPayment.setDescription(pendingDescription.toString());
        PendingPayment previous = AccountsRepository.getPendingPaymentOfCandidateInBackground(id);
        if(previous!=null)
            AccountsRepository.deletePendingPayment(previous.getPaymentID());
        PendingPayment newPendingPayment = merge(previous,pendingPayment);
        if(newPendingPayment!=null){
            AccountsRepository.insertPendingPayment(newPendingPayment);
        }


    }

    private static PendingPayment merge(PendingPayment payment1, PendingPayment payment2){
        if(payment1==null&&payment2.getDueAmount()==0){
            return null;
        }
        if(payment1==null&&payment2.getDueAmount()>0){
            return payment2;
        }
        if(payment1!=null&&payment2.getDueAmount()==0){
            return payment1;
        }
        assert payment1 != null;
        payment2.setDueAmount(payment1.getDueAmount()+payment2.getDueAmount());
        String prev = payment1.getDescription().substring(0,payment1.getDescription().length()-1);
        String present  = payment2.getDescription().substring(1);
        payment2.setDescription(prev + "," + present);
        return payment2;
    }

    private static boolean compareDates(String date1, String date2){
        int day1 = Integer.parseInt(date1.substring(0,2));
        int day2 = Integer.parseInt(date2.substring(0,2));
        int month1 = Integer.parseInt(date1.substring(3,5));
        int month2 = Integer.parseInt(date2.substring(3,5));
        int year1 = Integer.parseInt(date1.substring(6,10));
        int year2 = Integer.parseInt(date2.substring(6,10));
        if(year1<year2) return true;
        else{
            if(year1==year2){
               if (month1<month2) return true;
               else {
                   if(month1==month2) {
                       return day1 <= day2;
                   }
                   else return false;
               }
            }
            else return false;
        }


    }

    private static String formatDate(int day, int month, int year){
        String date = "";
        if(day<10)
            date = date+"0"+day+"/";
        else
            date=date+day+"/";
        if (month<10)
            date=date+"0"+month+"/";
        else
            date=date+month+"/";
        date=date+year;
        return date;
    }

    private static String incrementDate(String date, double increment){
        Calendar calendar = Calendar.getInstance();
        int day = Integer.parseInt(date.substring(0,2));
        int month = Integer.parseInt(date.substring(3,5))-1;
        int year = Integer.parseInt(date.substring(6,10));
        calendar.set(year,month,day);

        if(increment==0.5){
            calendar.add(Calendar.DAY_OF_MONTH,15);
        }
        if (increment==0.25){
            calendar.add(Calendar.DAY_OF_MONTH,7);
        }
        calendar.add(Calendar.MONTH,(int)increment);
        return formatDate(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH)+1,
                calendar.get(Calendar.YEAR));
    }
}