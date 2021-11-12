package com.terraincognita.automata;

import com.terraincognita.automata.states.DFAState;

public class DFABuilder implements FSABuilder{
    private DFA dfa;

    /**
     * Reset the builder by created a new FSA with the given id
     *
     * @param id the id of the new DFA
     */
    @Override
    public void reset(String id) {
        this.dfa = new DFA(id);
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
     * Set the state with the given id to be accepting
     * Return true if the state is set accepting successfully, return false otherwise
     *
     * @return true if changes are made successfully
     */
    public boolean setAcceptingState(String id){
        for(DFAState state: this.dfa.getStates()){
            if(state.getId().equals(id)){
                state.setAccepting(true);
                return true;
            }
        }
        return false;
    }

    /**
     * Add a state to the FSA with an auto id
     * id format: DFA Id + DFA.states.size()
     */
    public void addState(){
        String newId = this.dfa.getId() + this.dfa.states.size();
        DFAState newState = new DFAState(this.dfa.getId()+this.dfa.states.size(),false);
        this.dfa.states.put(newId, newState);
    }

    /**
     * Add a state to the FSA with an auto id
     * id format: DFA Id + DFA.states.size()
     * @param isAccepting whether the new state is an accepting state
     */
    public void addState(boolean isAccepting){
        String newId = this.dfa.getId()+this.dfa.states.size();
        DFAState newState = new DFAState(newId,isAccepting);
        this.dfa.states.put(newId, newState);
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
     * @param from the id of the start state of the transition
     * @param alphabet the alphabet of the transition
     * @param to the id of the end state of the transition
     */
    @Override
    public void addTransition(String from, String alphabet, String to) {
        DFAState toState = this.dfa.states.get(to);
        this.dfa.transitionTable.get(from).put(alphabet, toState);
    }
}
