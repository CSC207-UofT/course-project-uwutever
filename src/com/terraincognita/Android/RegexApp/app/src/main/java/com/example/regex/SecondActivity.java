package com.example.regex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {
    private EditText mMessageEditRegex;
    private EditText mMessageEditSample;

    public final static String EXTRA_REGEX = "com.example.android.Regex.REGEX";
    public final static String EXTRA_SAMPLE = "com.example.android.Regex.SAMPLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mMessageEditRegex = findViewById(R.id.editText_Regex); // Need to replace with method from Regex Calculation Part
        mMessageEditSample = findViewById(R.id.editText_Language);
    }

    public void launchThirdActivity(View view){
        Intent intent = new Intent(this, ThirdActivity.class);
        String RegexStr = mMessageEditRegex.getText().toString(); // regex string
        String SampleText = mMessageEditSample.getText().toString(); // sample text
        intent.putExtra(EXTRA_REGEX, RegexStr); // add to data field
        intent.putExtra(EXTRA_SAMPLE, SampleText);

        intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT); // forward the action chain
        startActivity(intent);
        finish();
    }
}