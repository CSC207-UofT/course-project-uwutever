package automata.nfa;

import java.util.*;

public class NFAConcat extends NFAOperations {
    public NFA build(String character1, String character2, boolean terminating, Integer counter) {
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
        nfaBuilder.addTransition(id2, character1, id3);
        counter += 1;

        String id4 = counter.toString();
        nfaBuilder.addState(id4, false);
        nfaBuilder.addTransition(id3, character2, id4);
        counter += 1;

        String id5 = counter.toString();
        nfaBuilder.addState(id5, terminating);
        nfaBuilder.addTransition(id4, "epsilon", id5);
        counter += 1;

        nfaBuilder.setEndState(id5);

        nfaBuilder.getResult().setMaxCount(counter + 1);

        return nfaBuilder.getResult();
    }

    public static NFA build(NFA midNFA1, NFA midNFA2, boolean terminating, Integer counter) {
        NFABuilder nfaBuilder = new NFABuilder();
        nfaBuilder.reset();

        String id1 = counter.toString();
        nfaBuilder.addState(id1, false);
        nfaBuilder.setStartState(id1);
        counter += 1;

        List<String> midNFA1StartEnd = appendMidNFA(nfaBuilder, midNFA1, counter);
        counter += midNFA1.getStates().size();
        List<String> midNFA2StartEnd = appendMidNFA(nfaBuilder, midNFA2, counter);
        counter += midNFA2.getStates().size();

        String id5 = counter.toString();
        nfaBuilder.addState(id5, terminating);
        nfaBuilder.setEndState(id5);

        //s1 -> midNFA1 start
        nfaBuilder.addTransition(id1, "epsilon", midNFA1StartEnd.get(0));
        //midNFA1 end -> midNFA 2 start
        nfaBuilder.addTransition(midNFA1StartEnd.get(1), "epsilon", midNFA2StartEnd.get(0));
        //midNFA2 end -> s5
        nfaBuilder.addTransition(midNFA2StartEnd.get(1), "epsilon", id5);

        nfaBuilder.getResult().setMaxCount(counter + 1);

        return nfaBuilder.getResult();
    }
}
