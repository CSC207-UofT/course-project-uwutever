package com.uwutever.RegexApp;


import net.xqhs.graphs.graph.Node;
import net.xqhs.graphs.graph.SimpleEdge;
import net.xqhs.graphs.graph.SimpleNode;

import com.uwutever.RegexApp.beans.RegexGraph;

import com.uwutever.RegexApp.utils.controllers.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GraphVisualizationPresenter {
    final private String RegexStr;

    public GraphVisualizationPresenter (String RegexStr) {
        this.RegexStr = RegexStr;
    }

    /**
     * Return a DFAGraphData class
     */
    private DFAGraphData getDFAGraphData(String regex){
        DFAGrapher dfaGrapher = new DFAGrapher(regex);
        return dfaGrapher.graph();
    }


    /**
     * Return an NFAGraphData class
     */
    private NFAGraphData getNFAGraphData(String regex){
        NFAGrapher nfaGrapher = new NFAGrapher(regex);
        return nfaGrapher.graph();
    }


    public RegexGraph initialize_Visualization_DFA() {

        DFAGraphData dfa = getDFAGraphData(RegexStr); // create DFA data object based on regex

        RegexGraph graph = new RegexGraph();
        Map<String, Node> NodeMap = new HashMap<>();

        for (Map.Entry<String, Map<String, String>> entry: dfa.transitionTable.entrySet()) { // add node into NodeMap
            String CurNodeName = entry.getKey();

            if(! NodeMap.containsKey(CurNodeName)) {
                Node CurNode = new SimpleNode(CurNodeName);
                NodeMap.put(CurNodeName, CurNode);
            }
        }

        for (Map.Entry<String, Map<String, String>> entry: dfa.transitionTable.entrySet()) {
            Map<String, String> NextNodeData = entry.getValue();

            for (Map.Entry<String, String> entry1: NextNodeData.entrySet()) {
                String CurNodeName = entry1.getValue();

                if(! NodeMap.containsKey(CurNodeName)) {
                    Node CurNode = new SimpleNode(CurNodeName);
                    NodeMap.put(CurNodeName, CurNode);
                }
            }
        }

        for (Map.Entry<String, Node> entry: NodeMap.entrySet()) { // add node into graph
            Node CurNode = entry.getValue();
            graph.addNode(CurNode);
        }

        for (Map.Entry<String, Map<String, String>> entry: dfa.transitionTable.entrySet()) { // add edge
            String CurNodeName = entry.getKey();

            Map<String, String> NextNodeData = entry.getValue();

            Node CurNode = NodeMap.get(CurNodeName);

            for (Map.Entry<String, String> entry1: NextNodeData.entrySet()) {
                String NextNodeName = entry1.getValue();
                Node NextNode = NodeMap.get(NextNodeName);

                String EdgeLabel = entry1.getKey();

                graph.addEdge(new SimpleEdge(CurNode, NextNode, EdgeLabel));
            }
        }

        return graph;
    }


    public RegexGraph initialize_Visualization_NFA() {

        NFAGraphData nfa = getNFAGraphData(RegexStr); // create DFA data object based on regex

        RegexGraph graph = new RegexGraph();
        Map<String, Node> NodeMap = new HashMap<>();


        for (Map.Entry<String, Map<String, Set<String>>> entry: nfa.transitionTable.entrySet()) { // add node into NodeMap
            String CurNodeName = entry.getKey();

            if(! NodeMap.containsKey(CurNodeName)) {
                Node CurNode = new SimpleNode(CurNodeName);
                NodeMap.put(CurNodeName, CurNode);
                graph.addNode(CurNode);
            }
        }

        for (Map.Entry<String, Map<String, Set<String>>> entry: nfa.transitionTable.entrySet()) {// add node into NodeMap
            Map<String, Set<String>> NextNodeData = entry.getValue();

            for (Map.Entry<String, Set<String>> entry1: NextNodeData.entrySet()) {
                Set<String> NextNodeSet = entry1.getValue();

                for (String s: NextNodeSet) {

                    if(! NodeMap.containsKey(s)) {
                        Node CurNode = new SimpleNode(s);
                        NodeMap.put(s, CurNode);
                        graph.add(CurNode);
                    }
                }

            }
        }

        for (Map.Entry<String, Map<String, Set<String>>> entry: nfa.transitionTable.entrySet()) { // add edge
            String CurNodeName = entry.getKey();

            Map<String, Set<String>> NextNodeData = entry.getValue();

            Node CurNode = NodeMap.get(CurNodeName);

            for (Map.Entry<String, Set<String>> entry1: NextNodeData.entrySet()) {
                for (String s: entry1.getValue()) {
                    Node NextNode = NodeMap.get(s);

                    String EdgeLabel = entry1.getKey();
                    graph.addEdge(new SimpleEdge(CurNode, NextNode, EdgeLabel));
                }
            }
        }

        return graph;
    }

    public RegexGraph initialize_Visualization_test() {
        RegexGraph graph = new RegexGraph();

        Node v0 = new SimpleNode("V0");
        Node v1 = new SimpleNode("V1");
        Node v2 = new SimpleNode("V2");


        graph.add(v0);
        graph.add(v1);
        graph.add(v2);

        graph.addEdge(new SimpleEdge(v0, v1, "v1"));
        graph.addEdge(new SimpleEdge(v1, v2, "v2"));
        graph.addEdge(new SimpleEdge(v2, v0, "v3"));

        return graph;
    }

}
