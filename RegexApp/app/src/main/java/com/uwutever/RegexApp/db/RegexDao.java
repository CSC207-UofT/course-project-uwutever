package com.uwutever.RegexApp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * the DAO of database for RegexApp.
 * 
 * @author HanruiJerryFan, RealFakeAccount
 * @version 1.0
 * @since 1.0
 */
@Dao
public interface RegexDao {

   @Insert(onConflict = OnConflictStrategy.IGNORE)
   void insert(RegexObj regex);

   @Query("DELETE FROM regex_table")
   void deleteAll();

   @Query("SELECT * from regex_table ORDER BY mId DESC") //descending order
   LiveData<List<RegexObj>> getAllRegex();
}
