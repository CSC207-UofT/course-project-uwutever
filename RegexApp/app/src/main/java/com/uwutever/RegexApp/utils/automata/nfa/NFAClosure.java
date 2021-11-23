package com.uwutever.RegexApp.utils.automata.nfa;

import java.util.*;

public class NFAClosure extends NFAOperations {
    /**      __________ e
     *      /          \
     * -> s1 -> s2 -> s3
     *       e     c
     */
    public NFA build(String character, boolean terminating, Integer counter) {
        NFABuilder nfaBuilder = new NFABuilder();
        nfaBuilder.reset();

        String id1 = counter.toString();
        nfaBuilder.addState(id1, terminating);
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

        nfaBuilder.addTransition(id3, "epsilon", id1);
        nfaBuilder.setEndState(id1);

        nfaBuilder.getResult().setMaxCount(counter + 1);

        return nfaBuilder.getResult();
    }

    public static NFA build(NFA midNFA, boolean terminating, Integer counter) {
        NFABuilder nfaBuilder = new NFABuilder();
        nfaBuilder.reset();

        String id1 = counter.toString();
        nfaBuilder.addState(id1, terminating);
        nfaBuilder.setStartState(id1);
        nfaBuilder.setEndState(id1);
        counter += 1;

        List<String> midNFAStartEnd = appendMidNFA(nfaBuilder, midNFA, counter);
        counter += midNFA.getStates().size();

        // id1 to midnfa start
        nfaBuilder.addTransition(id1,"epsilon", midNFAStartEnd.get(0));

        // midnfa end back to id1
        nfaBuilder.addTransition(midNFAStartEnd.get(1), "epsilon", id1);

        nfaBuilder.getResult().setMaxCount(counter + 1);

        return nfaBuilder.getResult();
    }

}
