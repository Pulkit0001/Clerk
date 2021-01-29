package com.example.clerk.database.databaseEntities;

import android.provider.ContactsContract;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity
public class StudentContactInfo {



    public StudentContactInfo(String address,String mobile1, String mobile2,String email){
        setAddress(address);
        setMobile1(mobile1);
        setMobile2(mobile2);
        setEmail(email);
    }

    @ColumnInfo(name = "Address")
     private String address;

    @ColumnInfo(name = "Email")
    private String email;

    @ColumnInfo(name = "Mobile 1")
    String mobile1;

    @ColumnInfo(name = "Mobile 2")
    String mobile2;

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile1() {
        return mobile1;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }
}
