package com.example.clerk.database.databaseEntities;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Group.class,
        parentColumns = {"Group ID"},
        childColumns = {"Group ID"},
        onDelete = ForeignKey.CASCADE))
public class Candidate {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    public int candidateId;

    @ColumnInfo(name = "Candidate Name")
    private String name;

    @ColumnInfo(name = "Guardian's Name")
    private String guardianName;

    @ColumnInfo(name = "Date Of Birth")
    private String dateOfBirth;

    @ColumnInfo(name = "Age")
    private int age;

    @ColumnInfo(name = "Gender")
    private String gender;

    @Embedded
    private StudentContactInfo contactInfo;

    @ColumnInfo(name = "Joining Date")
    private String joiningDate;

    @ColumnInfo(name = "Leaving Date")
    private String leavingDate;

    @ColumnInfo(name="Group ID")
    private int group_id;

    @ColumnInfo(name = "Pending Payment Id")
    private int pendingPaymentId;

    @Ignore
    private int pendingAmount;

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public StudentContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(StudentContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(String leavingDate) {
        this.leavingDate = leavingDate;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getPendingPaymentId() {
        return pendingPaymentId;
    }

    public void setPendingPaymentId(int pendingPaymentId) {
        this.pendingPaymentId = pendingPaymentId;
    }

    public int getPendingAmount() {
        return pendingAmount;
    }

    public void setPendingAmount(int pendingAmount) {
        this.pendingAmount = pendingAmount;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Ignore
    private String groupName;





}


