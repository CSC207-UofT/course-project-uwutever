package com.example.regex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<RegexCard> mRegexData;
    private RegexCardAdapter mRegexAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView); // id might be change
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRegexData = new ArrayList<>();
        mRegexAdapter = new RegexCardAdapter(this, mRegexData);
        mRecyclerView.setAdapter(mRegexAdapter);
        initializeData();
    }

    private void initializeData() {
        String[] regexList = getResources().getStringArray(R.array.regex_titles); // id might be change
        String[] regexInfo = getResources().getStringArray(R.array.regex_info); // id might be change

        mRegexData.clear();

        for (int i = 0; i <regexList.length; i++){
            mRegexData.add(new RegexCard(regexList[i], regexInfo[i]));
        }

        mRegexAdapter.notifyDataSetChanged();
    }

    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}