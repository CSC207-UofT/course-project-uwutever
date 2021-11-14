package com.terraincognita.automata.dfa;


import com.terraincognita.automata.FSA;
import com.terraincognita.automata.states.FSAState;
//
import java.util.Collection;
//
//public class DFA extends FSA {
//=======
import com.terraincognita.automata.states.DFAState;

import java.util.*;

public class DFA extends FSA<DFAState> {
    private final String id;

    protected Map<String, DFAState> states;
    protected DFAState startState;
    protected Map<String, Map<String, DFAState>> transitionTable;
    /**
     * Constructor DFA
     * @param id the id of this DFA
     */
    public DFA(String id){
        this.id = id;
        this.states = new HashMap<>();
        this.startState = null;
        this.transitionTable = new HashMap<>();
    }

    /**
     * Getter method of id
     */
    public String getId(){
        return this.id;
    }

//>>>>>>> 9b195620092c18eef28c7fe46d85a5e7860bd80c:src/com/terraincognita/automata/DFA.java
    /**
     * Return the start state of the FSA
     */
    @Override
    public DFAState getStartState() {
        return this.startState;
    }

    /**
     * Return the set of final states of the FSA
     */
    @Override
    public Set<DFAState> getAcceptingStates() {
        Set<DFAState> acceptingStates = new HashSet<>();

        for(DFAState state: this.states.values()){
            if(state.isAccepting()){
                acceptingStates.add(state);
            }
        }
        return acceptingStates;
    }

    /**
     * Return the set of states of the FSA
     */
    @Override
    public Collection<DFAState> getStates() {
        return this.states.values();
    }

    /**
     * The delta function of the FSA
     * Transition function of a given state and alphabet
     *
     * @param fromState the state where the transition starts
     * @param alphabet  the alphabet for the transition
     */
    @Override
    public DFAState delta(DFAState fromState, String alphabet) {
        String fromStateId = fromState.getId();
        return this.transitionTable.get(fromStateId).get(alphabet);
    }

    /**
     * Run the FSA from the start state with a given string (delta* function)
     *
     * @param alphabets a given string to run the FSA
     * @return the reached state(s)
     */
    @Override
    public DFAState transitions(String alphabets) {
        DFAState curr = this.startState;
        for(int i = 0; i < alphabets.length(); i++){
            curr = delta(curr, String.valueOf(alphabets.charAt(i)));
        }
        return curr;
    }

    /**
     * Return whether the input string is accepted by the FSA
     *
     * @param alphabets the string to be tested
     * @return whether the input string is accepted by the FSA
     */
    @Override
    public boolean accept(String alphabets) {
        return transitions(alphabets).isAccepting();
    }
}
