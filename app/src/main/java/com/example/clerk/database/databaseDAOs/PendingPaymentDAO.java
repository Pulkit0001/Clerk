package com.example.clerk.database.databaseDAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.clerk.database.databaseEntities.PendingPayment;

import java.util.List;

@Dao
public interface PendingPaymentDAO {

    @Query("SELECT SUM(`Due Amount`) AS 'TotalDue' FROM PendingPayment")
    LiveData<Integer> getPendingAmountList();

    //void delete(int paymentID);

    @Insert
    void insertPendingPayment(PendingPayment newPendingPayment);

    @Query("DELETE FROM PendingPayment WHERE `Payment ID`= :paymentID")
    void delete(int paymentID);

    @Query("SELECT * FROM PendingPayment WHERE Student_ID= :id")
    PendingPayment getPendingPaymentInBackground(int id);
}
