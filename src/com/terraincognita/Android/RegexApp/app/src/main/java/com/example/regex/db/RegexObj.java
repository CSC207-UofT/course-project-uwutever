package com.example.regex.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "regex_table")
public class RegexObj {

    @PrimaryKey(autoGenerate = true)
    public int mId; // used for index in database. Will increment automatically.
    //reference: <https://stackoverflow.com/questions/44109700/how-to-make-primary-key-as-autoincrement-for-room-persistence-lib>

    @NonNull
    @ColumnInfo(name = "regex")
    private String mRegex;

    public RegexObj(@NonNull String regex){
        this.mRegex = regex;
    }
    public String getRegex() {return this.mRegex;}
}
