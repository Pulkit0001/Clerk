package com.example.clerk.UIfragments.Candidates.NewCandidateFragments;

import android.graphics.Matrix;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.clerk.database.AccountsRepository;
import com.example.clerk.database.ReturnPOJOs.ChargeOfGroupListDataObj;
import com.example.clerk.database.ReturnPOJOs.ChooseGroupListDataObj;
import com.example.clerk.database.ReturnPOJOs.GroupListDataObj;
import com.example.clerk.database.databaseEntities.Candidate;
import com.example.clerk.database.databaseEntities.ChargesOnCandidates;
import com.example.clerk.database.databaseEntities.PendingPayment;
import com.example.clerk.database.databaseEntities.StudentContactInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NewCandidateViewModel extends ViewModel {

    Candidate student = new Candidate();
    int[] ids;

    public void addStudent(String firstName, String lastName, String dateOfBirth, String gender,String joiningDate) {

        student.setName(firstName);
        student.setGuardianName(lastName);
        student.setGender(gender);
        student.setDateOfBirth(dateOfBirth);
        student.setJoiningDate(joiningDate);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String dob = student.getDateOfBirth();
        int dobYear = Integer.parseInt(dob.substring(dob.length()-4));
        int age = year-dobYear;
        System.out.println(".. "+year+"  ....   "+dobYear+"  .....    "+age+"   .........     ");
        student.setAge(age);
    }

    public void addContactDetails(String address, String contactNo, String optionalContactNo, String emailId) {
        StudentContactInfo contactInfo = new StudentContactInfo(address,contactNo,optionalContactNo,emailId);
        student.setContactInfo(contactInfo);
    }

    public void addGroup(int selectedGrp) {
        student.setGroup_id(selectedGrp); }

    public void applyCharges(int[] ids) {
//        int[] arr = AccountsRepository.getIdsOfChargesOfGroup(student.getGroup_id());
//        int[] checkedChargeIds = new int[ids.length+arr.length];
//        for(int i=0;i<checkedChargeIds.length;i++){
//            if(i<arr.length)
//                checkedChargeIds[i] = arr[i];
//
//            else
//                checkedChargeIds[i] = ids[i-arr.length];
//        }
        this.ids = ids;

    }

    public void setPhotoOfCandidate(Matrix image) {
        AccountsRepository.insertCandidate(student,ids);
    }

    public LiveData<List<ChooseGroupListDataObj>> getGroups() {
        return AccountsRepository.getGrps();
    }

    public LiveData<List<ChargeOfGroupListDataObj>> getCharges() {
        return AccountsRepository.getAllCharges();
    }


    public void applyMoreCharges(int[] checkedChargeIds,int id) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        String date = "";
        if(day<10){
            date = date+"0"+day+"/";
        }
        else {
            date = date+day+"/";
        }
        if(month<10){
            date = date+"0"+month+"/";
        }
        else {
            date = date+month+"/";
        }
        date = date+year;
        AccountsRepository.applyMoreChargesOnCandidate(checkedChargeIds,id,date);
    }
}


