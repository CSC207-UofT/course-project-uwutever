package com.uwutever.RegexApp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.xqhs.graphs.graph.Node;
import net.xqhs.graphs.graph.SimpleEdge;
import net.xqhs.graphs.graph.SimpleNode;

import giwi.org.networkgraph.GraphSurfaceView;
import giwi.org.networkgraph.beans.NetworkGraph;
import giwi.org.networkgraph.beans.Vertex;

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

        String message = "Hello World!"; // need to replace with the algorithm object

        TextView Matched_Pattern = findViewById(R.id.Text_MatchedPattern);
        Matched_Pattern.setText(message);

        Log.d(TAG,"matched_pattern");
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
        NetworkGraph graph = new NetworkGraph();

        Node v1 = new SimpleNode("18");
        Node v2 = new SimpleNode("24");
        graph.getVertex().add(new Vertex(v1, ContextCompat.getDrawable(this, R.drawable.smallpixel)));
        graph.getVertex().add(new Vertex(v2, ContextCompat.getDrawable(this, R.drawable.smallpixel)));
        graph.addEdge(new SimpleEdge(v1, v2, "12"));

        Node v3 = new SimpleNode("7");
        graph.getVertex().add(new Vertex(v3, ContextCompat.getDrawable(this, R.drawable.smallpixel)));
        graph.addEdge(new SimpleEdge(v2, v3, "23"));

        v1 = new SimpleNode("14");
        graph.getVertex().add(new Vertex(v1, ContextCompat.getDrawable(this, R.drawable.smallpixel)));
        graph.addEdge(new SimpleEdge(v3, v1, "34"));

        v1 = new SimpleNode("10");
        graph.getVertex().add(new Vertex(v1, ContextCompat.getDrawable(this, R.drawable.smallpixel)));
        graph.addEdge(new SimpleEdge(v3, v1, "35"));

        v1 = new SimpleNode("11");
        graph.getVertex().add(new Vertex(v1, ContextCompat.getDrawable(this, R.drawable.smallpixel)));
        graph.addEdge(new SimpleEdge(v1, v3, "36"));
        graph.addEdge(new SimpleEdge(v3, v1, "6"));

        GraphSurfaceView surface = (GraphSurfaceView) findViewById(R.id.visualization);
        surface.init(graph);
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