package com.terraincognita.automata.nfa;

import com.terraincognita.automata.states.NFAState;

import java.util.ArrayList;
import java.util.List;

public class NFAOperations {
    public static List<String> appendMidNFA(NFABuilder nfaBuilder, NFA midNFA, int counter){
        // add midNFA to this nfa
        // merge states
        List<NFAState> orderedMidNfaStates = new ArrayList<>(midNFA.getStates());
        for (int i = 0; i < orderedMidNfaStates.size(); i++){
            nfaBuilder.addState(String.valueOf(i+counter));
        }

        //get start and end state id
        String midNFAStartStateId = String.valueOf(orderedMidNfaStates.indexOf(midNFA.startState) + counter - 1);
        String midNFAEndStateId = String.valueOf(orderedMidNfaStates.indexOf(midNFA.endState) + counter - 1);

        //update transition table
        for(NFAState fromState : orderedMidNfaStates){
            for (String alphabet: midNFA.transitionTable.get(fromState.getId()).keySet()){
                for (NFAState toState : midNFA.transitionTable.get(fromState.getId()).get(alphabet)){
                    // transform the fixed fromState, toState id to new Id
                    String newFromID = String.valueOf(orderedMidNfaStates.indexOf(fromState) + counter - 1);
                    String newToID = String.valueOf(orderedMidNfaStates.indexOf(toState) + counter - 1);

                    //add transition using the new ID
                    nfaBuilder.addTransition(newFromID, alphabet, newToID);
                }
            }
        }

        List<String> ret = new ArrayList<>();
        ret.add(midNFAStartStateId);
        ret.add(midNFAEndStateId);
        return ret;
    }
}
