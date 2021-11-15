package com.terraincognita.automata.nfa;

import java.util.*;

public class NFAChar extends NFAOperations{
    public NFA build(String character, boolean terminating, Integer counter) {
        NFABuilder nfaBuilder = new NFABuilder();
        nfaBuilder.reset();

        String id1 = counter.toString();
        nfaBuilder.addState(id1, false);
        nfaBuilder.setStartState(id1);
        counter += 1;

        String id2 = counter.toString();
        nfaBuilder.addState(id2, false);
        nfaBuilder.addTransition(id1, "epsilon", id2);
        counter += 1;

        String id3 = counter.toString();
        nfaBuilder.addState(id3, false);
        nfaBuilder.addTransition(id2, character, id3);
        counter += 1;

        String id4 = counter.toString();
        nfaBuilder.addState(id4, terminating);
        nfaBuilder.setEndState(id4);
        nfaBuilder.addTransition(id3, "epsilon", id4);

        nfaBuilder.getResult().setMaxCount(counter + 1);

        return nfaBuilder.getResult();
    }

    public NFA build(NFA midNFA, boolean terminating, Integer counter) {
        NFABuilder nfaBuilder = new NFABuilder();
        nfaBuilder.reset();

        String id1 = counter.toString();
        nfaBuilder.addState(id1, false);
        nfaBuilder.setStartState(id1);
        counter += 1;

        List<String> midNFAStartEnd = appendMidNFA(nfaBuilder, midNFA, counter);
        counter += midNFA.getStates().size();

        // add e transition between id1 and the start state of midnfa
        nfaBuilder.addTransition(id1, "epsilon", midNFAStartEnd.get(0));

        // Create fourth state
        String id2 = counter.toString();
        nfaBuilder.addState(id2, terminating);
        nfaBuilder.setEndState(id2); // make s2 ending state

        // Add epsilon transition from midnfa end state to s2 state
        nfaBuilder.addTransition(midNFAStartEnd.get(1), "epsilon", id2);

        //set max count
        nfaBuilder.getResult().setMaxCount(counter + 1);

        return nfaBuilder.getResult();
    }


}
