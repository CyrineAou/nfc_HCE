package com.example.nfc3.DataBase.Entity.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import androidx.room.Query;

import com.example.nfc3.DataBase.Entity.Entity.Record;

import java.util.List;

@Dao
public interface RecordDAO {
//        @Query("SELECT * FROM record ORDER BY id DESC")
//        LiveData<List<Record>> getAllRecords();
//        @Query("SELECT * FROM record WHERE id=:id")
//        LiveData<Record> getRecord(int id);
//
//        @Insert
//        long insert(Record record);
//
//        @Update
//        void update(Record record);
//
//        @Delete
//        void delete(Record record);
//
//        @Query("DELETE FROM record")
//        void deleteAll();

        @Insert
        void insert(Record record);

        @Update
        void update(Record record);

        @Delete
        void delete(Record record);

        @Query("SELECT * FROM record")
        List<Record> getAllRecords();


}

