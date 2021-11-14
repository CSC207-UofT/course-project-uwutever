package com.terraincognita.automata.dfa;

import com.terraincognita.automata.FSABuilder;
import com.terraincognita.automata.states.DFAState;

public class DFABuilder implements FSABuilder {
    private DFA dfa;

    /**
     * Return the DFA in the builder
     */
    public DFA getResult(){
        return this.dfa;
    }

    /**
     * Reset the builder by created a new FSA with the given id
     *
     */
    @Override
    public void reset() {
        this.dfa = new DFA();
    }

    /**
     * Set the start state of the FSA by id.
     * Return true if id is set successfully, return false otherwise
     *
     * @param id the id of the start state
     * @return true if id is set successfully
     */
    @Override
    public boolean setStartState(String id) {
        for(DFAState state: this.dfa.getStates()){
            if(state.getId().equals(id)){
                this.dfa.startState = state;
                return true;
            }
        }
        return false;
    }

    /**
     * Add a non-accepting state to the FSA with a given id
     *
     * @param id the id of the state
     */
    @Override
    public void addState(String id) {
        DFAState newState = new DFAState(id, false);
        this.dfa.states.put(id, newState);
    }

    /**
     * Add a state to the FSA while indicating whether it is an accepting state
     *
     * @param id          the id of the state
     * @param isAccepting whether the state is an accepting state
     */
    @Override
    public void addState(String id, boolean isAccepting) {
        DFAState newState = new DFAState(id, false);
        this.dfa.states.put(id, newState);
    }

    /**
     * Add a transition to the FSA
     *
     * @param fromId the id of the start state of the transition
     * @param alphabet the alphabet of the transition
     * @param toId the id of the end state of the transition
     */
    @Override
    public void addTransition(String fromId, String alphabet, String toId) {
        DFAState toState = this.dfa.states.get(toId);
        this.dfa.alphabets.add(alphabet);
        this.dfa.transitionTable.get(fromId).put(alphabet, toState);
    }
}
