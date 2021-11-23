package com.uwutever.RegexApp.utils.automata.states;

import java.util.HashMap;
import java.util.Map;

public class DFAState implements FSAState {
    private final String id;
    private boolean isAccepting;

    /**
     * Constructor of DFAState
     */
    public DFAState(String id, boolean isAccepting){
        this.id = id;
        this.isAccepting = isAccepting;
    }

    /**
     * Getter method of id
     */
    public String getId(){
        return this.id;
    }

    /**
     * Setter method of isAccepting
     */
    public void setAccepting(boolean isAccepting){
        this.isAccepting = isAccepting;
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
}
