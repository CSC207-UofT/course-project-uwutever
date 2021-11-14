package com.terraincognita.automata;

import com.terraincognita.automata.states.FSAState;

import java.util.Collection;

public abstract class FSA <T extends FSAState>{
    /**
     * Return the start state of the FSA
     */
    public abstract T getStartState();

    /**
     * Return the set of final states of the FSA
     */
    public abstract Collection<T> getAcceptingStates();

    /**
     * Return the set of states of the FSA
     */
    public abstract Collection<T> getStates();

    /**
     * Return the set of alphabets of the FSA
     */
    public abstract Collection<String> getAlphabets();

    /**
     * The delta function of the FSA
     * Transition function of a given state and alphabet
     * @param fromState the state where the transition starts
     * @param alphabet the alphabet for the transition
     */
    public abstract Object delta(T fromState, String alphabet);

    /**
     * Run the FSA from the start state with a given string (delta* function)
     * @param alphabets a given string to run the FSA
     * @return the reached state(s)
     */
    public abstract Object transitions(String alphabets);

    /**
     * Return whether the input string is accepted by the FSA
     * @param alphabets the string to be tested
     * @return whether the input string is accepted by the FSA
     */
    public abstract boolean accept(String alphabets);

}
