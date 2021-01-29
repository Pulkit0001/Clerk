package com.example.clerk.database.databaseEntities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PaidPayment {

    @ColumnInfo(name = "Payment Id")
    @PrimaryKey(autoGenerate = true)
    int paymentId;

    @ColumnInfo(name = "Candidate Name")
    String candidateName;

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCandidateName() {
        return candidateName;
    }

    @ColumnInfo(name = "Candidate Id")
    int CandidateId;

    @ColumnInfo(name = "Amount")
    int amount;

    @ColumnInfo(name = "Paid Time")
    String paidTime;

    @ColumnInfo(name = "Paid Date")
    String paidDate;

    @ColumnInfo(name = "PaymentType")
    String paymentType;

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setCandidateId(int candidateId) {
        CandidateId = candidateId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPaidTime(String paidTime) {
        this.paidTime = paidTime;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getCandidateId() {
        return CandidateId;
    }

    public int getAmount() {
        return amount;
    }

    public String getPaidTime() {
        return paidTime;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public String getPaymentType() {
        return paymentType;
    }
}
