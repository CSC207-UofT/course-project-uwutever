package com.example.regex.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.regex.db.RegexObj;

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
                           .build();
               }
           }
       }
       return INSTANCE;
   }
}
