package com.uwutever.RegexApp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class HelpActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
         * Set the layout of the activity.
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Intent intent = getIntent();
    }
}