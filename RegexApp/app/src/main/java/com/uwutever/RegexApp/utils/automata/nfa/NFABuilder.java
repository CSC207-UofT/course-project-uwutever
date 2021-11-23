package com.uwutever.RegexApp.utils.automata.nfa;

import com.uwutever.RegexApp.utils.automata.*;
import com.uwutever.RegexApp.utils.automata.states.DFAState;
import com.uwutever.RegexApp.utils.automata.states.NFAState;
import com.uwutever.RegexApp.utils.errors.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/** Builder class for NFA
 * @author Brian Ho
 */
public class NFABuilder implements FSABuilder {
    private NFA nfa;
    /**
     * Get the NFA from the builder
     */
    public NFA getResult(){
        return this.nfa;
    }

    /**
     * Reset the builder by created a new FSA with the given id
     */
    @Override
    public void reset() {
        this.nfa = new NFA();
    }

    /**
     * Set the start state of the FSA by id
     *
     * @param id the id of the start state
     * @throws UnknownIdException if the given ID is not in the FSA
     */
    @Override
    public void setStartState(String id) {
        if(this.nfa.states.containsKey(id)){
            this.nfa.startState = this.nfa.states.get(id);
        }else {
            throw new UnknownIdException(id);
        }
    }

    /**
     * Set the end state of the NFA by id
     *
     * @param id the id of the end state
     * @throws UnknownIdException if the given ID is not in the NFA
     */
    public void setEndState(String id){
        if(this.nfa.states.containsKey(id)){
            this.nfa.endState = this.nfa.states.get(id);
        }else {
            throw new UnknownIdException(id);
        }
    }

    /**
     * Add a state to the FSA with a given id
     *
     * @param id the id of the state
     * @throws OccupiedIdException if the ID is already occupied in the FSA
     */
    @Override
    public void addState(String id) {
        if(this.nfa.states.containsKey(id)){
            throw new OccupiedIdException(id);
        } else{
            NFAState newState = new NFAState(id, false);
            this.nfa.states.put(id, newState);
            this.nfa.transitionTable.put(id, new HashMap<>());
        }

    }

    /**
     * Add a state to the FSA while indicating whether it is an accepting state
     *
     * @param id          the id of the state
     * @param isAccepting whether the state is an accepting state
     * @throws OccupiedIdException if the ID is already occupied in the FSA
     */
    @Override
    public void addState(String id, boolean isAccepting) {
        if(this.nfa.states.containsKey(id)){
            throw new OccupiedIdException(id);
        } else{
            NFAState newState = new NFAState(id, isAccepting);
            this.nfa.states.put(id, newState);
            this.nfa.transitionTable.put(id, new HashMap<>());
        }
    }

    /**
     * Add a transition to the FSA
     *
     * @param fromId   the ID of the start of the transition
     * @param alphabet the alphabet of the transition
     * @param toId     the ID of the end of the transition
     * @throws UnknownIdException if either fromId or toId is not in the FSA
     * @throws IllegalAlphabetException if the given alphabet is illegal
     */
    @Override
    public void addTransition(String fromId, String alphabet, String toId) {
        if(!this.nfa.states.containsKey(fromId)){
            throw new UnknownIdException(fromId);
        }

        if(!this.nfa.states.containsKey(toId)){
            throw new UnknownIdException(toId);
        }

        if(!alphabet.equals("epsilon") && alphabet.length() > 1){
            throw new IllegalAlphabetException(alphabet);
        }

        if(!alphabet.equals("epsilon")){
            this.nfa.alphabets.add(alphabet);
        }

        NFAState toState = nfa.states.get(toId);

        if(this.nfa.transitionTable.get(fromId).containsKey(alphabet)){
            this.nfa.transitionTable.get(fromId).get(alphabet).add(toState);
        } else{
            Set<NFAState> newToStates = new HashSet<>();
            newToStates.add(toState);
            this.nfa.transitionTable.get(fromId).put(alphabet, newToStates);
        }
    }
}
