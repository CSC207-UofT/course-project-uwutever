package com.uwutever.RegexApp.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * RegexRepository: A class that handles the database operations.
 *
 * 
 * @author HanruiJerryFan, RealFakeAccount
 * @version 1.0
 * @since 1.0
 */
public class RegexRepository {

    private RegexDao mRegexDao;
    private LiveData<List<RegexObj>> mAllRegex;

    public RegexRepository(Application application) {
       /*
       * The constructor of the class.
       * @param application: The application context.
       */
       RegexRoomDatabase db = RegexRoomDatabase.getDatabase(application);
       mRegexDao = db.RegexDao();
       mAllRegex = mRegexDao.getAllRegex();
    }

    public LiveData<List<RegexObj>> getAllRegex() {
       /*
         * Get all the regexes in the database.
         * @return a list of regexes.
       */
       return mAllRegex;
    }

    public void insert (RegexObj regex) {
       /*
         * Insert a new regex into the database.
         * @param regex: The regex to be inserted.
         * @return void
         */
       new insertAsyncTask(mRegexDao).execute(regex);
    }

    private static class insertAsyncTask extends AsyncTask<RegexObj, Void, Void> {

       private RegexDao mAsyncTaskDao;

       insertAsyncTask(RegexDao dao) {
          /*
            * The constructor of the class.
            * @param dao: The dao to be used.
         */
           mAsyncTaskDao = dao;
       }

       @Override
       protected Void doInBackground(final RegexObj... params) {
           mAsyncTaskDao.insert(params[0]);
           return null;
       }
    }
}
