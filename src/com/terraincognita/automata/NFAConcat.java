package com.terraincognita.automata;

import com.terraincognita.automata.states.NFAState;

import java.util.*;

public class NFAConcat extends NFA{

    public NFAConcat(String character1, String character2, boolean terminating, boolean start, Integer counter) {
        this.states = new HashSet<>();
        String id1 = counter.toString();
        counter += 1;
        NFAState state1 = new NFAState(id1, false);
        this.states.add(state1);

        this.startState = state1;

        String id2 = counter.toString();
        NFAState state2 = new NFAState(id2, false);
        counter += 1;
        this.states.add(state2);

        Set<NFAState> transition1 = new HashSet<>();
        transition1.add(state2);
        state1.stateTransitions.put("epsilon", transition1);

        String id3 = counter.toString();
        NFAState state3 = new NFAState(id3, false);
        counter += 1;
        this.states.add(state3);

        Set<NFAState> transition2 = new HashSet<>();
        transition2.add(state3);
        state2.stateTransitions.put(character1, transition1);

        String id4 = counter.toString();
        NFAState state4 = new NFAState(id4, false);
        counter += 1;
        this.states.add(state4);

        Set<NFAState> transition3 = new HashSet<>();
        transition3.add(state4);
        state3.stateTransitions.put(character2, transition3);

        String id5 = counter.toString();
        NFAState state5 = new NFAState(id5, terminating);
        this.states.add(state5);

        Set<NFAState> transition4 = new HashSet<>();
        transition4.add(state5);
        state3.stateTransitions.put("epsilon", transition4);

        // TODO
        this.transitionTable = new HashMap<>();
    }
}
