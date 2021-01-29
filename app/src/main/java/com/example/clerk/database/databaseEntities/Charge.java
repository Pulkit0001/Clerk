package com.example.clerk.database.databaseEntities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class Charge {




    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Charge Id")
    int chargeID;

    @ColumnInfo(name = "Charge Name")
    String chargeName;

    @ColumnInfo(name = "Interval")
    int paymentInterval;

    @ColumnInfo(name = "Amount")
    int chargeAmount;

    public short getChargeType() {
        return chargeType;
    }

    @ColumnInfo(name = "Repayment Date")
    String repaymentDate;

    public void setChargeType(short chargeType) {
        this.chargeType = chargeType;
    }

    @ColumnInfo(name = "Charge Type")
    short chargeType;

    public String getChargeName() {
        return chargeName;
    }

    public int getChargeAmount() {
        return chargeAmount;
    }

    public int getChargeID() { return chargeID; }

    public String getRepaymentDate() { return repaymentDate; }

    public int getPaymentInterval() { return paymentInterval; }


    public void setRepaymentDate(String repaymentDate) { this.repaymentDate = repaymentDate; }

    public void setPaymentInterval(int paymentInterval) { this.paymentInterval = paymentInterval; }

    public void setChargeID(int chargeID) { this.chargeID = chargeID; }

    public void setChargeName(String chargeName) { this.chargeName = chargeName; }

    public void setChargeAmount(int chargeAmount) { this.chargeAmount = chargeAmount; }



}
