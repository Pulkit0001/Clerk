package com.example.clerk.UIfragments.Groups.GroupFullView;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.clerk.R;
import com.example.clerk.database.AccountsRepository;
import com.example.clerk.database.ReturnPOJOs.ChargeOfGroupListDataObj;
import com.example.clerk.database.databaseEntities.Charge;
import com.example.clerk.database.databaseEntities.Group;

import java.util.List;

public class GroupFullView extends ViewModel {

    LiveData<Group> grp;
    int id;
    LiveData<List<ChargeOfGroupListDataObj>> chargesList;


    public View.OnClickListener seeMembers(){
        Bundle b = new Bundle();
        b.putInt("Group_id",id);
        return Navigation.createNavigateOnClickListener(R.id.action_grpFullViewFragment_to_candidatesFragment,b);

    }
    public View.OnClickListener checkPendings(){
        Bundle b = new Bundle();
        b.putInt("Group_id",id);
        return Navigation.createNavigateOnClickListener(R.id.action_grpFullViewFragment_to_checkDuesFragment,b);
    }



    public void setId(int id) {
        this.id=id;

        chargesList = AccountsRepository.getChargesOfGroup(id);
    }

    public LiveData<Group> getGrp(){
        return AccountsRepository.getGrp(id);
    }

    public View.OnClickListener addMoreCharges() {
        Bundle b = new Bundle();
        b.putInt("GROUP_ID",id);
        b.putString("MESSAGE","Extra");
        return Navigation.createNavigateOnClickListener(R.id.action_grpFullViewFragment_to_applyChargesFragment,b);
    }

    public LiveData<List<ChargeOfGroupListDataObj>> getChargesOfGroup() {
        return AccountsRepository.getChargesOfGroup(id);
    }
}
