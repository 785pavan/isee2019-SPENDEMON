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

    @Query("SELECT * FROM summary_table ORDER BY priority DESC")
    LiveData<List<Table>> getAllData();
}
