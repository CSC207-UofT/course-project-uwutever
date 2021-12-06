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

import com.uwutever.RegexApp.utils.controllers.MatchController;

import net.xqhs.graphs.graph.Node;
import net.xqhs.graphs.graph.SimpleEdge;
import net.xqhs.graphs.graph.SimpleNode;

import giwi.org.networkgraph.GraphSurfaceView;
import giwi.org.networkgraph.beans.NetworkGraph;
import giwi.org.networkgraph.beans.Vertex;

import com.uwutever.RegexApp.utils.controllers.*;

import java.util.HashMap;
import java.util.Map;

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

        MatchController matchController = new MatchController(RegexStr,false);
        Boolean result = matchController.match(SampleText);

        if (result){
            message = "True!";
        }
        else {
            message = "False!";
        }

        TextView Matched_Pattern = findViewById(R.id.Text_MatchedPattern);
        Matched_Pattern.setText(message);

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

    /**
     * Return a DFAGraphData class
     */
    public DFAGraphData getDFAVisualization(String regex){
        DFAGrapher dfaGrapher = new DFAGrapher(regex);
        return dfaGrapher.graph();
    }

    private void initialize_Visualization() {

        DFAGraphData dfa = getDFAVisualization(RegexStr); // create DFA data object based on regex

        NetworkGraph graph = new NetworkGraph();
        Map<String, Node> NodeMap = new HashMap<String, Node>();

        Node StartNode = new SimpleNode(dfa.startState);

        for (Map.Entry<String, Map<String, String>> entry: dfa.transitionTable.entrySet()) { // add node into NodeMap
            String CurNodeName = entry.getKey();
            Node CurNode = new SimpleNode(CurNodeName);
            NodeMap.put(CurNodeName, CurNode);
        }

        for (Map.Entry<String, Node> entry: NodeMap.entrySet()) {
            Node CurNode = entry.getValue();
            graph.getVertex().add(new Vertex(CurNode, ContextCompat.getDrawable(this, R.drawable.smallpixel)));
        }

        for (Map.Entry<String, Map<String, String>> entry: dfa.transitionTable.entrySet()) { // add edge
            String CurNodeName = entry.getKey();

            Map<String, String> NextNodeData = entry.getValue();

            Node CurNode = NodeMap.get(CurNodeName);

            for (Map.Entry<String, String> entry1: NextNodeData.entrySet()) {
                Node NextNode = NodeMap.get(entry1.getValue());
                String EdgeLabel = entry1.getKey();

                graph.addEdge(new SimpleEdge(CurNode, NextNode, EdgeLabel));
            }
        }

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