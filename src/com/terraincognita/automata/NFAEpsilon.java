package com.terraincognita.automata;

import com.terraincognita.automata.states.NFAState;

import java.util.*;

public class NFAEpsilon implements FSABuilder{
    private NFA nfa;

    /**
     * Reset the builder
     */
    @Override
    public void reset() {
        this.nfa = new NFA();
    }

    /**
     * Set the start state of the FSA by index
     *
     * @param id the id of the start state
     * @throws IllegalArgumentException
     */
    //TODO create an exception for id not in states
    @Override
    public void setStartState(String id) {
        if(!this.nfa.states.containsKey(id)){
            //TODO Throw exception
            //Temporarily: using IllegalArgumentException
            throw new IllegalArgumentException("Invalid id for state.");
        }

        this.nfa.startState = this.nfa.states.get(id);
    }

    /**
     * Add a state to the FSA with a given id
     *
     * @param id the id of the state
     * @throws IllegalArgumentException
     */
    @Override
    public void addState(String id) {
        if(this.nfa.states.containsKey(id)){
            //TODO throw exception
            //Temporarily: using IllegalArgumentException
            throw new IllegalArgumentException("Invalid id for state.");
        }
        this.nfa.states.put(id, new NFAState(id, false));
    }

    /**
     * Add a state to the FSA while indicating whether it is an accepting state
     *
     * @param id          the id of the state
     * @param isAccepting whether the state is an accepting state
     */
    @Override
    public void addState(String  id, boolean isAccepting) {
        this.nfa.states.put(id, new NFAState(id, isAccepting));
    }

    /**
     * Add a transition to the FSA
     *
     * @param fromId the id of the from-state of the transition
     * @param alphabet the alphabet of the transition
     * @param toId the id of the to-state of the transition
     */
    @Override
    public void addTransition(String fromId, String alphabet, String toId) {
        if(!this.nfa.states.containsKey(fromId) || !this.nfa.states.containsKey(toId)){
            //TODO throw exception
        }

        // if the alphabet is in the transitionTable, add the to state into the value list
        // else put (alphabet, [to-state]) pair into the table
        if(this.nfa.transitionTable.get(fromId).containsKey(alphabet)){
            NFAState toState = this.nfa.states.get(toId);
            this.nfa.transitionTable.get(fromId).get(alphabet).add(toState);
        } else{
            List<NFAState> transitionList = new ArrayList<>();
            transitionList.add(this.nfa.states.get(toId));
            this.nfa.transitionTable.get(fromId).put(alphabet, transitionList);
        }
    }

    /**
     * Return the NFA from builder
     */
    public NFA getResult(){
        return this.nfa;
    }
}
