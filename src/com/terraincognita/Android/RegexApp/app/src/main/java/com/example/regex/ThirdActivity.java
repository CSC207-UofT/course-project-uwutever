package com.example.regex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent intent = getIntent();
        String message = intent.getStringExtra(SecondActivity.EXTRA_MESSAGE);

        TextView Matched_Pattern = findViewById(R.id.Text_MatchedPattern);
        Matched_Pattern.setText("Matched_Pattern:\n" + message);

        TextView Visualization = findViewById(R.id.Text_Visualization);
        Visualization.setText("Visualization:\n" + message);

        TextView Suggestion = findViewById(R.id.Text_Suggestion);
        Suggestion.setText("Suggestion:\n" + message);
    }
}