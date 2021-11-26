package automata.dfa;

import automata.FSA;
import automata.states.DFAState;
import errors.*;

import java.util.*;

public class DFA extends FSA<DFAState> {
    protected Map<String, DFAState> states;
    protected DFAState startState;
    protected Set<String> alphabets;
    protected Map<String, Map<String, DFAState>> transitionTable;

    /**
     * Constructor DFA
     */
    public DFA(){
        this.states = new HashMap<>();
        this.startState = null;
        this.alphabets = new HashSet<>();
        this.transitionTable = new HashMap<>();
    }

    /**
     * Return the start state of the FSA
     */
    @Override
    public DFAState getStartState() {
        return this.startState;
    }

    /**
     * Return the set of final states of the FSA
     */
    @Override
    public Set<DFAState> getAcceptingStates() {
        Set<DFAState> acceptingStates = new HashSet<>();

        for(DFAState state: this.states.values()){
            if(state.isAccepting()){
                acceptingStates.add(state);
            }
        }
        return acceptingStates;
    }

    /**
     * Return the set of states of the FSA
     */
    @Override
    public Collection<DFAState> getStates() {
        return this.states.values();
    }

    /**
     * Return the set of IDs of states of the FSA
     */
    @Override
    public Collection<String> getStatesID() {
        return this.states.keySet();
    }

    /**
     * Return the set of alphabets of the FSA
     */
    @Override
    public Set<String> getAlphabets() {
        return this.alphabets;
    }

    /**
     * The delta function of the FSA
     * Transition function of a given state and alphabet
     *
     * @param fromState the state where the transition starts
     * @param alphabet  the alphabet for the transition
     */
    @Override
    public DFAState delta(DFAState fromState, String alphabet) {
        String fromStateId = fromState.getId();
        if(this.alphabets.contains(alphabet)){
            return this.transitionTable.get(fromStateId).get(alphabet);
        } else{
            // return null if the alphabet is invalid
            return null;
        }
    }

    /**
     * Run the FSA from the start state with a given string (delta* function)
     *
     * @param alphabets a given string to run the FSA
     * @return the reached state(s)
     */
    @Override
    public DFAState transitions(DFAState fromState, String alphabets){
        DFAState curr = fromState;
        for(int i = 0; i < alphabets.length(); i++){
            curr = delta(curr, String.valueOf(alphabets.charAt(i)));

            if(curr == null){
                //return null if runs into a deadstate due to invalid alphabet
                return null;
            }
        }
        return curr;
    }

    /**
     * Return whether the input string is accepted by the FSA
     *
     * @param alphabets the string to be tested
     * @return whether the input string is accepted by the FSA
     */
    @Override
    public boolean accept(String alphabets){
        if (transitions(this.startState, alphabets) == null){
            return false;
        } else{
            return transitions(this.startState, alphabets).isAccepting();
        }
    }
}
