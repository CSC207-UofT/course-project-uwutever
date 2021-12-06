package com.uwutever.RegexApp.utils.automata.dfa;

import java.util.*;

/**
 * This class converts a DFA from a graph form into a quintuple representation
 * - Each State is represented by a unique String
 * - Each Transition is represented in a nested map
 */
public class DFAQuintuple {
    public Set<String> states;
    public Set<String> alphabets;
    public String startState;
    public Set<String> acceptingStates;
    public Map<String, Map<String, String>> transitionTable;

    /**
     * Constructor of the class
     * Convert the input DFA into quintuple form directly
     * @param dfa the DFA to be converted into Quintuple form
     */
    public DFAQuintuple(DFA dfa){
        setStates(dfa);
        this.alphabets = dfa.getAlphabets();
        this.startState = String.valueOf(dfa.states.indexOf(dfa.getStartState()));
        setAcceptingStates(dfa);
        setTransitionTable(dfa);
    }

    /**
     * Helper method of the constructor
     * Set the states as Strings of int from 0 to n-1
     * @param dfa the DFA to be converted into Quintuple form
     */
    private void setStates(DFA dfa) {
        this.states = new HashSet<>();
        for(int i = 0; i < dfa.states.size(); i++){
            this.states.add(String.valueOf(i));
        }
    }

    /**
     * Helper method of the Constructor
     * Set the accepting State as the index of the DFA accepting state indexes
     * @param dfa the DFA to be converted into Quintuple form
     */
    private void setAcceptingStates(DFA dfa){
        this.acceptingStates = new HashSet<>();
        for (DFAState state : dfa.getAcceptingStates()){
            this.acceptingStates.add(String.valueOf(dfa.states.indexOf(state)));
        }
    }

    /**
     * Helper method of the Constructor
     * Set the Transition Table to the corresponding nested map of Strings
     * @param dfa the DFA to be converted into Quintuple form
     */
    private void setTransitionTable(DFA dfa){
        this.transitionTable = new HashMap<>();

        // loop over all the dfa state
        for(DFAState fromState : dfa.getStates()){
            int fromStateIndex = dfa.states.indexOf(fromState);
            // create a specific transition table for one state
            this.transitionTable.put(String.valueOf(fromStateIndex), new HashMap<>());
            Map<String, String> fromStateTransitionTable = this.transitionTable.get(String.valueOf(fromStateIndex));

            // loop over all the alphabets in the state transition table and convert it into index style
            for(String alphabet : fromState.transitionTable.keySet()){
                fromStateTransitionTable.put(alphabet, String.valueOf(dfa.states.indexOf(fromState.delta(alphabet))));
            }
        }
    }
}
