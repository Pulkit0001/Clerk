package com.example.clerk.database.databaseEntities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(foreignKeys = {@ForeignKey(entity = Group.class,parentColumns = {"Group ID"}, childColumns =
        {"Group ID"}, onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Charge.class, parentColumns = {"Charge Id"}, childColumns = {"Charge ID"},
                onDelete = ForeignKey.CASCADE)},primaryKeys = {"Group ID", "Charge ID"})
public class ChargesOfGroup {
    public long getGroupID() {
        return groupID;
    }

    public void setGroupID(long groupID) {
        this.groupID = groupID;
    }

    public void setChargeID(int chargeID) {
        this.chargeID = chargeID;
    }

    public int getChargeID() {
        return chargeID;
    }

    @ColumnInfo(name = "Group ID")
    private long groupID;

    @ColumnInfo(name = "Charge ID")
    private int chargeID;

}
