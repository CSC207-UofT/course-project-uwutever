package com.example.regex;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
//import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.regex.db.RegexObj;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RegexCardAdapter.OnRegexListener {
    public static final int NEW_REGEX_ACTIVITY_REQUEST_CODE = 1;

    private RegexViewModel mRegexViewModel;
    private List<RegexObj> mRegexObjs;

    public static final String EXTRA_MESSAGE = "com.example.regex.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final RegexCardAdapter adapter = new RegexCardAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


//        mRegexViewModel = ViewModelProviders.of(this).get(RegexViewModel.class); //deprecated
        mRegexViewModel = new ViewModelProvider(this).get(RegexViewModel.class);
        mRegexViewModel.getAllRegex().observe(this, new Observer<List<RegexObj>>() {
            @Override
            public void onChanged(@Nullable final List<RegexObj> RegexObjs) {
                mRegexObjs = adapter.setRegexObjs(RegexObjs);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_REGEX_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
           RegexObj regex = new RegexObj(data.getStringExtra(ThirdActivity.EXTRA_REPLY));
           mRegexViewModel.insert(regex);
        } else {
           Toast.makeText( // show toast warning
                   getApplicationContext(),
                   R.string.empty_not_saved,
                   Toast.LENGTH_LONG).show();
        }
    }

    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, NEW_REGEX_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void onRegexClick(int position) {
        Log.d(TAG,"clicked " + position);

        Intent intent = new Intent(this, SecondActivity.class);

        String message = mRegexObjs.get(position).getRegex();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}