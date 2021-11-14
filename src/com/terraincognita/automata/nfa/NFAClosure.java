package com.terraincognita.automata.nfa;

import com.terraincognita.automata.nfa.NFA;
import com.terraincognita.automata.states.NFAState;

import java.util.*;

public class NFAClosure extends NFA {

    private final int maxCount;

    public NFAClosure(String character, boolean terminating, Integer counter) {
        // Create first state
        this.states = new HashSet<>();
        String id = counter.toString();
        counter += 1;
        NFAState state1 = new NFAState(id, false);
        this.states.add(state1);

        // Set starting state
        this.startState = state1;

        // Create second state
        id = counter.toString();
        NFAState state2 = new NFAState(id, false);
        this.states.add(state2);

        // Add epsilon transition from first state to second state
        Set<NFAState> transition = new HashSet<>();
        transition.add(state2);
        state1.stateTransitions.put("epsilon", transition);

        // Create third state
        id = counter.toString();
        state1 = new NFAState(id, false);
        this.states.add(state1);

        // Add character transition from second state to third state
        transition = new HashSet<>();
        transition.add(state1);
        state2.stateTransitions.put(character, transition);

        // Add epsilon transition from third state to second state
        transition = new HashSet<>();
        transition.add(state2);
        state1.stateTransitions.put("epsilon", transition);

        // Create fourth state
        id = counter.toString();
        state2 = new NFAState(id, terminating);
        this.states.add(state2);

        // Add epsilon transition from third state to fourth state
        transition = new HashSet<>();
        transition.add(state2);
        state1.stateTransitions.put("epsilon", transition);

        // TODO
        this.transitionTable = new HashMap<>();
        this.maxCount = counter + 1;
    }

    public NFAClosure(NFA midNFA, boolean terminating, Integer counter) {
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

        // Add epsilon transition from ending state of midNFA to its starting state
        transition = new HashSet<>();
        transition.add(midNFA.startState);
        midNFA.endState.stateTransitions.put("epsilon", transition);

        // Add epsilon transition from third state to second state
        transition = new HashSet<>();
        transition.add(state2);
        state1.stateTransitions.put("epsilon", transition);

        // Create fourth state
        id = counter.toString();
        state2 = new NFAState(id, terminating);
        this.states.add(state2);

        // Add epsilon transition from third state to fourth state
        transition = new HashSet<>();
        transition.add(state2);
        state1.stateTransitions.put("epsilon", transition);

        // TODO
        this.transitionTable = new HashMap<>();
        this.maxCount = counter + 1;
    }

    public int getMaxCount() {
        return maxCount;
    }
}
