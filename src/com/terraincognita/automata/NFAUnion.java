package com.terraincognita.automata;

import com.terraincognita.automata.states.NFAState;

import java.util.*;

public class NFAUnion implements NFABuilder{
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
     * @param state the start state
     * @throws IllegalArgumentException
     */
    //TODO create an exception for id not in states
    public void setStartState(NFAState state) {
        if(!this.nfa.states.contains(state)){
            //TODO Throw exception
            //Temporarily: using IllegalArgumentException
            throw new IllegalArgumentException("Invalid id for state.");
        }

        this.nfa.startState = state;
    }

    /**
     * Add a state to the FSA with a given id
     *
     * @param state the id of the state
     * @throws IllegalArgumentException
     */
    @Override
    public void addState(NFAState state, String id) {
        if(this.nfa.states.contains(state)){
            //TODO throw exception
            //Temporarily: using IllegalArgumentException
            throw new IllegalArgumentException("Invalid id for state.");
        }
        this.nfa.states.add(new NFAState(id, false));
    }

    /**
     * Add a state to the FSA while indicating whether it is an accepting state
     *
     * @param state       the state
     * @param id          the id of the state
     * @param isAccepting whether the state is an accepting state
     */
    @Override
    public void addState(NFAState state, String id, boolean isAccepting) {
        this.nfa.states.add(state);
    }

    /**
     * Add a transition to the FSA
     *
     * @param fromState the from-state of the transition
     * @param alphabet the alphabet of the transition
     * @param toState the to-state of the transition
     */
    @Override
    public void addTransition(NFAState fromState, String alphabet, NFAState toState) {
        if (!this.nfa.states.contains(fromState) || !this.nfa.states.contains(toState)){
            //TODO throw exception
        }

        // if the alphabet is in the transitionTable, add the to state into the value list
        // else put (alphabet, [to-state]) pair into the table
        if(fromState.stateTransitions.containsKey(alphabet)){
            fromState.stateTransitions.get(alphabet).add(toState);
        } else{
            Set<NFAState> transitionSet = new HashSet<NFAState>();
            transitionSet.add(toState);
            fromState.stateTransitions.put(alphabet, transitionSet);
        }
    }

    /**
     * Return the NFA from builder
     */
    public NFA getResult(){
        return this.nfa;
    }
}
