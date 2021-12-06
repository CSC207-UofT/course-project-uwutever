package controllers;

import java.util.Map;
import java.util.Set;

public final class DFAGraphData {
    public String startState;
    public Set<String> acceptingState;
    public Map<String, Map<String, String>> transitionTable;
}
