package com.terraincognita.automata.states;

import com.terraincognita.automata.NFAStateTransition;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NFAState implements FSAState {
    private final String id;
    private final boolean isAccepting;
    public NFAStateTransition stateTransitions;

    /**
     * Constructor of NFAState with
     * @param id the id of the state
     * @param isAccepting whether the state is an accepting state
     */
    public NFAState(String id, boolean isAccepting){
        this.id = id;
        this.isAccepting = isAccepting;
        this.stateTransitions = new NFAStateTransition();
    }


    /**
     * Return whether the state is an accepting state
     *
     * @return whether the state is an accepting state
     */
    @Override
    public boolean isAccepting() {
        return this.isAccepting;
    }

    /**
     * Return the id of the state
     *
     * @return the id of the state
     */
    public String getId() {
        return this.id;
    }
}
