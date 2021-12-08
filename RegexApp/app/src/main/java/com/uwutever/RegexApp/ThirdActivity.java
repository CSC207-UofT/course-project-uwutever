package com.uwutever.RegexApp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import com.uwutever.RegexApp.utils.controllers.*;

/**
 * ThirdActivity: Show the result of the regex match.
 *
 * 
 * @author HanruiJerryFan, RealFakeAccount
 * @version 1.0
 * @since 1.0
 */

public class ThirdActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.Regex.REPLY";
    private String RegexStr;
    private String SampleText;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
        * Set the layout of the activity.
        */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent intent = getIntent();

        RegexStr = intent.getStringExtra(SecondActivity.EXTRA_REGEX);
        SampleText = intent.getStringExtra(SecondActivity.EXTRA_SAMPLE);

        SearchController searchController = new SearchController(RegexStr,false);
        List<List<Integer>> matchedIntervals = searchController.search(SampleText);
        boolean result = !(matchedIntervals.size() == 0);

        if (result){
            message = highlight_MatchedPattern(matchedIntervals);
        }
        else {
            message = "No Match Pattern Available!";
        }

        TextView Matched_Pattern = findViewById(R.id.Text_MatchedPattern);
        Matched_Pattern.setText(Html.fromHtml(message));

        initialize_Visualization();

       final Button button = findViewById(R.id.Act3_button_save);
       button.setOnClickListener(new View.OnClickListener() {
           /**
            * Save the result of the regex match.
            *
            * @param view
            */
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

    private void initialize_Visualization() {
        /*
        * Initialize the visualization of the regex match.
        */
        GraphVisualizationPresenter Graph = new GraphVisualizationPresenter(RegexStr);

        GraphSurfaceView surface = (GraphSurfaceView) findViewById(R.id.visualization);
        surface.init(Graph.initialize_Visualization_NFA());
    }

    private String highlight_MatchedPattern(List<List<Integer>> matchedintervals){
        /*
        * Highlight the matched pattern.
        */
        String temp = "";
        int end = 0;
        Log.d(TAG, ((Integer) matchedintervals.size()).toString());
        for(int i = 0; i < matchedintervals.size(); i++){
            Log.d(TAG, matchedintervals.get(i).get(0).toString()+matchedintervals.get(i).get(1).toString());

            temp = temp + SampleText.substring(end, matchedintervals.get(i).get(0)) + "<font color='#226AD0'>"+ SampleText.substring(matchedintervals.get(i).get(0), matchedintervals.get(i).get(1)) + "</font>";
            end = matchedintervals.get(i).get(1);
        }
        return temp + SampleText.substring(end, SampleText.length());
    }
}