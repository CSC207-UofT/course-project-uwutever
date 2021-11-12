package com.terraincognita.automata;

import com.terraincognita.automata.states.NFAState;

import java.util.*;

public class NFAChar extends NFA{

    public NFAChar(String character, boolean terminating, boolean start, Integer counter) {
        this.states = new HashSet<>();
        String id1 = counter.toString();
        counter += 1;
        NFAState state1 = new NFAState(id1, false);
        this.states.add(state1);

        this.startState = state1;

        String id2 = counter.toString();
        NFAState state2 = new NFAState(id2, false);
        this.states.add(state2);
        Set<NFAState> transition1 = new HashSet<>();
        transition1.add(state2);
        state1.stateTransitions.put("epsilon", transition1);

        String id3 = counter.toString();
        NFAState state3 = new NFAState(id3, false);
        this.states.add(state3);
        Set<NFAState> transition2 = new HashSet<>();
        transition2.add(state3);
        state1.stateTransitions.put(character, transition2);

        String id4 = counter.toString();
        NFAState state4 = new NFAState(id4, terminating);
        this.states.add(state4);
        Set<NFAState> transition3 = new HashSet<>();
        transition3.add(state4);
        state1.stateTransitions.put("epsilon", transition3);

        // TODO
        this.transitionTable = new HashMap<>();
    }
}
