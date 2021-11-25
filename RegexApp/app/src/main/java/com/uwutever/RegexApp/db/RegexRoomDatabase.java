package com.uwutever.RegexApp.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * RegexRoomDatabase is the database that stores the Regex objects.
 * 
 * 
 * @author HanruiJerryFan, RealFakeAccount
 * @version 1.0
 * @since 1.0
 */
@Database(entities = {RegexObj.class}, version = 1, exportSchema = false)
public abstract class RegexRoomDatabase extends RoomDatabase {

   public abstract RegexDao RegexDao();
   private static RegexRoomDatabase INSTANCE;

   static RegexRoomDatabase getDatabase(final Context context) {
       if (INSTANCE == null) {
           synchronized (RegexRoomDatabase.class) {
               if (INSTANCE == null) {
                   INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                           RegexRoomDatabase.class, "regex_database")
                             // Wipes and rebuilds instead of migrating
                             // if no Migration object.
                            // Migration is not part of this practical.
                           .fallbackToDestructiveMigration()
                           .addCallback(sRoomDatabaseCallback)
                           .build();
               }
           }
       }
       return INSTANCE;
   }

   private static RoomDatabase.Callback sRoomDatabaseCallback =
       new RoomDatabase.Callback(){
       @Override
       public void onOpen (@NonNull SupportSQLiteDatabase db){
           super.onOpen(db);
           new PopulateDbAsync(INSTANCE).execute();
       }
   };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final RegexDao mDao;
        String[] RegexObjs = {"[0,1]*", "sample*"}; // example

        PopulateDbAsync(RegexRoomDatabase db) {
            mDao = db.RegexDao();
        }

        @Override
        protected Void doInBackground(final Void... params) { // reset db whenever app is started.
            mDao.deleteAll();

            for (int i = 0; i <= RegexObjs.length - 1; i++) {
                RegexObj Regex = new RegexObj(RegexObjs[i]);
                mDao.insert(Regex);
            }
            return null;
        }
    }
}
