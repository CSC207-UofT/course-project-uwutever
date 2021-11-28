package automata.states;

import java.util.HashMap;
import java.util.Map;

public class DFAState extends FSAState {

    /** The transition table for this state */
    protected Map<String, DFAState> transitionTable;

    /**
     * Constructor of DFAState
     */
    public DFAState(){
        this.transitionTable = new HashMap<>();
    }

    /**
     * Return the output state of the delta function with the given alphabet
     * Return null if there is no transitions for the given alphabet
     *
     * @param alphabet the alphabet for the transition
     * @return the output of the delta function
     */
    @Override
    public DFAState delta(String alphabet){
        return this.transitionTable.get(alphabet);
    }

    /**
     * Add a transition to this FSAState
     *
     * @param alphabet the alphabet for the transition
     * @param toState the to-state of the transition
     */
    public void addTransition(String alphabet, DFAState toState) {
        this.transitionTable.put(alphabet, toState);
    }
}
