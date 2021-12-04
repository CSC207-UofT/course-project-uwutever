package com.uwutever.RegexApp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.uwutever.RegexApp.db.RegexObj;
import com.uwutever.RegexApp.db.RegexRepository;

import java.util.List;

/**
 * AndroidViewModel for RegexApp.
 *
 * @author HanruiJerryFan, RealFakeAccount
 * @version 1.0
 * @since 1.0
 */
public class RegexViewModel extends AndroidViewModel {

    private RegexRepository mRepository;

    private LiveData<List<RegexObj>> mAllRegex;

    public RegexViewModel(Application application) {
        super(application);
        mRepository = new RegexRepository(application);
        mAllRegex = mRepository.getAllRegex();
    }

    public LiveData<List<RegexObj>> getAllRegex() {
        /*
        * This is a method that returns a LiveData object.
        * This LiveData object is a list of all the RegexObj objects in the database.
        */
        return mAllRegex; 
    }

    public void insert(RegexObj insert_regex) { 
        /*
        * This is a method that inserts a RegexObj object into the database.
        * @param insert_regex is the RegexObj object to be inserted. 
        */
        mRepository.insert(insert_regex); 
    }

}