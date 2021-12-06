package com.uwutever.RegexApp.beans;

import net.xqhs.graphs.graph.Node;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.map.LazyMap;

import android.util.Log;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class AbstractLayout implements Layout {

    private final Set<Node> nodes = new HashSet<>();

    private Dimension size;

    private RegexGraph graph;

    boolean initialized;

    private Map<Node, Point2D> locations
            = LazyMap.lazyMap(new HashMap<Node, Point2D>(), new Transformer<Node, Point2D>() {
        public Point2D transform(Node arg0) {
            return new Point2D();
        }
    });

    AbstractLayout(RegexGraph graph) {
        if (graph == null) {
            throw new IllegalArgumentException("RegexGraph must be non-null");
        }
        this.graph = graph;
    }

    @SuppressWarnings("unchecked")
    protected AbstractLayout(RegexGraph graph, Transformer<Node, Point2D> initializer) {
        this.graph = graph;
        Transformer<Node, Point2D> chain
                = ChainedTransformer.chainedTransformer(new Transformer[]{initializer});
        this.locations
                = LazyMap.lazyMap(new HashMap<>(), chain);
        initialized = true;
    }

    protected AbstractLayout(RegexGraph graph, Dimension size) {
        this.graph = graph;
        this.size = size;
    }

    AbstractLayout(RegexGraph graph, Transformer<Node, Point2D> initializer, Dimension size) {
        this.graph = graph;
        this.locations = LazyMap.lazyMap(new HashMap<Node, Point2D>(), initializer);
        this.size = size;
    }

    public void setGraph(RegexGraph graph) {
        this.graph = graph;
        if (size != null && graph != null) {
            initialize();
        }
    }

    public void setSize(Dimension size) {
        if (size != null && graph != null) {
            Dimension oldSize = this.size;
            this.size = size;
            initialize();
            if (oldSize != null) {
                adjustLocations(oldSize, size);
            }
        }
    }

    private void adjustLocations(Dimension oldSize, Dimension size) {
        int xOffset = (size.getWidth() - oldSize.getWidth()) / 2;
        int yOffset = (size.getHeight() - oldSize.getHeight()) / 2;
        while (true) {
            try {
                for (Node node : getGraph().getNodes()) {
                    offsetVertex(node, xOffset, yOffset);
                }
                break;
            } catch (ConcurrentModificationException cme) {
                Log.e(AbstractLayout.class.getName(), cme.getMessage());
            }
        }
    }

    public void setInitializer(Transformer<Node, Point2D> initializer) {
        if (this.equals(initializer)) {
            throw new IllegalArgumentException("Layout cannot be initialized with itself");
        }
        this.locations = LazyMap.lazyMap(new HashMap<Node, Point2D>(), initializer);
        initialized = true;
    }

    public Dimension getSize() {
        return size;
    }

    private Point2D getCoordinates(Node node) {
        return locations.get(node);
    }

    public Point2D transform(Node node) {
        return getCoordinates(node);
    }

    public double getX(Node node) {
        assert getCoordinates(node) != null : "Cannot get X coordinate for an unmapped vertex " + node;
        return getCoordinates(node).getX();
    }
    public double getY(Node node) {
        assert getCoordinates(node) != null : "Cannot get Y coordinate for an unmapped vertex " + node;
        return getCoordinates(node).getY();
    }

    private void offsetVertex(Node node, double xOffset, double yOffset) {
        Point2D coordinate = getCoordinates(node);
        coordinate.setLocation(coordinate.getX() + xOffset, coordinate.getY() + yOffset);
        setLocation(node, coordinate);
    }

    public RegexGraph getGraph() {
        return graph;
    }

    public void setLocation(Node pickedNode, Point2D point) {
        Point2D coordinate = getCoordinates(pickedNode);
        coordinate.setLocation(point);
    }

}