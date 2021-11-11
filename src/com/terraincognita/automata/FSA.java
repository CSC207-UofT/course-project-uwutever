package com.terraincognita.automata;

import java.util.*;

public abstract class FSA {

    Set<FSAState> states;

    public Set<FSAState> states() {
        return this.states;
    };

    public void addState(FSAState state) {
        states.add(state);
    }

}
