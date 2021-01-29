package com.example.clerk.database.databaseDAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.clerk.database.databaseEntities.PaidPayment;

import java.util.List;

@Dao
public interface PaidPaymentDAO {

    @Query("SELECT * FROM PaidPayment WHERE `Candidate Id` = :candidateId")
    LiveData<List<PaidPayment>> getPaidPayments(int candidateId);

    @Query("SELECT * FROM PaidPayment ORDER BY `Paid Date` DESC")
    LiveData<List<PaidPayment>> getAllPayments();

    @Insert
    void insetPayment(PaidPayment payment);
}
