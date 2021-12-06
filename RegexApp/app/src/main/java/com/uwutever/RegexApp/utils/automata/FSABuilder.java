package com.uwutever.RegexApp.utils.automata;

public interface FSABuilder{
    /**
     * Reset the builder by created a new FSA with the given id
     */
    void reset();

    /**
     * Set the start state of the FSA by index
     * @param index the id of the start state
     */
    void setStartState(int index);

    /**
     * Set a state in the FSA to be accepting
     * @param index the index of the state
     */
    void setAcceptingState(int index);

    /**
     * Add a new state to the FSA
     * @return the index of the addedState
     */
    int addNewState();

    /**
     * Add a transition to the FSA
     * Add the alphabet to the alphabet set if the alphabet is new
     * @param fromIndex the ID of the start of the transition
     * @param alphabet the alphabet of the transition
     * @param toIndex the ID of the end of the transition
     */
    void addTransition(int fromIndex, String alphabet, int toIndex);
}
