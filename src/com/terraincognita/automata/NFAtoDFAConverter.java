package com.terraincognita.automata;

import com.terraincognita.automata.states.NFAState;
import com.terraincognita.automata.nfa.NFA;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NFAtoDFAConverter {
    private NFA nfa;

    /**
     * Constructor of the converter
     * @param nfa the NFA to be converted into DFA
     */
    public NFAtoDFAConverter(NFA nfa){
        this.nfa = nfa;
    }

    /**
     * Setter method of the converter
     * @param nfa the NFA to be converted into DFA
     */
    public void setNfa(NFA nfa){
        this.nfa = nfa;
    }

    /**
     * Return the equivalent DFA of this.nfa using power set construction
     * 1. Get an ordered list of states of the NFA
     * 1. Add a state for every subset of the NFA
     * 2. Draw the transition between the subsets
     * 3. Make all states which contains the accepting states of the nfa be accepting state
     * 3. Delete states which are not used
     *
     * @param id of the resulting DFA
     * @return an equivalent DFA
     */
    public DFA convert(String id){
        List<NFAState> orderedStates = new ArrayList<>(this.nfa.getStates());

        // use DFABuilder to construct the new DFA
        DFABuilder dfaBuilder = new DFABuilder();
        dfaBuilder.reset(id);

        addSubsetState(dfaBuilder);

        dfaBuilder.setStartState(DFAStateId(this.nfa.epsilon(this.nfa.getStartState()), orderedStates, id));
    }

    /**
     * Helper method of convert
     * Add state for every subset of the nfa.
     * For an NFA containing n states, it has 2^n subsets
     * @param dfaBuilder the builder instance in convert
     */
    private void addSubsetState(DFABuilder dfaBuilder){
        int n = this.nfa.getStates().size();
        for(int i=0; i<Math.pow(2,n); i++){
            dfaBuilder.addState();
        }
    }

    /**
     * Helper method of convert
     * Given a subset of the nfa state, return the corresponding state id in DFA
     * The status of each NFA state subset is represented as a binary string
     * For instance NFA has states [a,b,c]. For the subset {a,b}, the corresponding string is '110'
     * The corresponding state id in DFA is DFA ID + subset status string represented in decimal
     *
     * @param NFAsubset a subset of NFA states
     * @param orderedStateList an ordered NFA state list
     * @param dfaId the id of the DFA
     * @return the id of the corresponding DFA state
     */
    private String DFAStateId(Collection<NFAState> NFAsubset, List<NFAState> orderedStateList, String dfaId){
        StringBuilder ret = new StringBuilder();

        for(NFAState state: orderedStateList){
            if(NFAsubset.contains(state)){
                ret.append(1);
            } else{
                ret.append(0);
            }
        }

        int decimalRet = Integer.parseInt(ret.toString(),2);
        return dfaId + decimalRet;
    }
}
