package com.example.clerk.database.databaseDAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.clerk.database.ReturnPOJOs.ChargeOfGroupListDataObj;
import com.example.clerk.database.databaseEntities.Charge;

import java.util.List;

@Dao
public interface ChargeDAO {


    @Query("SELECT `Charge Name` AS chargeName, ChargesOfGroup.`Charge ID` AS chargeId, Amount AS " +
            "amount FROM Charge INNER JOIN ChargesOfGroup ON ChargesOfGroup.`Charge ID` == Charge." +
            "`Charge ID` WHERE ChargesOfGroup.`Group ID` = :id")
    LiveData<List<ChargeOfGroupListDataObj>> getCharges(int id);

    //@Query("SELECT * FROM Charge")
    //List<Charge> getFeeCharges(String date);

    @Query("SELECT `Charge ID` AS chargeId, `Charge Name` AS chargeName, Amount AS amount FROM Charge")
    LiveData<List<ChargeOfGroupListDataObj>> getAllCharges();

    @Insert
    void insertCharge(Charge charge);
}
