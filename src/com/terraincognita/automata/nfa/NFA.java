package com.terraincognita.automata.nfa;

import com.terraincognita.automata.FSA;
import com.terraincognita.automata.states.NFAState;

import java.util.*;

public class NFA extends FSA<NFAState> implements NFABuilder {
    protected Set<NFAState> states;
    protected NFAState startState;
    protected NFAState endState;
    protected Map<String, Map<String, List<NFAState>>> transitionTable;

    public NFA(){
        this.states = new HashSet<>();
        this.startState = null;
        this.endState = null;
        this.transitionTable = new HashMap<>();
    }

    /**
     * Return the start state of the FSA
     */
    @Override
    public NFAState getStartState() {
        return this.startState;
    }

    @Override
    public Collection<NFAState> getAcceptingStates() {
        return null;
    }

    @Override
    public Collection<NFAState> getStates() {
        return null;
    }

    @Override
    public Object delta(NFAState fromState, String alphabet) {
        return null;
    }

    /**
     * Run the FSA from the start state with a given string
     *
     * @param alphabets a given string to run the FSA
     * @return the reached state(s)
     */
    @Override
    public List<NFAState> transitions(String alphabets) {
        //TODO
        return null;    }

    /**
     * Return whether the input string is accepted by the FSA
     *
     * @param alphabets the string to be tested
     * @return whether the input string is accepted by the FSA
     */
    @Override
    public boolean accept(String alphabets) {
        for(NFAState state:transitions(alphabets)){
            if (state.isAccepting()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void reset() {
        this.states = new HashSet<>();
        this.startState = null;
        this.transitionTable = new HashMap<>();
    }

    /**
     * Set the start state of the FSA by index
     *
     * @param state the start state
     * @throws IllegalArgumentException
     */
    //TODO create an exception for id not in states
    public void setStartState(NFAState state) {
        if(!this.states.contains(state)){
            //TODO Throw exception
            //Temporarily: using IllegalArgumentException
            throw new IllegalArgumentException("Invalid id for state.");
        }

        this.startState = state;
    }

    /**
     * Add a state to the FSA with a given id
     *
     * @param state the id of the state
     * @throws IllegalArgumentException
     */
    public void addState(NFAState state, String id) {
        if(this.states.contains(state)){
            //TODO throw exception
            //Temporarily: using IllegalArgumentException
            throw new IllegalArgumentException("Invalid id for state.");
        }
        this.states.add(new NFAState(id, false));
    }

    /**
     * Add a state to the FSA while indicating whether it is an accepting state
     *
     * @param state       the state
     * @param id          the id of the state
     * @param isAccepting whether the state is an accepting state
     */
    public void addState(NFAState state, String id, boolean isAccepting) {
        this.states.add(state);
    }

    /**
     * Add a transition to the FSA
     *
     * @param fromState the from-state of the transition
     * @param alphabet the alphabet of the transition
     * @param toState the to-state of the transition
     */
    public void addTransition(NFAState fromState, String alphabet, NFAState toState) {
        if (!this.states.contains(fromState) || !this.states.contains(toState)){
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

    @Override
    public void reset(String id) {

    }

    @Override
    public boolean setStartState(String id) {
        return false;
    }

    @Override
    public void addState(String id) {

    }

    @Override
    public void addState(String id, boolean isAccepting) {

    }

    @Override
    public void addTransition(String from, String alphabet, String to) {

    }
}
