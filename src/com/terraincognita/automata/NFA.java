package com.terraincognita.automata;

import java.util.*;

public class NFA extends FSA<NFAState>{
    protected Map<String, NFAState> states;
    protected NFAState startState;
    protected Map<String, Map<String, List<NFAState>>> transitionTable;

    public NFA(){
        this.states = new HashMap<>();
        this.startState = null;
        this.transitionTable = new HashMap<>();
    }

    /**
     * Return the start state of the FSA
     */
    @Override
    public NFAState getStartState() {
        return this.startState;
    }

    /**
     * Run the FSA from the start state with a given string
     *
     * @param alphabets a given string to run the FSA
     * @return the reached state(s)
     */
    @Override
    public List<NFAState> transitions(String alphabets) {
        //TODO
        return null;    }

    /**
     * Return whether the input string is accepted by the FSA
     *
     * @param alphabets the string to be tested
     * @return whether the input string is accepted by the FSA
     */
    @Override
    public boolean accept(String alphabets) {
        for(NFAState state:transitions(alphabets)){
            if (state.isAccepting()){
                return true;
            }
        }
        return false;
    }
}
