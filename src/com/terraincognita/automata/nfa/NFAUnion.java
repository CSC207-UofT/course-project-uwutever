package com.terraincognita.automata.nfa;

import com.terraincognita.automata.nfa.NFA;
import com.terraincognita.automata.states.NFAState;

import java.util.*;

public class NFAUnion extends NFA {

    private final int maxCount;

    public NFAUnion(String character1, String character2, boolean terminating, Integer counter) {
        // Create first state
        this.states = new HashSet<>();
        String id1 = counter.toString();
        counter += 1;
        NFAState state1 = new NFAState(id1, false);
        this.states.add(state1);

        // Specify starting state
        this.startState = state1;

        // Create second state
        String id2 = counter.toString();
        NFAState state2 = new NFAState(id2, terminating);
        this.states.add(state2);

        // Add epsilon transition from first state to second state
        Set<NFAState> transition1 = new HashSet<>();
        transition1.add(state2);
        state1.stateTransitions.put("epsilon", transition1);

        // TODO
        this.transitionTable = new HashMap<>();
        this.maxCount = counter + 1;

    }

    public int getMaxCount() {
        return maxCount;
    }
}
