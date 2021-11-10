package com.terraincognita.automata;

public class DFA extends FSA {
    /**
     * Return the start state of the FSA
     */
    @Override
    public FSAState getStartState() {
        return null;
    }

    /**
     * Run the FSA from the start state with a given string
     *
     * @param alphabets a given string to run the FSA
     * @return the reached state(s)
     */
    @Override
    public Object transitions(String alphabets) {
        return null;
    }

    /**
     * Return whether the input string is accepted by the FSA
     *
     * @param alphabets the string to be tested
     * @return whether the input string is accepted by the FSA
     */
    @Override
    public boolean accept(String alphabets) {
        return false;
    }
}
