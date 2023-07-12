package com.example.nfc3.DataBase.Entity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.nfc3.DataBase.Entity.DAO.RecordDAO;
import com.example.nfc3.DataBase.Entity.Entity.Record;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Record.class}, version = 1)
public abstract class RecordDatabase extends RoomDatabase {
    public abstract RecordDAO recordDao();

    private static volatile RecordDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static RecordDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (RecordDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RecordDatabase.class, "record_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
