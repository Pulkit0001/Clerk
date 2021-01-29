package com.example.clerk.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.clerk.database.databaseDAOs.ChargeDAO;
import com.example.clerk.database.databaseDAOs.ChargesOfGroupDAO;
import com.example.clerk.database.databaseDAOs.ChargesOnCandidateDAO;
import com.example.clerk.database.databaseDAOs.GroupDAO;
import com.example.clerk.database.databaseDAOs.PaidPaymentDAO;
import com.example.clerk.database.databaseDAOs.PendingPaymentDAO;
import com.example.clerk.database.databaseDAOs.StudentDAO;
import com.example.clerk.database.databaseEntities.Candidate;
import com.example.clerk.database.databaseEntities.Charge;
import com.example.clerk.database.databaseEntities.ChargesOfGroup;
import com.example.clerk.database.databaseEntities.ChargesOnCandidates;
import com.example.clerk.database.databaseEntities.Group;
import com.example.clerk.database.databaseEntities.PaidPayment;
import com.example.clerk.database.databaseEntities.PendingPayment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Candidate.class, Group.class, PendingPayment.class, Charge.class, ChargesOfGroup.class,
        ChargesOnCandidates.class, PaidPayment.class}, version = 8)
public abstract class AccountsDatabase extends RoomDatabase {

    public abstract StudentDAO studentDAO();
    public abstract GroupDAO groupDAO();
    public abstract PendingPaymentDAO pendingPaymentDAO();
    public abstract ChargeDAO chargeDAO();
    public abstract ChargesOfGroupDAO chargesOfGroupDAO();
    public abstract ChargesOnCandidateDAO chargesOnCandidateDAO();
    public abstract PaidPaymentDAO paidPaymentDAO();

    private static volatile AccountsDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AccountsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AccountsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AccountsDatabase.class, "account_database").fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }



}
