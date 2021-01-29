package com.example.clerk.database;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.core.app.ShareCompat;
import androidx.lifecycle.LiveData;

import com.example.clerk.Services.PaymentGeneratingService;
import com.example.clerk.UIfragments.Charges.NewChargeFragments.NewChargeViewModel;
import com.example.clerk.database.ReturnPOJOs.CandidateListDataObj;
import com.example.clerk.database.ReturnPOJOs.ChargeOfGroupListDataObj;
import com.example.clerk.database.ReturnPOJOs.ChargeOnCandidateListDataObj;
import com.example.clerk.database.ReturnPOJOs.ChooseGroupListDataObj;
import com.example.clerk.database.ReturnPOJOs.GroupListDataObj;
import com.example.clerk.database.ReturnPOJOs.PendingCandidateObjs;
import com.example.clerk.database.databaseDAOs.ChargeDAO;
import com.example.clerk.database.databaseDAOs.ChargesOfGroupDAO;
import com.example.clerk.database.databaseDAOs.ChargesOnCandidateDAO;
import com.example.clerk.database.databaseDAOs.GroupDAO;
import com.example.clerk.database.databaseDAOs.PaidPaymentDAO;
import com.example.clerk.database.databaseDAOs.PendingPaymentDAO;
import com.example.clerk.database.databaseDAOs.StudentDAO;
import com.example.clerk.database.databaseEntities.Candidate;
import com.example.clerk.database.databaseEntities.Charge;
import com.example.clerk.database.databaseEntities.ChargesOfGroup;
import com.example.clerk.database.databaseEntities.ChargesOnCandidates;
import com.example.clerk.database.databaseEntities.Group;
import com.example.clerk.database.databaseEntities.PaidPayment;
import com.example.clerk.database.databaseEntities.PendingPayment;

import java.util.HashMap;
import java.util.List;

public class AccountsRepository  {

    private static StudentDAO studentDAO;
    private static GroupDAO groupDAO;
    private static PendingPaymentDAO pendingPaymentDAO;
    private static ChargeDAO chargeDAO;
    private static ChargesOfGroupDAO chargesOfGroupDAO;
    private static ChargesOnCandidateDAO chargesOnCandidateDAO;
    private static PaidPaymentDAO paidPaymentDAO;
    private static Context context = null;

    public AccountsRepository(Application application){
        context = application.getBaseContext();
        System.out.println("repo constructor called..........................................................................");
        AccountsDatabase database = AccountsDatabase.getDatabase(application);
        studentDAO=database.studentDAO();
        groupDAO=database.groupDAO();
        pendingPaymentDAO =database.pendingPaymentDAO();
        chargeDAO = database.chargeDAO();
        chargesOfGroupDAO = database.chargesOfGroupDAO();
        chargesOnCandidateDAO = database.chargesOnCandidateDAO();
        paidPaymentDAO = database.paidPaymentDAO();
    }

    public static long insertGrp(final Group group, final int[] chargeIds) {

        final long[] groupId = {0};

        AsyncTask<Void, Void, Long> insTask =  new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                groupId[0] = groupDAO.insertGrp(group);
                return groupId[0];
            }

            @Override
            protected void onPostExecute(final Long aLong) {
                super.onPostExecute(aLong);
                Bundle b = new Bundle();
                b.putIntArray("IdArray",chargeIds);
                b.putInt("GroupId",aLong.intValue());
                new ApplyChargesTask().execute(b);


            }
        };
        insTask.execute();
        return 0;

    }

    public static int[] getIdsOfChargesOfGroup(int group_id) {
        return chargesOfGroupDAO.getChargeIdsOfGroup(group_id);
    }

    public static LiveData<Candidate> getCandidateDetails(int id) {
        return studentDAO.getCandidate(id);
    }

    public static void delChargeOnCandidate(int candidateId, int chargeId) {
        chargesOnCandidateDAO.delete(candidateId,chargeId);
    }

    public static void updateRepaymentUpdate(int candidateId, int chargeId, String repaymentDate) {
        chargesOnCandidateDAO.updateRepaymentDate(candidateId,chargeId,repaymentDate);
    }

    public static void deletePendingPayment(int paymentID) {
        pendingPaymentDAO.delete(paymentID);
    }

    public static void insertPendingPayment(PendingPayment newPendingPayment) {
        pendingPaymentDAO.insertPendingPayment(newPendingPayment);
    }

    public static LiveData<List<PendingCandidateObjs>> getPendingCandidatesOfGroup(int id) {
        return studentDAO.getPendingCandidatesOfGroup(id);
    }

    public static Candidate getCandidateDetailsInBackground(int id) {
        return studentDAO.getCandidateInBackgroumd(id);
    }

    public static PendingPayment getPendingPaymentOfCandidateInBackground(int id) {
        return pendingPaymentDAO.getPendingPaymentInBackground(id);
    }

    public static LiveData<List<ChargeOfGroupListDataObj>> getChargesOnCandidateList(int candidateId) {
        return chargesOnCandidateDAO.getChargesOfCandidates(candidateId);
    }

    public static void applyMoreChargesOnCandidate(final int[] checkedChargeIds, final int id, final String date) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids ) {
                ChargesOnCandidates chargesOnCandidates = new ChargesOnCandidates();
                chargesOnCandidates.setStudentId(id);
                chargesOnCandidates.setNextPayment(date);
                for (int id : checkedChargeIds) {
                    chargesOnCandidates.setChargeId(id);
                    //System.out.println(chargesOfGroup.getChargeID()+"  "+chargesOfGroup.getGroupID()+"......................");
                    chargesOnCandidateDAO.addChargeOnCandidates(chargesOnCandidates);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent i = new Intent(context, PaymentGeneratingService.class);
                i.setPackage("com.example.clerk");
                i.putExtra("CandidateId",id);
                context.startService(i);
            }

        }.execute();
    }

    public static void insertPaidPayment(final PaidPayment payment) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                paidPaymentDAO.insetPayment(payment);
                return null;
            }
        }.execute();
    }


    private static class ApplyChargesTask extends AsyncTask<Bundle, Void, Void> {
        @Override
        protected Void doInBackground(Bundle... bundles ) {
            ChargesOfGroup chargesOfGroup = new ChargesOfGroup();
            chargesOfGroup.setGroupID(bundles[0].getInt("GroupId"));
            for(int id : bundles[0].getIntArray("IdArray")){
                chargesOfGroup.setChargeID(id);
                System.out.println(chargesOfGroup.getChargeID()+"  "+chargesOfGroup.getGroupID()+"......................");
                chargesOfGroupDAO.addChargeOfGroup(chargesOfGroup);
            }
            return null;
        }
    };

    public static void applyChargesOnGrp(final long group_id, final int[] chargeIds) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids ) {
                ChargesOfGroup chargesOfGroup = new ChargesOfGroup();
                chargesOfGroup.setGroupID((int)group_id);
                for(int id : chargeIds){
                    chargesOfGroup.setChargeID(id);
                    System.out.println(chargesOfGroup.getChargeID()+"  "+chargesOfGroup.getGroupID()+"......................");
                chargesOfGroupDAO.addChargeOfGroup(chargesOfGroup);
                }
                return null;
            }
        }.execute();

    }

    public static void removeChargesOfGroup(String group_id, int[] chargeIds){
        ChargesOfGroup chargesOfGroup = new ChargesOfGroup();
        chargesOfGroup.setGroupID(Integer.parseInt(group_id));
        for(int id : chargeIds){
            chargesOfGroup.setChargeID(id);
            //chargesOfGroupDAO.removeChargeOfGroup(chargesOfGroup);
        }
    }




    public static LiveData<Group> getGrp(int id){  return groupDAO.getGroup(id); }

    public static LiveData<List<ChooseGroupListDataObj>> getGrps() { return groupDAO.getGrps(); }

    public static List<String> getGroupNames(){
        return groupDAO.getGroupNames();
    }




    public static LiveData<List<ChargeOfGroupListDataObj>> getChargesOfGroup(int id) {
        //TODO:
        return chargeDAO.getCharges(id) ;

    }

    public static void insertCharge(final Charge charge) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                System.out.println("insert Charge is in work...........................................");
                chargeDAO.insertCharge(charge);
                return null;
            }
        }.execute();
    }

    //public static List<ChargeOfGroupListDataObj> getFeeCharges(String date) {
      //  return chargeDAO.getFeeCharges(date);
   // }




    public LiveData<List<CandidateListDataObj>> getCandidateOfGroup(int id) {
        return studentDAO.getCandidates(id);
    }



    public static LiveData<Integer> getTotalPendingAmount() {
       return pendingPaymentDAO.getPendingAmountList();
    }

    public static LiveData<List<GroupListDataObj>> getGroupList(){
        return groupDAO.getGroupList();
    }

    public static LiveData<List<PaidPayment>> getRecentPayments() {
        return paidPaymentDAO.getAllPayments();
    }

    public static LiveData<List<ChargeOfGroupListDataObj>> getAllCharges() {
        return  chargeDAO.getAllCharges();
    }

    public static long insertCandidate(final Candidate student, final int[] extraChargeIds) {
        final long[] candidateId = {0};

        AsyncTask<Void, Void, Long> insTask =  new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                candidateId[0] = studentDAO.insertCandidate(student);
                return candidateId[0];
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                Bundle b = new Bundle();
                b.putInt("CandidateId",aLong.intValue());
                b.putIntArray("IdArray",extraChargeIds);
                b.putString("Joining Date",student.getJoiningDate());
                b.putInt("GroupId",student.getGroup_id());
                new ApplyExtraChargesTask().execute(b);

            }
        };
        insTask.execute();
        return 0;
    }

    private static class ApplyExtraChargesTask extends AsyncTask<Bundle, Void, Integer> { ;

        @Override
        protected Integer doInBackground(Bundle... bundles ) {
            ChargesOnCandidates chargesOnCandidate = new ChargesOnCandidates();

            chargesOnCandidate.setStudentId(bundles[0].getInt("CandidateId"));
            chargesOnCandidate.setNextPayment(bundles[0].getString("Joining Date"));


            int[] arr = AccountsRepository.getIdsOfChargesOfGroup(bundles[0].getInt("GroupId"));
            int[] ids = bundles[0].getIntArray("IdArray");

            int[] checkedChargeIds = new int[ids.length+arr.length];
            int max = -1;
            for(int i=0;i<checkedChargeIds.length;i++){
                if(i<arr.length)
                    checkedChargeIds[i] = arr[i];

                else

                    checkedChargeIds[i] = ids[i-arr.length];
                if(checkedChargeIds[i]>max){
                    max=checkedChargeIds[i];
                }
            }
            int[] checkInsertion = new int[max];
            for(int i=0;i<max;i++){
                checkInsertion[i]=0;
            }
            for(int id : checkedChargeIds){
                 if(checkInsertion[id-1]==0) {
                     chargesOnCandidate.setChargeId(id);
                     System.out.println(chargesOnCandidate.getChargeId() + "  " + chargesOnCandidate.getStudentId() + "......................");
                     chargesOnCandidateDAO.addChargeOnCandidates(chargesOnCandidate);
                     checkInsertion[id-1]=1;
                 }
            }
            return bundles[0].getInt("CandidateId");

        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Intent i = new Intent(context, PaymentGeneratingService.class);
            i.setPackage("com.example.clerk");
            i.putExtra("CandidateId",integer);
            context.startService(i);
        }
    };

    public static List<ChargeOnCandidateListDataObj> getChargesOnCandidate(int candidateId) {
        return chargesOnCandidateDAO.getChargesOnCandidate(candidateId);
    }

    public static LiveData<PendingPayment> getPendingPaymentOfCandidate(int candidateId) {
        return studentDAO.getPendingPayment(candidateId);
    }

    public static LiveData<List<PaidPayment>> getPaidPayments(int candidateId){
        return paidPaymentDAO.getPaidPayments(candidateId);

    }

    private static class MyAsyncTask extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }


}
