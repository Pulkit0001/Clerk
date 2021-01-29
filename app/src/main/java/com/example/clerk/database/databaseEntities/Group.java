package com.example.clerk.database.databaseEntities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Group Table")
public class Group {

    @ColumnInfo(name="Group ID")
    @PrimaryKey(autoGenerate = true)
    private int groupId;

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    @ColumnInfo(name="Name")
    private String groupName;

    @ColumnInfo(name="Strength")
    private int strength;

    @ColumnInfo(name = "Start Time")
    private String startTime;

    @ColumnInfo(name = "End Time")
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getStrength() {
        return strength;
    }

    public void setGroup_id(int groupId) {
        this.groupId = groupId;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
