package com.uwutever.RegexApp.utils.controllers;

import java.util.Map;
import java.util.Set;

public final class NFAGraphData {
    public String startState;
    public Set<String> acceptingState;
    public Map<String, Map<String, Set<String>>> transitionTable;
}