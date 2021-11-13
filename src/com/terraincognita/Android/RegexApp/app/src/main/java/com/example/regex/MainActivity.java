package com.example.regex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
//import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.regex.db.RegexObj;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RegexViewModel mRegexViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final RegexCardAdapter adapter = new RegexCardAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        mRegexViewModel = ViewModelProviders.of(this).get(RegexViewModel.class); //deprecated
        mRegexViewModel = new ViewModelProvider(this).get(RegexViewModel.class);
        mRegexViewModel.getAllRegex().observe(this, new Observer<List<RegexObj>>() {
            @Override
            public void onChanged(@Nullable final List<RegexObj> RegexObjs) {
                adapter.setRegexObjs(RegexObjs);
            }
        });
    }


    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}