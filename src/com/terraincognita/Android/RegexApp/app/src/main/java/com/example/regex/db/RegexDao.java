package com.example.regex.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RegexDao {

   @Insert
   void insert(RegexObj regex);

   @Query("DELETE FROM regex_table")
   void deleteAll();

   @Query("SELECT * from regex_table ORDER BY mId DESC") //descending order
   LiveData<List<RegexObj>> getAllRegex();
}
