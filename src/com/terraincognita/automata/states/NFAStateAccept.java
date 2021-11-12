package com.terraincognita.automata.states;

public class NFAStateAccept extends NFAState {

    /**
     * Constructor of NFAState with
     * @param id: the id of the state
     * @param: isAccepting:true
     */
    public NFAStateAccept(String id) {
        super(id, true);
    }
}
