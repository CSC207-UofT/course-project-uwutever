package com.example.regex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.regex.db.RegexObj;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent intent = getIntent();
        String message = "Hello World!"; // need to replace with the algorithm object

        TextView Matched_Pattern = findViewById(R.id.Text_MatchedPattern);
        Matched_Pattern.setText("Matched_Pattern:\n" + message);

        TextView Visualization = findViewById(R.id.Text_Visualization);
        Visualization.setText("Visualization:\n" + message);

        TextView Suggestion = findViewById(R.id.Text_Suggestion);
        Suggestion.setText("Suggestion:\n" + message);
    }

    public void CallBackMainActivity(View view){

//        String RegexStr, SampleText;

//        if(bundle != null){
//            RegexStr = (String) bundle.get("RegexStr");
//            SampleText = (String) bundle.get("SampleText");
//        }

        // jump to main activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(getIntent().getExtras()); // send Regex pattern and sample text to main activity
        startActivity(intent);
    }
}