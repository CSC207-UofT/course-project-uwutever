package com.uwutever.RegexApp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.uwutever.RegexApp.utils.controllers.MatchController;

/**
 * SecondActivity: Let user input a regular expression.
 *
 * 
 * 
 * @author HanruiJerryFan, RealFakeAccount
 * @version 1.0
 * @since 1.0
 */
public class SecondActivity extends AppCompatActivity {
    private EditText mMessageEditRegex;
    private EditText mMessageEditSample;

    public static final String EXTRA_MESSAGE = "com.example.regex.extra.MESSAGE";
    public final static String EXTRA_REGEX = "com.example.android.Regex.REGEX";
    public final static String EXTRA_SAMPLE = "com.example.android.Regex.SAMPLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
        * onCreate: the onCreate method is called when the activity is first created.
        */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Setup SecondScreen with card information
        Intent intent = getIntent();
        String message = intent.getStringExtra(SecondActivity.EXTRA_MESSAGE);
        TextView Regex_input = findViewById(R.id.editText_Regex);
        Regex_input.setText(message);

        mMessageEditRegex = findViewById(R.id.editText_Regex);
        mMessageEditSample = findViewById(R.id.editText_Language);
    }

    public void launchThirdActivity(View view){
        /*
        * launchThirdActivity: launch the third activity.
        * @param view: the view that is clicked.
        */

        String RegexStr = mMessageEditRegex.getText().toString(); // regex string
        String SampleText = mMessageEditSample.getText().toString(); // sample text
        if (RegexStr.length() == 0){
            Toast.makeText( // show toast warning
                    getApplicationContext(),
                    "Regex Pattern cannot be empty",
                    Toast.LENGTH_LONG).show();
        }
        else{
            if (catchException(RegexStr, SampleText)){
                Log.d(TAG,"Catch Exception!");
            }
            else {
                catchException(RegexStr, SampleText);
                Intent intent = new Intent(this, ThirdActivity.class);
                intent.putExtra(EXTRA_REGEX, RegexStr); // add to data field
                intent.putExtra(EXTRA_SAMPLE, SampleText);
                intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT); // forward the action chain
                startActivity(intent);
                finish();
            }
        }

    }

    public boolean catchException(String regexStr, String sampleText) {
        try {
            MatchController matchController = new MatchController(regexStr, false);
            matchController.match(sampleText);
        } catch (Exception e) {
            Toast.makeText( // show toast warning
                    getApplicationContext(),
                    e.getMessage(),
                    Toast.LENGTH_LONG).show();
            return true;
        }
        Log.d(TAG,"No Exception!");
        return false;
    }
}