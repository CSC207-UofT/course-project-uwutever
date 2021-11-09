package com.terraincognita.automata;

public class FSAState implements FSAStatePlan {

    private boolean isInitial;

    private boolean isFinal;

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
