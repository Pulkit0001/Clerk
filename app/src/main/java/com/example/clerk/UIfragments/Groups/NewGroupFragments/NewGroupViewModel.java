package com.example.clerk.UIfragments.Groups.NewGroupFragments;

import android.os.Bundle;
import android.view.View;

import androidx.core.content.res.TypedArrayUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.clerk.R;
import com.example.clerk.database.AccountsRepository;
import com.example.clerk.database.ReturnPOJOs.ChargeOfGroupListDataObj;
import com.example.clerk.database.databaseEntities.Charge;
import com.example.clerk.database.databaseEntities.Group;

import java.util.List;

public class NewGroupViewModel extends ViewModel {

    public Group group = new Group();
    long id;
    public NewGroupViewModel(){

    }
    public void  insertGroup(final Group grp){
        group.setGroupName(grp.getGroupName());
        group.setStartTime(grp.getStartTime());
        group.setEndTime(grp.getEndTime());

    }
    public void applyCharges(final int[] chargeIds){
        id = AccountsRepository.insertGrp(group,chargeIds);

    }


    public LiveData<List<ChargeOfGroupListDataObj>> getCharges() {
        return AccountsRepository.getAllCharges();
    }

    public void applyExtraCharges(int id, int[] checkedChargeIds) {
        AccountsRepository.applyChargesOnGrp(id,checkedChargeIds);
    }
}
