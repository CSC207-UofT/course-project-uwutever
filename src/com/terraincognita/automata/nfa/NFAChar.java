package com.terraincognita.automata.nfa;

import com.terraincognita.automata.nfa.NFA;
import com.terraincognita.automata.states.NFAState;

import java.util.*;

public class NFAChar extends NFA {

    private final int maxCount;

    public NFAChar(String character, boolean terminating, Integer counter) {
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
        state2.stateTransitions.put(character, transition2);

        String id4 = counter.toString();
        NFAState state4 = new NFAState(id4, terminating);
        this.states.add(state4);
        Set<NFAState> transition3 = new HashSet<>();
        transition3.add(state4);
        state3.stateTransitions.put("epsilon", transition3);

        // TODO
        this.transitionTable = new HashMap<>();
        this.maxCount = counter + 1;
    }

    public NFAChar(NFA midNFA, boolean terminating, Integer counter) {
        // Create first state
        this.states = new HashSet<>();
        String id = counter.toString();
        counter += 1;
        NFAState state1 = new NFAState(id, false);
        this.states.add(state1);

        // Set starting state
        this.startState = state1;

        // Second state is starting state of midNFA
        NFAState state2 = midNFA.startState;

        // Add epsilon transition from first state to second state
        Set<NFAState> transition = new HashSet<>();
        transition.add(state2);
        state1.stateTransitions.put("epsilon", transition);

        // Update state set
        this.states.addAll(midNFA.states);

        // Create fourth state
        id = counter.toString();
        state2 = new NFAState(id, terminating);
        this.states.add(state2);

        // Make fourth state ending state
        this.endState = state2;

        // Add epsilon transition from third state to fourth state
        transition = new HashSet<>();
        transition.add(state2);
        midNFA.endState.stateTransitions.put("epsilon", transition);

        // TODO
        this.transitionTable = new HashMap<>();
        this.maxCount = counter + 1;
    }

    public int getMaxCount() {
        return maxCount;
    }
}
