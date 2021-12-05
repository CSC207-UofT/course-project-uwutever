package automata.nfa;

import java.util.*;

/**
 * This class converts a DFA from a graph form into a quintuple representation
 * - Each State is represented by a unique String
 * - Each Transition is represented in a nested map
 */
public class NFAQuintuple {
    public Set<String> states;
    public Set<String> alphabets;
    public String startState;
    public Set<String> acceptingStates;
    public Map<String, Map<String, Set<String>>> transitionTable;

    /**
     * Constructor of the class
     * Convert the input NFA into quintuple form directly
     * @param nfa the NFA to be converted into Quintuple form
     */
    public NFAQuintuple(NFA nfa){
        setStates(nfa);
        this.alphabets = nfa.getAlphabets();
        this.startState = String.valueOf(nfa.states.indexOf(nfa.getStartState()));
        setAcceptingStates(nfa);
        setTransitionTable(nfa);
    }

    /**
     * Helper method of the constructor
     * Set the states as Strings of int from 0 to n-1
     * @param nfa the NFA to be converted into Quintuple form
     */
    private void setStates(NFA nfa) {
        this.states = new HashSet<>();
        for(int i = 0; i < nfa.states.size(); i++){
            this.states.add(String.valueOf(i));
        }
    }

    /**
     * Helper method of the Constructor
     * Set the accepting State as the index of the NFA accepting state indexes
     * @param nfa the NFA to be converted into Quintuple form
     */
    private void setAcceptingStates(NFA nfa){
        this.acceptingStates = new HashSet<>();
        for (NFAState state : nfa.getAcceptingStates()){
            this.acceptingStates.add(String.valueOf(nfa.states.indexOf(state)));
        }
    }

    /**
     * Helper method of the Constructor
     * Set the Transition Table to the corresponding nested map of Strings
     * @param nfa the NFA to be converted into Quintuple form
     */
    private void setTransitionTable(NFA nfa){
        this.transitionTable = new HashMap<>();

        // loop over all the nfa state
        for(NFAState fromState : nfa.getStates()){
            int fromStateIndex = nfa.states.indexOf(fromState);
            // create a specific transition table for one state
            this.transitionTable.put(String.valueOf(fromStateIndex), new HashMap<>());
            Map<String, Set<String >> fromStateTransitionTable = this.transitionTable.get(
                    String.valueOf(fromStateIndex));

            // loop over all the alphabets in the state transition table and convert it into index style
            for(String alphabet : fromState.transitionTable.keySet()){
                fromStateTransitionTable.put(alphabet, new HashSet<>());
                Set<String> toSet = fromStateTransitionTable.get(alphabet);
                for (NFAState toState : fromState.delta(alphabet)){
                    toSet.add(String.valueOf(nfa.states.indexOf(toState)));
                }
            }
        }
    }
}
