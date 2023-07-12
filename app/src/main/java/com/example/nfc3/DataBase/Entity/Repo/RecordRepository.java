package com.example.nfc3.DataBase.Entity.Repo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.nfc3.DataBase.Entity.DAO.RecordDAO;
import com.example.nfc3.DataBase.Entity.RecordDatabase;
import com.example.nfc3.DataBase.Entity.Entity.Record;

import java.util.List;

public class RecordRepository {
    private RecordDAO recordDao;
    private LiveData<List<Record>> allRecords;

    public RecordRepository(Application application) {
        RecordDatabase database = RecordDatabase.getInstance(application);
        recordDao = database.recordDao();
        allRecords = (LiveData<List<Record>>) recordDao.getAllRecords();
    }

    public LiveData<List<Record>> getAllRecords() {
        return allRecords;
    }

    public void insert(Record record) {
        RecordDatabase.databaseWriteExecutor.execute(() -> recordDao.insert(record));
    }

    public void update(Record record) {
        RecordDatabase.databaseWriteExecutor.execute(() -> recordDao.update(record));
    }

    public void delete(Record record) {
        RecordDatabase.databaseWriteExecutor.execute(() -> recordDao.delete(record));
    }
    }



