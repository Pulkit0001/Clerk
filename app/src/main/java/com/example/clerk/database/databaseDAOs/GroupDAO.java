package com.example.clerk.database.databaseDAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.clerk.database.ReturnPOJOs.ChooseGroupListDataObj;
import com.example.clerk.database.ReturnPOJOs.GroupListDataObj;
import com.example.clerk.database.databaseEntities.Group;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface GroupDAO {

    @Insert
    long insertGrp(Group group);

    //@Query("SELECT candidate.`Group ID` AS groupId , COUNT(candidateId) AS strength, `Group Table`.Name AS groupName " +
    //        "FROM candidate INNER JOIN `Group Table` ON candidate.`Group ID` = `Group Table`.`Group ID`" +
    //        "GROUP BY candidate.`Group ID` HAVING candidate.`Pending Payment Id` != NULL")
    @Query("SELECT Name AS groupName, `Group Table`.`Group ID` AS groupId, Strength AS strength FROM `Group Table`")
    public LiveData<List<GroupListDataObj>> getGroupList();

    @Query("SELECT Name AS groupName, `Start Time` AS startTime, `End Time` AS endTime, `Group ID` AS groupId FROM `Group Table`")
    LiveData<List<ChooseGroupListDataObj>> getGrps();

    @Query("SELECT * FROM `Group Table` WHERE `Group ID` = :id")
    LiveData<Group> getGroup(int id);

    @Query("SELECT Name FROM `Group Table`")
    List<String> getGroupNames();

}
