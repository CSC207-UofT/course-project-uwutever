package com.uwutever.RegexApp.beans;

import net.xqhs.graphs.graph.Node;
import net.xqhs.graphs.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.List;

public class RegexGraph extends net.xqhs.graphs.graph.SimpleGraph {

    private int defaultColor = android.R.color.black;
    private int edgeColor = android.R.color.black;
    private int nodeColor = android.R.color.black;
    private int nodeBgColor = android.R.color.white;

    public int getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(final int defaultColor) {
        this.defaultColor = defaultColor;
    }

    public int getEdgeColor() {
        return edgeColor;
    }

    public void setEdgeColor(final int edgeColor) {
        this.edgeColor = edgeColor;
    }

    public int getNodeColor() {
        return nodeColor;
    }

    public void setNodeColor(final int nodeColor) {
        this.nodeColor = nodeColor;
    }

    public int getNodeBgColor() {
        return nodeBgColor;
    }

    public void setNodeBgColor(final int nodeBgColor) {
        this.nodeBgColor = nodeBgColor;
    }
}