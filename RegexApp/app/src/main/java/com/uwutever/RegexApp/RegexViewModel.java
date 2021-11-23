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

    public LiveData<List<RegexObj>> getAllRegex() { return mAllRegex; }

    public void insert(RegexObj insert_regex) { mRepository.insert(insert_regex); }

}