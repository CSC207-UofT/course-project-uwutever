package com.example.regex;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.regex.db.RegexObj;
import com.example.regex.db.RegexRepository;

import java.util.List;


public class RegexViewModel extends AndroidViewModel {

    private RegexRepository mRepository;

    private LiveData<List<RegexObj>> mAllRegex;

    public RegexViewModel(Application application) {
        super(application);
        mRepository = new RegexRepository(application);
        mAllRegex = mRepository.getAllRegex();
    }

    public LiveData<List<RegexObj>> getAllRegex() { return mAllRegex; }

    public void insert(RegexObj insert_regex) { mRepository.insert(insert_regex); }

}