package com.uwutever.RegexApp.beans;

import net.xqhs.graphs.graph.Graph;
import net.xqhs.graphs.graph.Node;

import org.apache.commons.collections4.Transformer;

public interface Layout extends Transformer<Node, Point2D> {
    void initialize();

    void setInitializer(Transformer<Node, Point2D> initializer);

    void setGraph(RegexGraph graph);

    Graph getGraph();

    void setSize(Dimension dimension);

    Dimension getSize();

    void setLocation(Node node, Point2D coordinates);
}