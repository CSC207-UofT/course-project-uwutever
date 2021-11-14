package com.terraincognita.automata;

public interface FSABuilder{
    /**
     * Reset the builder by created a new FSA with the given id
     */
    void reset();

    /**
     * Set the start state of the FSA by index
     * @param id the id of the start state
     * @return true if the start state is set successfully
     */
    boolean setStartState(String id);

    /**
     * Add a state to the FSA with a given id
     * @param id the id of the state
     */
    void addState(String id);

    /**
     * Add a state to the FSA while indicating whether it is an accepting state
     * @param id the id of the state
     * @param isAccepting whether the state is an accepting state
     */
    void addState(String id, boolean isAccepting);

    /**
     * Add a transition to the FSA
     */
    void addTransition(String fromId, String alphabet, String toId);
}
