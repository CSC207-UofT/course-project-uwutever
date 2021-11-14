package com.terraincognita.automata.nfa;

import com.terraincognita.automata.nfa.NFA;
import com.terraincognita.automata.states.NFAState;

import java.util.*;

public class NFAUnion extends NFA {

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

    public NFAUnion(NFA midNFA1, NFA midNFA2, boolean terminating, Integer counter) {
        // Create first state
        this.states = new HashSet<>();
        String id = counter.toString();
        counter += 1;
        NFAState state1 = new NFAState(id, false);
        this.states.add(state1);

        // Set starting state
        this.startState = state1;

        // Second state is starting state of midNFA
        // Add epsilon transition from first state to second state
        Set<NFAState> transition = new HashSet<>();
        transition.add(midNFA1.startState);
        state1.stateTransitions.put("epsilon", transition);

        // Third state is starting state of midNFA
        // Add epsilon transition from first state to second state
        Set<NFAState> transition2 = new HashSet<>();
        transition.add(midNFA2.startState);
        this.startState.stateTransitions.put("epsilon", transition2);

        // Update state set
        this.states.addAll(midNFA1.states);
        this.states.addAll(midNFA2.states);

        // Create fourth state
        id = counter.toString();
        NFAState state2 = new NFAState(id, terminating);
        this.states.add(state2);

        // Make fourth state ending state
        this.endState = state2;

        // Add epsilon transition from second state to fourth state
        HashSet<NFAState> transition3 = new HashSet<>();
        transition3.add(state2);
        midNFA1.endState.stateTransitions.put("epsilon", transition3);

        // Add epsilon transition from third state to fourth state
        HashSet<NFAState> transition4 = new HashSet<>();
        transition4.add(state2);
        midNFA2.endState.stateTransitions.put("epsilon", transition4);

        // TODO
        this.transitionTable = new HashMap<>();
        this.maxCount = counter + 1;
    }

    public int getMaxCount() {
        return maxCount;
    }
}
