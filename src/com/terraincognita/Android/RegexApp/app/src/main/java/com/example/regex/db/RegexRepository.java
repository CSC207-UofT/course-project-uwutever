package com.example.regex.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * RegexRepository: A class that handles the database operations.
 * 
 * @param RegexDao: the DAO of database for RegexApp.
 * @param LiveData<List<Regex>>: the LiveData of the list of Regex.
 * 
 * @author HanruiJerryFan, RealFakeAccount
 * @version 1.0
 * @since 1.0
 */
public class RegexRepository {

    private RegexDao mRegexDao;
    private LiveData<List<RegexObj>> mAllRegex;

    public RegexRepository(Application application) {
       RegexRoomDatabase db = RegexRoomDatabase.getDatabase(application);
       mRegexDao = db.RegexDao();
       mAllRegex = mRegexDao.getAllRegex();
    }

    public LiveData<List<RegexObj>> getAllRegex() {
       return mAllRegex;
    }

    public void insert (RegexObj regex) {
       new insertAsyncTask(mRegexDao).execute(regex);
    }

    private static class insertAsyncTask extends AsyncTask<RegexObj, Void, Void> {

       private RegexDao mAsyncTaskDao;

       insertAsyncTask(RegexDao dao) {
           mAsyncTaskDao = dao;
       }

       @Override
       protected Void doInBackground(final RegexObj... params) {
           mAsyncTaskDao.insert(params[0]);
           return null;
       }
    }
}
