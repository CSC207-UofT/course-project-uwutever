package com.terraincognita.automata.dfa;
import com.terraincognita.errors.*;

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
     *
     * @param id the id of the start state
     * @throws UnknownIdException if the given id is not in FSA
     */
    @Override
    public void setStartState(String id) {
        if(this.dfa.states.containsKey(id)){
            this.dfa.startState = this.dfa.states.get(id);
        }else {
            throw new UnknownIdException(id);
        }
    }

    /**
     * Add a state to the DFA with a given id
     * @param id the id of the state
     * @throws OccupiedIdException if the ID is already occupied in the DFA
     */
    @Override
    public void addState(String id) {
        if(this.dfa.states.containsKey(id)){
            throw new OccupiedIdException(id);
        } else{
            DFAState newState = new DFAState(id, false);
            this.dfa.states.put(id, newState);
        }
    }

    /**
     * Add a state to the DFA while indicating whether it is an accepting state
     * @param id the id of the state
     * @param isAccepting whether the state is an accepting state
     * @throws OccupiedIdException if the ID is already occupied in the DFA
     */
    @Override
    public void addState(String id, boolean isAccepting) {
        if(this.dfa.states.containsKey(id)){
            throw new OccupiedIdException(id);
        } else{
            DFAState newState = new DFAState(id, false);
            this.dfa.states.put(id, newState);
        }
    }

    /**
     * Add a transition to the FSA
     * @param fromId the ID of the start of the transition
     * @param alphabet the alphabet of the transition
     * @param toId the ID of the end of the transition
     * @throws UnknownIdException if either fromId or toId is not in the FSA
     */
    @Override
    public void addTransition(String fromId, String alphabet, String toId) {
        if(!this.dfa.states.containsKey(fromId)){
            throw new UnknownIdException(fromId);
        }

        if(!this.dfa.states.containsKey(toId)){
            throw new UnknownIdException(toId);
        }

        DFAState toState = this.dfa.states.get(toId);
        this.dfa.alphabets.add(alphabet);
        this.dfa.transitionTable.get(fromId).put(alphabet, toState);
    }
}
