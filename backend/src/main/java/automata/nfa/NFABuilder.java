package automata.nfa;

import automata.FSABuilder;
import errors.UnknownStateIndexException;

/** Builder class for NFA
 * @author Brian Ho
 */
public class NFABuilder implements FSABuilder {
    private NFA nfa;

    /**
     * Get the NFA from the builder
     */
    public NFA getResult() {
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
     * Set the start state of the FSA by index
     *
     * @param index the id of the start state
     */
    @Override
    public void setStartState(int index) {
        if(index < 0 || index >= this.nfa.states.size()) {
            throw new UnknownStateIndexException(index);
        }
            this.nfa.startState = this.nfa.states.get(index);
    }

    /**
     * Set a state in the FSA to be accepting
     * In our implementation, the NFA has only ONE accepting state
     * <p>
     * To represent an NFA with multiple accepting states, use epsilon transition to transition
     * all the accepting states to one accepting state.
     *
     * @param index the index of the state
     */
    @Override
    public void setAcceptingState(int index) {
        if(index < 0 || index >= this.nfa.states.size()) {
            throw new UnknownStateIndexException(index);
        }
        this.nfa.endState = this.nfa.states.get(index);
    }

    /**
     * Add a new state to the FSA
     * @return a map with one key-value pair. The key is the index and the value is the state object
     */
    @Override
    public int addNewState() {
        NFAState newState = new NFAState();
        this.nfa.states.add(newState);

        return this.nfa.states.indexOf(newState);
    }

    /**
     * Add a transition to the FSA
     * Add the alphabet to the alphabet set if the alphabet is new
     *
     * @param fromIndex the ID of the start of the transition
     * @param alphabet  the alphabet of the transition
     * @param toIndex   the ID of the end of the transition
     */
    @Override
    public void addTransition(int fromIndex, String alphabet, int toIndex) {
        if(fromIndex < 0 || fromIndex >= this.nfa.states.size()) {
            throw new UnknownStateIndexException(fromIndex);
        }
        if(toIndex < 0 || toIndex >= this.nfa.states.size()) {
            throw new UnknownStateIndexException(toIndex);
        }

        this.nfa.alphabets.add(alphabet);
        NFAState fromState = this.nfa.states.get(fromIndex);
        NFAState toState = this.nfa.states.get(toIndex);
        fromState.addTransition(alphabet, toState);
    }

    /**
     * Overload addTransition method
     * Allow adding transition from the given index state to another NFA
     *
     * Add the states of the NFA to this.nfa state list if it is not present
     * The transition relationship of the NFA should remain unchanged
     *
     * @param fromIndex the given index for the transition
     * @param alphabet the alphabet for the transition
     * @param addNFA the NFA to be added in this.NFA
     */
    public void addTransition(int fromIndex, String alphabet, NFA addNFA){
        if(fromIndex < 0 || fromIndex >= this.nfa.states.size()) {
            throw new UnknownStateIndexException(fromIndex);
        }
        this.nfa.alphabets.add(alphabet);
        this.nfa.alphabets.addAll(addNFA.alphabets);

        NFAState fromState = this.nfa.states.get(fromIndex);

        // add the states of addNFA to this nfa if not present
        for(NFAState state: addNFA.states){
            if (!this.nfa.states.contains(state)){
                this.nfa.states.add(state);
            }
        }

        fromState.addTransition(alphabet, addNFA.startState);
    }

    /**
     * Overload addTransition method
     * Allow adding transition from the given NFA to another index state
     *
     * Add the states of the NFA to this.nfa state list if it is not present
     * The transition relationship of the NFA should remain unchanged
     *
     * @param toIndex the index of the end state of the transition
     * @param alphabet the alphabet for the transition
     * @param addNFA the NFA to be added in this.NFA
     */
    public void addTransition(NFA addNFA, String alphabet, int toIndex){
        if(toIndex < 0 || toIndex >= this.nfa.states.size()) {
            throw new UnknownStateIndexException(toIndex);
        }
        this.nfa.alphabets.add(alphabet);
        this.nfa.alphabets.addAll(addNFA.alphabets);

        NFAState toState = this.nfa.states.get(toIndex);

        // add the states of addNFA to this nfa if not present
        for(NFAState state: addNFA.states){
            if (!this.nfa.states.contains(state)){
                this.nfa.states.add(state);
            }
        }

        addNFA.endState.addTransition(alphabet, toState);
    }

    /**
     * Overload addTransition method
     * Allow adding transition from the given NFA to another NFA
     *
     * Add the states of the NFA to this.nfa state list if it is not present
     * The transition relationship of the NFA should remain unchanged
     *
     * @param addNFA1 the first NFA to be added in this.nfa
     * @param alphabet the alphabet for the transition
     * @param addNFA2 the second NFA to be added in this.NFA
     */
    public void addTransition(NFA addNFA1, String alphabet, NFA addNFA2){
        this.nfa.alphabets.add(alphabet);
        this.nfa.alphabets.addAll(addNFA1.alphabets);
        this.nfa.alphabets.addAll(addNFA2.alphabets);

        // add the states of addNFA1 to this nfa if not present
        for(NFAState state: addNFA1.states){
            if (!this.nfa.states.contains(state)){
                this.nfa.states.add(state);
            }
        }

        // add the states of addNFA2 to this nfa if not present
        for(NFAState state: addNFA2.states){
            if (!this.nfa.states.contains(state)){
                this.nfa.states.add(state);
            }
        }

        addNFA1.endState.addTransition(alphabet, addNFA2.startState);
    }

}