package com.terraincognita.automata;

import java.util.Map;
import java.util.Set;

public class NFAState extends FSAState {

    private Map<String, Set<NFAState>> transitions;

    public void addTransition(String character, NFAState state) {
//        if (transitions.containsKey(character)) {
//
//        }
    }
}
