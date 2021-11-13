package com.example.regex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {
    private EditText mMessageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mMessageEditText = findViewById(R.id.editText_Regex); // Need to replace with method from Regex Calculation Part
    }

    public void launchThirdActivity(View view){
        Intent intent = new Intent(this, ThirdActivity.class);
        String RegexStr = mMessageEditText.getText().toString(); // regex string
        String SampleText = ((EditText) findViewById(R.id.editText_Language)).getText().toString(); // sample text
        intent.putExtra("RegexStr", RegexStr);
        intent.putExtra("SampleText", SampleText);
        startActivity(intent);
    }
}