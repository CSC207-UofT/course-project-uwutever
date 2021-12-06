package com.uwutever.RegexApp.beans;

import net.xqhs.graphs.graph.Node;


public class RegexLayout extends AbstractLayout {

    public RegexLayout(RegexGraph regexGraph, Dimension dimension) {
        super(regexGraph, new RandomLocationTransformer<>(dimension), dimension);
        initialize();
    }

    @Override
    public void setSize(Dimension size) {
        if (!initialized) {
            setInitializer(new RandomLocationTransformer<Node>(size));
        }
        super.setSize(size);
    }

    public void initialize() {
        doInit();
    }

    @Override
    public void setGraph(final RegexGraph regexGraph) {
        super.setGraph(regexGraph);
    }

    private void doInit() {
        RegexGraph graph = getGraph();
        Dimension dimension = getSize();
    }
}