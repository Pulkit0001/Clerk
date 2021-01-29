package com.example.clerk.database.databaseDAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.clerk.database.ReturnPOJOs.CandidateListDataObj;
import com.example.clerk.database.ReturnPOJOs.ChargeOfGroupListDataObj;
import com.example.clerk.database.ReturnPOJOs.PendingCandidateObjs;
import com.example.clerk.database.databaseEntities.Candidate;
import com.example.clerk.database.databaseEntities.PendingPayment;

import java.util.List;

@Dao
public interface StudentDAO {

    // This method is used for inserting a single student in the studentTable of Database
    // parameter here is the instance of studentTable entity containing all the provided information
    // about the new Student
    @Insert
    void insertStudentDetails(Candidate student);

    //This method is used for deleting a student entry in the student table of the database by the
    // provided ID of that student in the arguments of the function
    @Query("DELETE FROM Candidate WHERE candidateId = :id")
    int  deleteStudent(int id);

    //This method is used for deleting multiple student entries fom the student table of the database
    // by th provided array of IDs in the arguments of the function that are to be deleted
    @Query("DELETE FROM Candidate WHERE candidateId IN(:id)")
    int deleteStudents(int[] id);

    // the method is provided for updating thr information of a student
//    @Update
//    int updateStudentInfo(Candidate student);

    // Fetch the details about a single student whose ID is provided by the argument of this method
    //@Query("SELECT `Candidate Name`, Age, `Joining Date`, candidateId, `Mobile No.1`, " +
      //      "`Mobile No.2`, Email, Address, `Due Amount` AS pendingAmount, Name AS groupName FROM " +
        //    "Candidate INNER JOIN `Group Table` INNER JOIN PendingPayment  WHERE candidateId = :id ")
    //LiveData<Candidate> getStudentData(int id);

    //This method is used to get details about all the students that are having provide first name
    // and last name in the arguments
//    @Query("SELECT * FROM Candidate WHERE `First Name` = :firstName AND `Last Name` = :lastNamae ")
//    LiveData<Candidate> getStudentData(String firstName, String lastNamae);

    //This method is used to get the list of all student's name and there IDs(in the form of strings
    // not integer from the defined table. The List is to be used primarily in AutoCompleteTextView
    // to find a student.
    @Query("SELECT `Candidate Name` FROM Candidate UNION " +
            "SELECT candidateId FROM Candidate")
    LiveData<List<String>> getNameOfStudents();

    @Query("SELECT `Candidate Name` AS candidateName, candidateId AS candidateId, PendingPayment.`Due Amount` AS dueAmount FROM " +
            "Candidate LEFT OUTER JOIN PendingPayment ON candidateId == PendingPayment.Student_ID WHERE `Group ID` = :id")

    LiveData<List<CandidateListDataObj>> getCandidates(int id);


    @Query("SELECT * FROM Candidate INNER JOIN PENDINGPAYMENT INNER JOIN `Group Table` WHERE candidateId = :candidateId ")
   LiveData<PendingPayment> getPendingPayment(int candidateId);

    @Insert
    long insertCandidate(Candidate student);

    @Query("SELECT * FROM Candidate WHERE candidateId = :id")
    LiveData<Candidate> getCandidate(int id);

    @Query("SELECT Candidate.candidateId AS candidateId, Candidate.`Candidate Name` AS CandidateName," +
            " Candidate.Age AS age, Candidate.`Guardian's Name` AS guaurdidanName, PendingPayment." +
            "`Due Amount` AS amount FROM PendingPayment INNER JOIN  Candidate ON candidateId == " +
            "PendingPayment.Student_ID WHERE `Group ID`= :id")
    LiveData<List<PendingCandidateObjs>> getPendingCandidatesOfGroup(int id);

    @Query("SELECT * FROM Candidate WHERE candidateId= :id")
    Candidate getCandidateInBackgroumd(int id);

}


