package com.dbse.android.spendemon;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TableDAO {

    @Insert
    void insert(Table table);

    @Update
    void update(Table table);

    @Delete
    void delete(Table table);

    @Query("DELETE FROM summary_table")
    void deleteAllData();

    @Query("SELECT * FROM summary_table WHERE Date BETWEEN :start_date AND :end_date")
    LiveData<List<Table>> getDateData(String start_date, String end_date);

    @Query("SELECT * FROM summary_table ORDER BY id DESC")
    LiveData<List<Table>> getAllData();
}
