package com.dbse.android.spendemon;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Table.class}, version = 1)
public abstract class SummaryDatabase extends RoomDatabase {

    private static SummaryDatabase instance;

    public abstract TableDAO tableDAO();

    public static synchronized SummaryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SummaryDatabase.class, "table_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private TableDAO tableDAO;

        private PopulateDbAsyncTask(SummaryDatabase db){
            tableDAO = db.tableDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            tableDAO.insert(new Table("Income", "Salary", 500, "30-05-2019", "Cash"));
            return null;
        }
    }
}
