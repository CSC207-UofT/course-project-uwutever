package automata.nfa;

import automata.FSAState;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NFAState extends FSAState {

    /** The transition table for this state */
    protected Map<String, Set<NFAState>> transitionTable;

    /**
     * Constructor of NFAState
     */
    public NFAState(){
        this.transitionTable = new HashMap<>();
    }

    /**
     * Return the output of the delta function with the given alphabet
     * Return null if there is no transition for the given alphabet
     *
     * @param alphabet the given alphabet
     * @return the output of the delta function
     */
    @Override
    public Set<NFAState> delta(String alphabet) {
        return this.transitionTable.get(alphabet);
    }

    /**
     * Add a transition to this FSAState
     *
     * @param alphabet the alphabet for the transition
     * @param toState the to-state of the transition
     */
    public void addTransition(String alphabet, NFAState toState) {
        if (this.transitionTable.containsKey(alphabet)){
            // add toState to the set if the alphabet is in the transition table
            this.transitionTable.get(alphabet).add(toState);
        } else{
            // put the new (alphabet, to-state set) pair in the transition table
            // if the alphabet is not in the transition table
            Set<NFAState> toSet = new HashSet<>();
            toSet.add(toState);
            this.transitionTable.put(alphabet, toSet);
        }
    }
}
