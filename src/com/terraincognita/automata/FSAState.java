package com.terraincognita.automata;

import java.util.Map;

public class FSAState implements FSAStatePlan {

    private boolean isInitial;

    private boolean isFinal;

    private Map<String, Object> transitions;

    private int id;

    @Override
    public void setID(int identity) {
        id = identity;
    }

    @Override
    public void setInitial(boolean initial) {
        isInitial = initial;
    }

    @Override
    public void setFinal(boolean ending) {
        isFinal = ending;
    }

    public boolean getInitial() {
        return isInitial;
    }

    public boolean getFinal() {
        return isFinal;
    }
}
