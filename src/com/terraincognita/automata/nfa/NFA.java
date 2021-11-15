package com.terraincognita.automata.nfa;

import com.terraincognita.automata.FSA;
import com.terraincognita.automata.states.NFAState;
import com.terraincognita.errors.*;

import java.util.*;

public class NFA extends FSA<NFAState> implements NFABuilder {
    protected Set<NFAState> states;
    protected NFAState startState;
    protected NFAState endState;
    protected Set<String> alphabets;
    protected Map<String, Map<String, Set<NFAState>>> transitionTable;
    protected int maxCount;

    public NFA(){
        this.states = new HashSet<>();
        this.startState = null;
        this.endState = null;
        this.transitionTable = new HashMap<>();
    }

    @Override
    public NFAState getStartState() {
        return this.startState;
    }

    @Override
    public Collection<NFAState> getAcceptingStates() {
        Set<NFAState> ret = new HashSet<>();
        for(NFAState state: this.states){
            if(state.isAccepting()){
                ret.add(state);
            }
        }
        return ret;
    }

    @Override
    public Set<NFAState> getStates() {
        return this.states;
    }

    @Override
    public Set<String> getAlphabets() {
        return this.alphabets;
    }

    @Override
    public Set<NFAState> delta(NFAState fromState, String alphabet) {
        if(this.alphabets.contains(alphabet) || alphabet.equals("epsilon")){
            String fromID = fromState.getId();
            return this.transitionTable.get(fromID).get(alphabet);
        } else{
            throw new UnknownAlphabetException(alphabet);
        }
    }

    @Override
    public Set<NFAState> transitions(String alphabets) throws NullStartStateException {
        if(this.startState == null){
            throw new NullStartStateException();
        }

        // starts from the epsilon of startState
        Set<NFAState> fromStates = new HashSet<>(epsilon(this.startState));

        // traverse every char in alphabets
        for(int i = 0; i < alphabets.length(); i++){

            // for each alphabet, loop fromStates set and get all the toStates set
            // also include the epsilon of elements in toStates
            Set<NFAState> reached = new HashSet<>();
            for(NFAState fromState: fromStates){
                Set<NFAState> toStates = delta(fromState, String.valueOf(alphabets.charAt(i)));
                for(NFAState toState : toStates){
                    reached.addAll(epsilon(toState));
                }
            }
            // reached states are the from states of next iteration
            fromStates = reached;
        }

        return fromStates;
    }

    @Override
    public boolean accept(String alphabets) throws NullStartStateException {
        if(this.startState == null){
            throw new NullStartStateException();
        }

        for(NFAState state:transitions(alphabets)){
            if (state.isAccepting()){
                return true;
            }
        }
        return false;
    }

    /**
     * Given a certain state, return a set of NFAStates that can be reached by epsilon transitions
     * @param state the given NFAState
     * @return a set of NFAStates that can be reached by epsilon transitions
     */
    public Set<NFAState> epsilon(NFAState state){
        Set<NFAState> reached = new HashSet<>();

        //DFS search
        Stack<NFAState> stack = new Stack<>();
        stack.add(state);
        while (!stack.isEmpty()){
            NFAState top = stack.pop();
            if(!reached.contains(top)){
                Set<NFAState> epsilonStates = delta(top, "epsilon");
                reached.addAll(epsilonStates);
                stack.addAll(epsilonStates);
            }
        }
        reached.add(state);
        return reached;
    }

    @Override
    public void reset() {
        this.states = new HashSet<>();
        this.startState = null;
        this.endState = null;
        this.transitionTable = new HashMap<>();
    }

    public void setStartState(NFAState state) {
        if(!this.states.contains(state)){
            throw new InvalidStateException(state);
        }

        this.startState = state;
    }

    public void addState(NFAState state, String id) {
        if(this.states.contains(state)){
            throw new InvalidStateException(state);
        }
        this.states.add(new NFAState(id, false));
    }

    public void addState(NFAState state, String id, boolean isAccepting) {
        this.states.add(state);
    }

    public void addTransition(NFAState fromState, String alphabet, NFAState toState) {
        if (!this.states.contains(fromState) || !this.states.contains(toState)){
            throw new InvalidStateException("Sta");
        }

        // if the alphabet is in the transitionTable, add the to state into the value list
        // else put (alphabet, [to-state]) pair into the table
        if(fromState.stateTransitions.containsKey(alphabet)){
            fromState.stateTransitions.get(alphabet).add(toState);
        } else{
            Set<NFAState> transitionSet = new HashSet<>();
            transitionSet.add(toState);
            fromState.stateTransitions.put(alphabet, transitionSet);
        }
    }

    @Override
    public void setStartState(String id) {

    }

    @Override
    public void addState(String id) {
        this.states.add(new NFAState(id, false));
    }

    @Override
    public void addState(String id, boolean isAccepting) {
        this.states.add(new NFAState(id, false));
    }

    @Override
    public void addTransition(String from, String alphabet, String to) {

    }

    public int getMaxCount() {
        return maxCount;
    }
}
