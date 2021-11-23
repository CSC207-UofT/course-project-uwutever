package com.uwutever.RegexApp.utils.automata.nfa;

import java.util.*;

public class NFAUnion extends NFAOperations {
    public NFA build(String character1, String character2, boolean terminating, Integer counter) {
        NFABuilder nfaBuilder = new NFABuilder();
        nfaBuilder.reset();

        // 1 -> {2,3} e
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
        nfaBuilder.addTransition(id1, "epsilon", id3);
        counter += 1;

        // 2 -> 4 character 1
        String id4 = counter.toString();
        nfaBuilder.addState(id4, false);
        nfaBuilder.addTransition(id2, character1, id4);
        counter += 1;

        //3 -> 5 character 2
        String id5 = counter.toString();
        nfaBuilder.addState(id5, false);
        nfaBuilder.addTransition(id3, character2, id5);
        counter += 1;

        //{4, 5} -> 6 e
        String id6 = counter.toString();
        nfaBuilder.addState(id6, terminating);
        nfaBuilder.addTransition(id4, "epsilon", id6);
        nfaBuilder.addTransition(id5, "epsilon", id6);

        nfaBuilder.getResult().setMaxCount(counter + 1);

        return nfaBuilder.getResult();
    }

    public static NFA build(NFA midNFA1, NFA midNFA2, boolean terminating, Integer counter) {
        NFABuilder nfaBuilder = new NFABuilder();
        nfaBuilder.reset();

        // 1 -> {2,3} e
        String id1 = counter.toString();
        nfaBuilder.addState(id1, false);
        nfaBuilder.setStartState(id1);
        counter += 1;

        List<String> midNFA1StartEnd = appendMidNFA(nfaBuilder, midNFA1, counter);
        counter += midNFA1.states.size();

        List<String> midNFA2StartEnd = appendMidNFA(nfaBuilder, midNFA2, counter);
        counter += midNFA2.states.size();

        nfaBuilder.addTransition(id1,"epsilon", midNFA1StartEnd.get(0));
        nfaBuilder.addTransition(id1,"epsilon", midNFA2StartEnd.get(0));

        //{4, 5} -> 6 e
        String id6 = counter.toString();
        nfaBuilder.addState(id6, terminating);
        nfaBuilder.setEndState(id6);

        nfaBuilder.addTransition(midNFA1StartEnd.get(1), "epsilon", id6);
        nfaBuilder.addTransition(midNFA2StartEnd.get(1), "epsilon", id6);

        nfaBuilder.getResult().setMaxCount(counter + 1);

        return nfaBuilder.getResult();
    }
}
