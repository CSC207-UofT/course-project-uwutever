package com.terraincognita.automata.nfa;


public class NFAEpsilon extends NFA {
    public static NFA build(boolean terminating, Integer counter) {
        NFABuilder nfaBuilder = new NFABuilder();
        nfaBuilder.reset();

        String id1 = counter.toString();
        nfaBuilder.addState(id1, false);
        nfaBuilder.setStartState(id1); // set state 1 to start state
        counter += 1;

        String id2 = counter.toString();
        nfaBuilder.addState(id2, terminating);
        nfaBuilder.addTransition(id1, "epsilon", id2);
        counter += 1;

        nfaBuilder.getResult().setMaxCount(counter + 1);

        return nfaBuilder.getResult();
    }
}
