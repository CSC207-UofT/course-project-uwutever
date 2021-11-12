package com.terraincognita.automata;

import com.terraincognita.automata.states.NFAState;

public interface NFABuilder{
    /**
     * Reset the builder
     */
    void reset();

    /**
     * Set the start state of the FSA by index
     * @param state the start state
     */
    void setStartState(NFAState state);

    /**
     * Add a state to the FSA with a given id
     * @param state the state to be added
     */
    void addState(NFAState state, String id);

    /**
     * Add a state to the FSA while indicating whether it is an accepting state
     * @param state the state
     * @param isAccepting whether the state is an accepting state
     */
    void addState(NFAState state, String id, boolean isAccepting);

    /**
     * Add a transition to the FSA
     */
    void addTransition(NFAState from, String alphabet, NFAState to);
}
