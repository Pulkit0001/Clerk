package com.example.clerk.database.databaseDAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.clerk.database.ReturnPOJOs.ChargeOfGroupListDataObj;
import com.example.clerk.database.ReturnPOJOs.ChargeOnCandidateListDataObj;
import com.example.clerk.database.databaseEntities.Charge;
import com.example.clerk.database.databaseEntities.ChargesOfGroup;
import com.example.clerk.database.databaseEntities.ChargesOnCandidates;

import java.util.List;

@Dao
public interface ChargesOnCandidateDAO {

    @Query("SELECT `Charge Name` AS chargeName, Interval AS interval, Amount AS amount, " +
            "ChargesOnCandidates.`Next Payment Date` AS repaymentDate, Charge.`Charge Id` AS chargeId FROM " +
            "ChargesOnCandidates INNER JOIN Charge ON ChargesOnCandidates.`Charge Id` == Charge.`Charge Id` " +
            "WHERE `Student Id` = :candidateId")
    List<ChargeOnCandidateListDataObj> getChargesOnCandidate(int candidateId);

    @Insert(entity = ChargesOnCandidates.class, onConflict = OnConflictStrategy.IGNORE)
    void addChargeOnCandidates(ChargesOnCandidates chargesOnCandidate);

    @Query("DELETE FROM ChargesOnCandidates WHERE `Student Id`= :candidateId AND `Charge Id`= :chargeId")
    void delete(int candidateId, int chargeId);

    @Query("UPDATE ChargesOnCandidates SET `Next Payment Date` = :repaymentDate WHERE " +
            "`Student Id`== :candidateId AND `Charge Id` == :chargeId")
    void updateRepaymentDate(int candidateId, int chargeId, String repaymentDate);

    @Query("SELECT `Charge Name` AS chargeName, Amount AS amount, Charge.`Charge Id` AS chargeId FROM " +
            "ChargesOnCandidates INNER JOIN Charge ON ChargesOnCandidates.`Charge Id` == Charge.`Charge Id` " +
            "WHERE `Student Id` = :candidateId")
    LiveData<List<ChargeOfGroupListDataObj>> getChargesOfCandidates(int candidateId);
}
