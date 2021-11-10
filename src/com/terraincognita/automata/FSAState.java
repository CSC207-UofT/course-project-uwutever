package com.terraincognita.automata;

public abstract class FSAState{
    /**
     * Return whether the state is an accepting state
     * @return whether the state is an accepting state
     */
    public abstract boolean isAccepting();
}
