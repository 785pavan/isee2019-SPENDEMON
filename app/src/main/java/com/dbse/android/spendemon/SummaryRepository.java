package com.dbse.android.spendemon;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SummaryRepository {

    public static Table tableGet = new Table();
    private TableDAO tableDAO;
    private LiveData<List<Table>> allTables;
    private LiveData<List<Table>> datedTables;

    public SummaryRepository(Application application) {
        SummaryDatabase database = SummaryDatabase.getInstance(application);
        tableDAO = database.tableDAO();
        allTables = tableDAO.getAllData();
        //datedTables = tableDAO.getDateData();
    }


    public void insert(Table table) {
        new InsertTableAsyncTask(tableDAO).execute(table);
    }

    public void update(Table table) {
        new UpdateTableAsyncTask(tableDAO).execute(table);
    }

    public void delete(Table table) {
        new DeleteTableAsyncTask(tableDAO).execute(table);
    }

    public void deleteAllData() {
        new DeleteAllTableAsyncTask(tableDAO).execute();
    }

    public LiveData<List<Table>> getAllTables() {
        return allTables;
    }

    public Table getTableById(int id) {
        new getTableAsyncTask(tableDAO).execute(id);
        return tableGet;
    }

    public LiveData<List<Table>> getDateData(String date1, String date2) {
        return tableDAO.getDateData(date1, date2);
    }

    private static class InsertTableAsyncTask extends AsyncTask<Table, Void, Void> {
        private TableDAO tableDAO;

        private InsertTableAsyncTask(TableDAO tableDAO) {
            this.tableDAO = tableDAO;
        }

        @Override
        protected Void doInBackground(Table... tables) {
            tableDAO.insert(tables[0]);
            return null;
        }
    }

    private static class getTableAsyncTask extends AsyncTask<Integer, Void, Void> {
        private TableDAO tableDAO;

        private getTableAsyncTask(TableDAO tableDAO) {
            this.tableDAO = tableDAO;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            tableGet = tableDAO.getTableById(integers[0]);
            return null;
        }
    }

    private static class DeleteTableAsyncTask extends AsyncTask<Table, Void, Void> {
        private TableDAO tableDAO;

        private DeleteTableAsyncTask(TableDAO tableDAO) {
            this.tableDAO = tableDAO;
        }

        @Override
        protected Void doInBackground(Table... tables) {
            tableDAO.delete(tables[0]);
            return null;
        }
    }

    private static class UpdateTableAsyncTask extends AsyncTask<Table, Void, Void> {
        private TableDAO tableDAO;

        private UpdateTableAsyncTask(TableDAO tableDAO) {
            this.tableDAO = tableDAO;
        }

        @Override
        protected Void doInBackground(Table... tables) {
            tableDAO.update(tables[0]);
            return null;
        }
    }

    private static class DeleteAllTableAsyncTask extends AsyncTask<Void, Void, Void> {
        private TableDAO tableDAO;

        private DeleteAllTableAsyncTask(TableDAO tableDAO) {
            this.tableDAO = tableDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            tableDAO.deleteAllData();
            return null;
        }
    }
}
