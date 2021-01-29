package com.example.clerk.UIfragments.Charges;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.clerk.database.AccountsRepository;
import com.example.clerk.database.ReturnPOJOs.ChargeOfGroupListDataObj;
import com.example.clerk.database.databaseEntities.Charge;

import java.util.List;

public class ChargesViewModel extends ViewModel {


    public LiveData<List<ChargeOfGroupListDataObj>> getFeeChargeList() {
        return AccountsRepository.getAllCharges();
    }

    public LiveData<List<ChargeOfGroupListDataObj>> getDiscountList() {
        return AccountsRepository.getAllCharges();
    }

    public LiveData<List<ChargeOfGroupListDataObj>> getPenaltyChargeList() {
        return AccountsRepository.getAllCharges();
    }
}
