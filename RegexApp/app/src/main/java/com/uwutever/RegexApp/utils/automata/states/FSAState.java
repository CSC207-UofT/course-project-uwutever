package com.uwutever.RegexApp.utils.automata.states;

import java.util.Map;

public interface FSAState{

    /**
     * Return whether the state is an accepting state
     * @return whether the state is an accepting state
     */
    boolean isAccepting();
}
