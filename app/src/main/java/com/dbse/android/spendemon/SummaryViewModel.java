package com.dbse.android.spendemon;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class SummaryViewModel extends AndroidViewModel {

    private SummaryRepository repository;
    private LiveData<List<Table>> table;
    private LiveData<List<Table>> datedTable;

    public SummaryViewModel(@NonNull Application application) {
        super(application);
        repository = new SummaryRepository(application);
        table = repository.getAllTables();
        //datedTable = repository.getDateData(date1, date2);

    }

    public void insert(Table table){
        repository.insert(table);
    }

    public void update(Table table){
        repository.update(table);
    }

    public void delete(Table table){
        repository.delete(table);
    }

    public void deleteAllData(){
        repository.deleteAllData();
    }

    public LiveData<List<Table>> getTable() {
        return table;
    }

    public LiveData<List<Table>> getDateData(String date1, String date2){ return repository.getDateData(date1, date2);}
    public List<Table> getTable2(){
        return table.getValue();
    }
}
