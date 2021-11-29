package automata.dfa;

import automata.nfa.NFA;
import automata.dfa.DFAState;
import automata.nfa.NFAState;

import java.util.*;

public class NFAtoDFAConverter {
    private NFA nfa;

    public NFAtoDFAConverter(NFA nfa){
        this.nfa = nfa;
    }

    /**
     * Return the equivalent DFA of this.nfa using power set construction
     * 1. Get an ordered list of states of the NFA
     * 2. Add a state for every subset of the NFA
     * 3. Set the new start state
     * 4. Set the new accepting state
     * 5. Draw the transition between the subsets
     *
     * The index of the DFAState, when converted into binary represent which NFAState is in the subset.
     * For instance, dfa.state.get(0) is the empty set state (dead state)
     *
     * @return an equivalent DFA
     */
    public DFA convert() {
        DFABuilder dfaBuilder = new DFABuilder();
        dfaBuilder.reset();

        List<NFAState> orderedStates = new ArrayList<>(nfa.getStates()); //get the ordered states list of the NFA

        addSubsetState(dfaBuilder, orderedStates); //add states for each subset

        dfaBuilder.setStartState(getDFAStateId(nfa.epsilon(nfa.getStartState()), orderedStates));
        //set start state as epsilon of the nfa start state

        setAcceptingState(dfaBuilder, this.nfa, orderedStates); // set new accepting state

        Set<String> alphabets = new HashSet<>(nfa.getAlphabets()); // set new alphabets
        alphabets.remove("");// remove the empty string in case it is in the alphabets
        alphabets.remove("epsilon"); // remove epsilon in case it is in the alphabets

        addDFATransitions(dfaBuilder, alphabets, nfa, orderedStates); // draw the new transitions

        return dfaBuilder.getResult();
    }

    /**
     * Helper method of convert
     * Given a set of NFAStates, return the corresponding state index in DFA
     *
     * @param NFASubsetStates a power set of NFA.states
     * @param orderedStates a list of NFAStates of the original NFA
     * @return the corresponding Binary String id of that power set in the DFA
     */
    private int getDFAStateId(Collection<NFAState> NFASubsetStates, List<NFAState> orderedStates){
        StringBuilder id = new StringBuilder();

        for(NFAState state:orderedStates){
            if(NFASubsetStates.contains(state)){
                id.append(1);
            } else{
                id.append(0);
            }
        }

        return Integer.parseInt(id.toString(), 2);
    }

    /**
     * Helper method of convert
     * Given an integer DFAState index, return the corresponding subset of NFAStates
     *
     * @param DFAStateId a binary string of DFAState id
     * @param orderedStates a list of NFAStates of the original NFA
     * @return a subset of nfa.states
     */
    private static Set<NFAState> getNFAStateSubset(int DFAStateId, List<NFAState> orderedStates){
        StringBuilder binaryIndex = new StringBuilder(Integer.toBinaryString(DFAStateId));
        while(binaryIndex.length() != orderedStates.size()){
            binaryIndex.insert(0, 0);
        }

        Set<NFAState> ret = new HashSet<>();
        for(int i = 0; i < binaryIndex.length(); i++){
            if (binaryIndex.charAt(i) == '1'){
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
    private void addSubsetState(DFABuilder dfaBuilder, List<NFAState> orderedStates) {
        int n = orderedStates.size();
        for (int i = 0; i < Math.pow(2, n); i++) {
            dfaBuilder.addNewState();
        }
    }

    /**
     * Helper method of convert
     * Set the accepting state in the resulting DFA after subset construction
     *
     * @param dfaBuilder the dfaBuilder instance
     * @param nfa the nfa instance
     */
    private void setAcceptingState(DFABuilder dfaBuilder, NFA nfa, List<NFAState> orderedStates){
        for (int i = 0; i < Math.pow(2, orderedStates.size()); i++){
            if(isAcceptingState(i, nfa, orderedStates)){
                dfaBuilder.setAcceptingState(i);
            }
        }
    }

    /**
     * Helper method of convert
     * Given an int DFA state index indicating which states in the NFA are present
     * return whether the state is accepting in the DFA
     *
     * @param stateIndex a binary string representing which DFA state is present
     * @param nfa the nfa instance
     * @return whether the state is an accepting state in the DFA
     */
    private boolean isAcceptingState(int stateIndex, NFA nfa, List<NFAState> orderedStates){
        Set<NFAState> nfaStateSet = getNFAStateSubset(stateIndex, orderedStates);
        Set<NFAState> intersection = new HashSet<>(nfaStateSet);
        intersection.removeAll(nfa.getAcceptingStates());

        return !intersection.isEmpty();
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
    private void addDFATransitions(DFABuilder dfaBuilder, Set<String> alphabets,
                                          NFA nfa, List<NFAState> orderedStates){
        for(int i = 0; i < Math.pow(2, orderedStates.size()); i++){
            for(String alphabet: alphabets){
                // get the transition states with a fixed DFA state and alphabet
                int toIndex = getTransitionOut(i, alphabet, nfa, orderedStates);
                dfaBuilder.addTransition(i, alphabet, toIndex);
            }
        }
    }

    /**
     * Helper method of addDFATransitions
     * Return the expected id of the delta function output state with a given DFAState and alphabet
     *
     * @param fromIndex the input DFAState index of the transition
     * @param alphabet the alphabet of the transition
     * @param orderedStates a list of NFAStates of the original NFA
     * @return the id of output state of delta(dfaState, alphabet) transition
     */
    private int getTransitionOut(int fromIndex, String alphabet,
                                           NFA nfa, List<NFAState> orderedStates){
        Set<NFAState> transitToStates = new HashSet<>();
        for (NFAState fromNfaState : getNFAStateSubset(fromIndex, orderedStates)){
            transitToStates.addAll(nfa.transitions(fromNfaState, alphabet));
        }
        return getDFAStateId(transitToStates, orderedStates);
    }
}
