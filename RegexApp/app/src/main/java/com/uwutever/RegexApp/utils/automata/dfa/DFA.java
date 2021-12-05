package automata.dfa;

import automata.FSA;

import java.util.*;

public class DFA extends FSA<DFAState> {
    protected List<DFAState> states;
    protected Set<String> alphabets;
    protected DFAState startState;
    protected Set<DFAState> acceptingStates;
    protected DFAState deadState;

    /**
     * Constructor DFA
     */
    public DFA(){
        this.states = new ArrayList<>();
        this.startState = null;
        this.alphabets = new HashSet<>();
        this.acceptingStates = new HashSet<>();
        this.deadState = null;
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
        return this.acceptingStates;
    }

    /**
     * Return the set of states of the FSA
     */
    @Override
    public List<DFAState> getStates() {
        return this.states;
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
        if (fromState.delta(alphabet) != null){
            return fromState.delta(alphabet);
        } else{
            // return the dead state if the alphabet is invalid
            return this.deadState;
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
            curr = this.delta(curr, String.valueOf(alphabets.charAt(i)));
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
        return this.acceptingStates.contains(this.transitions(this.startState, alphabets));
    }
}
