package controllers;

import java.util.Map;
import java.util.Set;

/**
 * Represents a NFA graph
 */
public final class NFAGraphData {
    public String startState;
    public Set<String> acceptingState;
    public Map<String, Map<String, Set<String>>> transitionTable;
}