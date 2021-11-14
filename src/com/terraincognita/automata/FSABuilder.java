package com.terraincognita.automata;

public interface FSABuilder{
    /**
     * Reset the builder by created a new FSA with the given id
     */
    void reset();

    /**
     * Set the start state of the FSA by index
     * @param id the id of the start state
     * @throws IllegalArgumentException if the given ID is not in the FSA
     */
    void setStartState(String id);

    /**
     * Add a state to the FSA with a given id
     * @param id the id of the state
     * @throws IllegalArgumentException if the ID is already occupied in the FSA
     */
    void addState(String id);

    /**
     * Add a state to the FSA while indicating whether it is an accepting state
     * @param id the id of the state
     * @param isAccepting whether the state is an accepting state
     * @throws IllegalArgumentException if the ID is already occupied in the FSA
     */
    void addState(String id, boolean isAccepting);

    /**
     * Add a transition to the FSA
     * @param fromId the ID of the start of the transition
     * @param alphabet the alphabet of the transition
     * @param toId the ID of the end of the transition
     * @throws IllegalArgumentException if either fromId or toId is not in the FSA
     */
    void addTransition(String fromId, String alphabet, String toId);
}
