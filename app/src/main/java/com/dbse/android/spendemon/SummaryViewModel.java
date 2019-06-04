package com.dbse.android.spendemon;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SummaryViewModel extends AndroidViewModel {

    private SummaryRepository repository;
    private LiveData<List<Table>> table;

    public SummaryViewModel(@NonNull Application application) {
        super(application);
        repository = new SummaryRepository(application);
        table = repository.getAllTables();

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
    public List<Table> getTable2(){
        return table.getValue();
    }
}
