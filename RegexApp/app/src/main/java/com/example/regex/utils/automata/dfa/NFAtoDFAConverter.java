package com.terraincognita.automata.dfa;

import com.terraincognita.automata.nfa.NFA;
import com.terraincognita.automata.states.DFAState;
import com.terraincognita.automata.states.NFAState;

import java.util.*;

public class NFAtoDFAConverter {
    /**
     * Return the equivalent DFA of this.nfa using power set construction
     * 1. Get an ordered list of states of the NFA
     * 2. Add a state for every subset of the NFA
     * 3. Get the new start state
     * 4. Draw the transition between the subsets
     *
     * DFA ID policy:
     * The return DFA has states which IDs are Binary Strings from 00...0 to 11...1 (same length as NFA.states)
     * The DFA State ID represents which NFA states are included in the subset
     * For instance if the char at position i in the ID is 1, then NFAStates[i] is included in this DFA state
     * (NFAStates is the ordered list obtained from step 1)
     *
     * @param nfa the NFA to be converted to DFA
     * @return an equivalent DFA
     */
    public static DFA convert(NFA nfa) {
        DFABuilder dfaBuilder = new DFABuilder();

        List<NFAState> orderedStates = new ArrayList<>(nfa.getStates()); //get the ordered states list of the NFA

        addSubsetState(dfaBuilder, orderedStates); //add states for each subset

        dfaBuilder.setStartState(getDFAStateId(nfa.epsilon(nfa.getStartState()), orderedStates));
        //set start state as epsilon of the nfa start state

        Set<String> alphabets = new HashSet<>(nfa.getAlphabets());
        alphabets.remove("epsilon"); // remove epsilon in case it is in the alphabets
        alphabets.remove("");// remove the empty string in case it is in the alphabets
        addDFATransitions(dfaBuilder, alphabets, nfa, orderedStates); // draw the new transitions

        return dfaBuilder.getResult();
    }

    /**
     * Helper method of convert
     * Given a set of NFAState, return the corresponding state id in NFA
     *
     * @param NFASubsetStates a power set of NFA.states
     * @param orderedStates a list of NFAStates of the original NFA
     * @return the corresponding Binary String id of that power set in the DFA
     */
    private static String getDFAStateId(Collection<NFAState> NFASubsetStates, List<NFAState> orderedStates){
        StringBuilder id = new StringBuilder();

        for(NFAState state:orderedStates){
            if(NFASubsetStates.contains(state)){
                id.append(1);
            } else{
                id.append(0);
            }
        }

        return id.toString();
    }

    /**
     * Helper method of convert
     * Given a binary string DFAState id, return the corresponding subset of NFAStates
     *
     * @param DFAStateId a binary string of DFAState id
     * @param orderedStates a list of NFAStates of the original NFA
     * @return a subset of nfa.states
     */
    private static Set<NFAState> getNFAStateSubset(String DFAStateId, List<NFAState> orderedStates){
        Set<NFAState> ret = new HashSet<>();
        for(int i = 0; i < DFAStateId.length(); i++){
            if (DFAStateId.charAt(i)=='1'){
                ret.add(orderedStates.get(i));
            }
        }
        return ret;
    }

    /**
     * Helper method of convert
     * Add state for every subset of the nfa.
     * For an NFA containing n states, it has 2^n subsets
     *
     * @param dfaBuilder the builder instance in convert
     * @param orderedStates an ordered list of NFA.states
     */
    private static void addSubsetState(DFABuilder dfaBuilder, List<NFAState> orderedStates) {
        int n = orderedStates.size();
        for (int i = 0; i < Math.pow(2, n); i++) {
            StringBuilder StateId = new StringBuilder(Integer.toBinaryString(i));

            // add 0 in front if the id string is too short
            while(StateId.length() < n){
                StateId.insert(0, "0");
            }

            // add state to DFA with the binary string id and accepting status
            dfaBuilder.addState(StateId.toString(), isAcceptingState(StateId.toString(), orderedStates));
        }
    }

    /**
     * Helper method of addSubsetState
     * Given a Binary String DFA state id indicating which states in the NFA are present
     * return whether the state is accepting in the DFA
     *
     * @param statusString a binary string representing which DFA state is present
     * @param orderedStates a list of NFAStates
     * @return whether the state is an accepting state in the DFA
     */
    private static boolean isAcceptingState(String statusString, List<NFAState> orderedStates){
        List<Integer> acceptingPos = getAcceptingPosition(orderedStates);
        for(int pos: acceptingPos){
            if(statusString.charAt(pos)=='1'){
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method of isAcceptingState
     * Given an order list of NFAStates, return a list of index of the accepting states
     *
     * @param orderedStates a list of NFAStates
     * @return the indexes of the accepting states in orderedStates
     */
    private static List<Integer> getAcceptingPosition(List<NFAState> orderedStates){
        List<Integer> acceptingPos = new ArrayList<>();

        for (int i = 0; i < orderedStates.size(); i++){
            if(orderedStates.get(i).isAccepting()){
                acceptingPos.add(i);
            }
        }
        return acceptingPos;
    }

    /**
     * Helper method of convert
     * Add the transitions to the DFA
     *
     * @param dfaBuilder the builder instance in convert
     * @param alphabets a set of alphabets for the new DFA
     * @param nfa the nfa given in the convert function
     * @param orderedStates a list of NFAStates of the original NFA
     */
    private static void addDFATransitions(DFABuilder dfaBuilder, Set<String> alphabets,
                                          NFA nfa, List<NFAState> orderedStates){
        for(DFAState state: dfaBuilder.getResult().getStates()){
            for(String alphabet: alphabets){
                // get the transition states with a fixed DFA state and alphabet
                String toID = getTransitionOut(state, alphabet, nfa, orderedStates);
                dfaBuilder.addTransition(state.getId(), alphabet, toID);
            }
        }
    }

    /**
     * Helper method of addDFATransitions
     * Return the expected id of the delta function output state with a given DFAState and alphabet
     *
     * @param dfaState the input DFAState of the transition
     * @param alphabet the alphabet of the transition
     * @param orderedStates a list of NFAStates of the original NFA
     * @return the id of output state of delta(dfaState, alphabet) transition
     */
    private static String getTransitionOut(DFAState dfaState, String alphabet,
                                           NFA nfa, List<NFAState> orderedStates){
        Set<NFAState> transitToStates = new HashSet<>();
        for (NFAState fromNfaState : getNFAStateSubset(dfaState.getId(), orderedStates)){
            for (NFAState ToNfaState : nfa.delta(fromNfaState, alphabet)){
                transitToStates.addAll(nfa.epsilon(ToNfaState));
            }
        }
        return getDFAStateId(transitToStates, orderedStates);
    }
}
