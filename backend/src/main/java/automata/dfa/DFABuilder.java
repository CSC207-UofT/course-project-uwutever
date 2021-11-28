package automata.dfa;
import automata.FSABuilder;
import automata.states.DFAState;

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
     * Set the start state of the FSA by index
     *
     * @param index the id of the start state
     */
    @Override
    public void setStartState(int index) {
        this.dfa.startState = this.dfa.states.get(index);
    }

    /**
     * Set a state in the FSA to be accepting
     *
     * @param index the index of the state
     */
    @Override
    public void setAcceptingState(int index) {
        this.dfa.acceptingStates.add(this.dfa.states.get(index));
    }

    /**
     * Add a new state to the FSA
     */
    @Override
    public int addNewState() {
        DFAState newState = new DFAState();
        this.dfa.states.add(newState);
        return this.dfa.states.indexOf(newState);
    }

    /**
     * Add a transition to the FSA
     * Add the alphabet to the alphabet set if the alphabet is new
     *
     * @param fromIndex the index of the start of the transition
     * @param alphabet  the alphabet of the transition
     * @param toIndex   the index of the end of the transition
     */
    @Override
    public void addTransition(int fromIndex, String alphabet, int toIndex) {
        DFAState fromState = this.dfa.states.get(fromIndex);
        DFAState toState = this.dfa.states.get(toIndex);
        fromState.addTransition(alphabet, toState);
    }



}
