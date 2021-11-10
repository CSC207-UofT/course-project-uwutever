package com.terraincognita.automata;

public abstract class FSA <T extends FSAState>{
    /**
     * Return the start state of the FSA
     */
    public abstract T getStartState();

    /**
     * Run the FSA from the start state with a given string
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
