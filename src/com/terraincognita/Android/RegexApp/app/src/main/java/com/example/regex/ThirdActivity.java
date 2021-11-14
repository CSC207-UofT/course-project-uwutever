package com.example.regex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.regex.db.RegexObj;

public class ThirdActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.Regex.REPLY";
    private String RegexStr;
    private String SampleText;

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

        RegexStr = getIntent().getStringExtra(SecondActivity.EXTRA_REGEX);
        SampleText = getIntent().getStringExtra(SecondActivity.EXTRA_SAMPLE);

       final Button button = findViewById(R.id.Act3_button_save);
       button.setOnClickListener(new View.OnClickListener() {
           public void onClick(View view) {
               Intent replyIntent = new Intent();
               if (TextUtils.isEmpty(RegexStr)) {
                   setResult(RESULT_CANCELED, replyIntent);
               } else {
                   String word = RegexStr;
                   replyIntent.putExtra(EXTRA_REPLY, word);
                   setResult(RESULT_OK, replyIntent);
               }
               finish();
           }
       });
    }

//    public void CallBackMainActivity(View view){
//
////        String RegexStr, SampleText;
//
////        if(bundle != null){
////            RegexStr = (String) bundle.get("RegexStr");
////            SampleText = (String) bundle.get("SampleText");
////        }
//
//        // jump to main activity
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtras(getIntent().getExtras()); // send Regex pattern and sample text to main activity
//        startActivity(intent);
//    }
}