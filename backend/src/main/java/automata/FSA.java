package automata;

import automata.states.FSAState;

import java.util.Collection;

/** Abstract class representing an FSA
 * @author (Brian) Ho
 * @author Arkaprava Choudhury
 */
public abstract class FSA <T extends FSAState>{
    /**
     * Return the states of the FSA
     */
    public abstract Collection<T> getStates();

    /**
     * Return the set of alphabets of the FSA
     */
    public abstract Collection<String> getAlphabets();

    /**
     * Return the start state of the FSA
     */
    public abstract T getStartState();

    /**
     * Return the accepting states of the FSA
     */
    public abstract Collection<T> getAcceptingStates();

    /**
     * The delta function of the FSA
     * Transition function of a given state and alphabet
     * For unknown alphabet,
     *  - return empty set for NFA
     *  - return the dead state for DFA
     *
     * @param fromState the state where the transition starts
     * @param alphabet the alphabet for the transition
     */
    public abstract Object delta(T fromState, String alphabet);

    /**
     * Run the FSA from the given state with a given string (delta* function)
     * @param fromState start from this state for the transitions
     * @param alphabets a given string to run the FSA
     * @return the reached state(s)
     */
    public abstract Object transitions(T fromState, String alphabets);

    /**
     * Return whether the input string is accepted by the FSA
     * @param alphabets the string to be tested
     * @return whether the input string is accepted by the FSA
     */
    public abstract boolean accept(String alphabets);

}
