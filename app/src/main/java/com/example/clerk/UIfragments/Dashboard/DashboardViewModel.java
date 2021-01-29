package com.example.clerk.UIfragments.Dashboard;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;

import com.example.clerk.database.AccountsRepository;
import com.example.clerk.database.databaseEntities.Candidate;
import com.example.clerk.database.databaseEntities.Group;
import com.example.clerk.database.databaseEntities.PaidPayment;
import com.example.clerk.database.databaseEntities.PendingPayment;

import java.util.Calendar;
import java.util.List;

public class DashboardViewModel extends AndroidViewModel {

    LiveData<Group> mGroups;
    int candidateId = 0;
    AccountsRepository accountRepo;

    public DashboardViewModel(@NonNull Application application) {
        super(application);
        // accountRepo = new AccountsRepository(application);
    }

    public boolean createNew(){
        //ToDo

        return true;
    }

    public void editGroup(int id){

        //To do
    }

    public void deleteGroup(List<Integer> list){
        //Todo Delete a list o groups having ids provided by the list of integers
    }

    public void mergeGroups(List<Integer> list){
        //Todo Merge all the groups mentioned in list of integer ids in to one as single group
    }

    public void splitGroup(int id) {
        // to be added in later version
    }

    public LiveData<Integer> getTotalPendingAmount() {
        return AccountsRepository.getTotalPendingAmount();
    }

    public LiveData<PendingPayment> getPendingPaymentOfCandidate(int i) {
        return AccountsRepository.getPendingPaymentOfCandidate(i);
    }

    public LiveData<Candidate> getCandidateDetails(int i) {
        return AccountsRepository.getCandidateDetails(i);
    }

    public void acceptPAyment(int amount, int id) {
        PaidPayment payment = new PaidPayment();
        payment.setAmount(amount);
        payment.setCandidateId(id);
        payment.setPaymentType("CASH");
        Calendar calendar = Calendar.getInstance();
        String date = calendar.get(Calendar.DAY_OF_MONTH)+"/"+(calendar.get(Calendar.MONTH)+1)+
                "/"+calendar.get(Calendar.YEAR);
        payment.setPaidDate(date);
        String time = (calendar.get(Calendar.HOUR)+1)+":"+calendar.get(Calendar.MINUTE);
        if(calendar.get(Calendar.AM_PM)==0){
            time = time + " AM";
        }
        else {
            time = time + "PM";
        }
        payment.setPaidTime(time);
        AccountsRepository.insertPaidPayment(payment);
    }

}
