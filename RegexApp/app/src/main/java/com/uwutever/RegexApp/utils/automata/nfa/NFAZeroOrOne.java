package com.uwutever.RegexApp.utils.automata.nfa;

import java.util.*;

public class NFAZeroOrOne extends NFAOperations {
    /**
     * Return an NFA that accept 0 or 1 character
     *       _______________ e
     *      /               \
     * -> s1 -> s2 -> s3 -> s4
     *       e    char   e
     *
     */
    public static NFA build(String character, boolean terminating, Integer counter) {
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

        nfaBuilder.addTransition(id1, "epsilon", id4);

        nfaBuilder.getResult().setMaxCount(counter + 1);

        return nfaBuilder.getResult();
    }

    public static NFA build(NFA midNFA, boolean terminating, Integer counter) {
        NFABuilder nfaBuilder = new NFABuilder();
        nfaBuilder.reset();

        String id1 = counter.toString();
        nfaBuilder.addState(id1, false);
        nfaBuilder.setStartState(id1);
        counter += 1;

        List<String> midNFAStartEnd = appendMidNFA(nfaBuilder, midNFA, counter);
        counter += midNFA.getStates().size();

        nfaBuilder.addTransition(id1,"epsilon", midNFAStartEnd.get(0));

        String id4 = counter.toString();
        nfaBuilder.addState(id4, terminating);
        nfaBuilder.setEndState(id4);

        // midnfa end to id4
        nfaBuilder.addTransition(midNFAStartEnd.get(1), "epsilon", id4);

        //id 1 to id 4
        nfaBuilder.addTransition(id1, "epsilon", id4);

        nfaBuilder.getResult().setMaxCount(counter + 1);

        return nfaBuilder.getResult();
    }
}
