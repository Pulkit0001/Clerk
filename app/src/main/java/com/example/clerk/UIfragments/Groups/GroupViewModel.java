package com.example.clerk.UIfragments.Groups;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.clerk.R;
import com.example.clerk.database.AccountsRepository;
import com.example.clerk.database.ReturnPOJOs.GroupListDataObj;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GroupViewModel extends ViewModel {



    public LiveData<List<GroupListDataObj>> getGroupList(){
       //LiveData<List<GroupListDataObj>> grpList =
        return AccountsRepository.getGroupList();
       //return grpList;
    }
    public View.OnClickListener addNewGroup() {
        return Navigation.createNavigateOnClickListener(R.id.action_groupsFragment_to_navigation3);
    }
}
