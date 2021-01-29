package com.example.clerk.database.databaseDAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.clerk.database.databaseEntities.ChargesOfGroup;

@Dao
public interface ChargesOfGroupDAO {

    @Insert(entity = ChargesOfGroup.class, onConflict = OnConflictStrategy.IGNORE)
    void addChargeOfGroup(ChargesOfGroup chargesOfGroup);

//    @Delete(entity = ChargesOfGroup.class)
//    void removeChargeOfGroup(ChargesOfGroup chargesOfGroup);

    @Query("SELECT `Charge Id` FROM ChargesOfGroup WHERE `Group ID` = :group_id")
    int[] getChargeIdsOfGroup(int group_id);
}
