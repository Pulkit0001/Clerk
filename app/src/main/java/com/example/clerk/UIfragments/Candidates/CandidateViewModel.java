package com.example.clerk.UIfragments.Candidates;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.clerk.R;
import com.example.clerk.database.AccountsRepository;
import com.example.clerk.database.ReturnPOJOs.CandidateListDataObj;
import com.example.clerk.database.ReturnPOJOs.ChooseGroupListDataObj;
import com.example.clerk.database.ReturnPOJOs.GroupListDataObj;
import com.example.clerk.database.databaseEntities.Candidate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CandidateViewModel extends AndroidViewModel {

    AccountsRepository repository;
    public CandidateViewModel(Application application) {
        super(application);
        repository = new AccountsRepository(application);

    }

    public LiveData<List<CandidateListDataObj>> getCandidatesOfGrp(int id){
        return repository.getCandidateOfGroup(id);
    }

    public static class SpinnerAdapter extends ArrayAdapter {
        List<ChooseGroupListDataObj> grps = new ArrayList<>();

        public SpinnerAdapter(@NonNull Context context, int resource) {
            super(context, resource);
        }


        public void submitData(List<ChooseGroupListDataObj> grps){
            this.grps = grps;
            System.out.println(this.grps.size()+"....................................................");
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return super.getView(position, convertView, parent);

        }

        @Nullable
        @Override
        public Object getItem(int position) {
            return grps.get(position).groupName;
        }

        @Override
        public int getCount() {
            return grps.size();
        }

        public int getGroupId(int position) {
            return (int)grps.get(position).groupId;
        }
    }


//    public void removeCandidate(){
//
//    }
//
//    public void changePic(int id){
//
//    }
//
//    public void editCandidate(int id){
//
//    }
//
//    public void moveTo(int groupId){
//
//    }
}
