package com.uwutever.RegexApp.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The RegexObj class is a data class that represents a Regex object.
 * need to be replaced by the implementation of the algorithm team.
 * 
 * @author HanruiJerryFan, RealFakeAccount
 * @version 1.0
 * @since 1.0
 */
@Entity(tableName = "regex_table")
public class RegexObj {

    @PrimaryKey(autoGenerate = true)
    public int mId; // used for index in database. Will increment automatically.
    //reference: <https://stackoverflow.com/questions/44109700/how-to-make-primary-key-as-autoincrement-for-room-persistence-lib>

    @NonNull
    @ColumnInfo(name = "regex")
    private String mRegex;

    public RegexObj(@NonNull String regex){
        /*
        * Constructor for RegexObj
        * @param regex: the regex string
        */

        this.mRegex = regex;
    }
    public String getRegex() {
        /*
        * Getter for mRegex
        * @return mRegex: the regex string 
        */
        return this.mRegex;
    }
}
