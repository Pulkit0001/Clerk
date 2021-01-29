package com.example.clerk.database.databaseEntities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.sql.Time;
import java.util.Date;

@Entity(foreignKeys = @ForeignKey(entity = Candidate.class,
                                parentColumns = {"candidateId"},
                                childColumns = {"Student_ID"},
                                onDelete = ForeignKey.CASCADE))
@TypeConverters({PendingPayment.DateConverter.class, PendingPayment.TimeConverter.class})
public class PendingPayment {

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setDueAmount(int dueAmount) {
        this.dueAmount = dueAmount;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public int getDueAmount() {
        return dueAmount;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Payment ID")
    int paymentID;

    public void setDescription(String description) {
        Description = description;
    }

    public String getDescription() {
        return Description;
    }

    @ColumnInfo(name = "Description")
    String Description;

    @ColumnInfo(name = "Student_ID")
    int studentID;

    @ColumnInfo(name = "Issue Date")
    Date issueDate;

    @ColumnInfo(name = "Due Date")
    Date dueDate;

    @ColumnInfo(name = "Due Amount")
    int dueAmount;


    public static class DateConverter {

        @TypeConverter
        public static Date toDate(String date) {
            return new Date(date);
        }

        @TypeConverter
        public static String fromDate(Date date) {
            return date.toString();
        }
    }
        public static class TimeConverter{

            @TypeConverter
            public Time toDate(long time){
                return new Time(time);
            }

            @TypeConverter
            public long fromDate(Time time){
                return time.getTime();
            }
    }

}
