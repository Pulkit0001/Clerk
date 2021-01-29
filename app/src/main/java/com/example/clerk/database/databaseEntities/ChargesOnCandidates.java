package com.example.clerk.database.databaseEntities;

import android.app.DatePickerDialog;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(foreignKeys = {@ForeignKey(entity = Candidate.class,parentColumns = {"candidateId"},
        childColumns = {"Student Id"}),@ForeignKey(entity = Charge.class, parentColumns = {"Charge Id"},
        childColumns = {"Charge Id"})}, primaryKeys = {"Student Id","Charge Id"})
@TypeConverters(PendingPayment.DateConverter.class)
public class ChargesOnCandidates {


    @NonNull
    public String getNextPayment() {
        return nextPayment;
    }

    public void setNextPayment(@NonNull String nextPayment) {
        this.nextPayment = nextPayment;
    }

    @ColumnInfo(name="Student Id")
    @NonNull
    private int studentId;

    @ColumnInfo(name = "Charge Id")
    @NonNull
    private int chargeId;

    @ColumnInfo(name = "Next Payment Date")
    @NonNull
    private String nextPayment;

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setChargeId(int chargeId) {
        this.chargeId = chargeId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getChargeId() {
        return chargeId;
    }
}
