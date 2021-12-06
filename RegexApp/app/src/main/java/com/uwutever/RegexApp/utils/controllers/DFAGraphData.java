package com.uwutever.RegexApp.utils.controllers;

import java.util.Map;
import java.util.Set;

public final class DFAGraphData {
    public String startState;
    public Set<String> acceptingState;
    public Map<String, Map<String, String>> transitionTable; // Map<start_node, Map<edge_label, next_node>>
}
