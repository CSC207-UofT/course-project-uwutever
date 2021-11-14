package com.terraincognita.automata.nfa;

import com.terraincognita.automata.nfa.NFA;
import com.terraincognita.automata.states.NFAState;

import java.util.*;

public class NFAConcat extends NFA {

    public NFAConcat(String character1, String character2, boolean terminating, Integer counter) {
        // Create first state
        this.states = new HashSet<>();
        String id = counter.toString();
        counter += 1;
        NFAState state1 = new NFAState(id, false);
        this.states.add(state1);

        // Specify starting state
        this.startState = state1;

        // Create second state
        id = counter.toString();
        NFAState state2 = new NFAState(id, false);
        counter += 1;
        this.states.add(state2);

        // Add epsilon transition from first state to second state
        Set<NFAState> transition1 = new HashSet<>();
        transition1.add(state2);
        state1.stateTransitions.put("epsilon", transition1);

        // Create third state
        id = counter.toString();
        NFAState state3 = new NFAState(id, false);
        counter += 1;
        this.states.add(state3);

        // Add epsilon character1 transition from second state to third state
        Set<NFAState> transition2 = new HashSet<>();
        transition2.add(state3);
        state2.stateTransitions.put(character1, transition2);

        // Create fourth state
        String id4 = counter.toString();
        NFAState state4 = new NFAState(id4, false);
        counter += 1;
        this.states.add(state4);

        // Add character2 transition from third state to fourth state
        Set<NFAState> transition3 = new HashSet<>();
        transition3.add(state4);
        state3.stateTransitions.put(character2, transition3);

        // Create fifth state
        String id5 = counter.toString();
        NFAState state5 = new NFAState(id5, terminating);
        this.states.add(state5);

        // Add epsilon transition from fourth state to fifth state
        Set<NFAState> transition4 = new HashSet<>();
        transition4.add(state5);
        state3.stateTransitions.put("epsilon", transition4);

        // TODO
        this.transitionTable = new HashMap<>();
        this.maxCount = counter + 1;
    }

    public NFAConcat(NFA midNFA1, NFA midNFA2, boolean terminating, Integer counter) {
        // Create first state
        this.states = new HashSet<>();
        String id = counter.toString();
        counter += 1;
        NFAState state1 = new NFAState(id, false);
        this.states.add(state1);

        // Set starting state
        this.startState = state1;

        // Second state is starting state of midNFA1

        // Add epsilon transition from first state to second state
        Set<NFAState> transition = new HashSet<>();
        transition.add(midNFA1.startState);
        state1.stateTransitions.put("epsilon", transition);

        // Add epsilon transition from ending state of midNFA1 to starting state of midNFA2
        transition = new HashSet<>();
        transition.add(midNFA2.startState);
        midNFA1.endState.stateTransitions.put("epsilon", transition);

        // Update state set
        this.states.addAll(midNFA1.states);
        this.states.addAll(midNFA2.states);

        // Create fourth state
        id = counter.toString();
        NFAState state2 = new NFAState(id, terminating);
        this.states.add(state2);

        // Make fourth state ending state
        this.endState = state2;

        // Add epsilon transition from ending state of midNFA2 to fourth state
        transition = new HashSet<>();
        transition.add(state2);
        midNFA2.endState.stateTransitions.put("epsilon", transition);

        // TODO
        this.transitionTable = new HashMap<>();
        this.maxCount = counter + 1;
    }
}
