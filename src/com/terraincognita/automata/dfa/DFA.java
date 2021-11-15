package com.terraincognita.automata.dfa;

import com.terraincognita.automata.FSA;
import com.terraincognita.automata.states.DFAState;
import com.terraincognita.errors.*;

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
     * @throws UnknownAlphabetException if the alphabet is not in the alphabets set
     */
    @Override
    public DFAState delta(DFAState fromState, String alphabet) {
        String fromStateId = fromState.getId();
        if(this.alphabets.contains(alphabet)){
            return this.transitionTable.get(fromStateId).get(alphabet);
        } else{
            throw new UnknownIdException(alphabet);
        }
    }

    /**
     * Run the FSA from the start state with a given string (delta* function)
     *
     * @param alphabets a given string to run the FSA
     * @return the reached state(s)
     */
    @Override
    public DFAState transitions(String alphabets) throws NullStartStateException {
        if(this.startState == null){
            throw new NullStartStateException();
        }

        DFAState curr = this.startState;
        for(int i = 0; i < alphabets.length(); i++){
            curr = delta(curr, String.valueOf(alphabets.charAt(i)));
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
    public boolean accept(String alphabets) throws NullStartStateException {
        try{
            return transitions(alphabets).isAccepting();
        } catch (UnknownAlphabetException e){
            return false;
        }
    }
}
