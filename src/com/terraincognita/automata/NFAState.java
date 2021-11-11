package com.terraincognita.automata;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NFAState extends FSAState {

    private Map<String, Set<NFAState>> transitions;

    public void addTransition(String character, NFAState state) {
        if (transitions.containsKey(character)) {
            transitions.get(character).add(state);
        } else {
            Set<NFAState> stateSet = new HashSet<>();
            stateSet.add(state);
            transitions.put(character, stateSet);
        }
    }
}
