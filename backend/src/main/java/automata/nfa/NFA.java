package automata.nfa;

import automata.FSA;

import java.util.*;

/** Represents a NFA
 * @author Arkaprava Choudhury
 * @author Brian Ho
 */
public class NFA extends FSA<NFAState> {

    /** Represents all states in the NFA.*/
    protected List<NFAState> states;

    /** Represents the start state of the NFA.*/
    protected NFAState startState;

    /** Represents the final state of the NFA.
     * In our implementation, we always have a unique final state using epsilon transition.*/
    protected NFAState endState;

    /** Represents the alphabets which can have a transition to the final state.
     * Any character (or string) not included in this set is not accepted.*/
    protected Set<String> alphabets;

    /** Initializes an empty NFA with no states.
     */
    public NFA(){
        this.states = new ArrayList<>();
        this.startState = null;
        this.endState = null;
        this.alphabets = new HashSet<>();
    }

    /**
     * Gets the start state of the NFA
     * @return the initial state
     */
    @Override
    public NFAState getStartState() {
        return this.startState;
    }

    /**
     * Return the accepting state of the NFA
     * @return the set of accepting states
     */
    @Override
    public Set<NFAState> getAcceptingStates() {
        Set<NFAState> ret = new HashSet<>();
        ret.add(this.endState);
        return ret;
    }

    @Override
    public List<NFAState> getStates() {
        return this.states;
    }

    @Override
    public Set<String> getAlphabets() {
        return this.alphabets;
    }

    /**
     * The transition function of the NFA
     * @param fromState the state where the transition starts
     * @param alphabet the alphabet for the transition
     * @return the set of reached states
     */
    @Override
    public Set<NFAState> delta(NFAState fromState, String alphabet) {
        if(fromState.delta(alphabet) != null){
            return fromState.delta(alphabet);
        } else{
            return new HashSet<>();
        }
    }

    /**
     * Given a certain state, return a set of NFAStates that can be reached by epsilon transitions
     * @param state the given NFAState
     * @return a set of NFAStates that can be reached by epsilon transitions
     */
    public Set<NFAState> epsilon(NFAState state){
        Set<NFAState> reached = new HashSet<>();

        //DFS for the epsilon states
        Stack<NFAState> stack = new Stack<>();
        stack.add(state);
        while (!stack.isEmpty()){
            NFAState top = stack.pop();
            if(!reached.contains(top)){
                Set<NFAState> epsilonStates = delta(top, "epsilon");
                reached.add(top);
                stack.addAll(epsilonStates);
            }
        }
        return reached;
    }

    /**
     * Run the FSA from the given state with a given string (delta* function)
     * @param from start from this state for the transitions
     * @param alphabets a given string to run the FSA
     * @return the reached state(s)
     */
    @Override
    public Set<NFAState> transitions(NFAState from, String alphabets){
        // starts from the epsilon of startState
        Set<NFAState> fromStates = new HashSet<>(epsilon(from));

        // traverse every char in alphabets
        for(int i = 0; i < alphabets.length(); i++){
            Set<NFAState> reached = new HashSet<>();

            // loop over all the characters in the alphabets
            for(NFAState fromState: fromStates) {
                // get all the to-states in the reached set
                Set<NFAState> toStates = delta(fromState, String.valueOf(alphabets.charAt(i)));

                // get all the epsilon of the to-states in the reached set
                for (NFAState toState : toStates) {
                    reached.addAll(epsilon(toState));
                }
            }
            // reached states are the from states of next iteration
            fromStates = reached;
        }
        return fromStates;
    }

    /**
     * Return whether the input string is accepted by the FSA
     * @param alphabets the string to be tested
     * @return whether the input string is accepted by the FSA
     */
    @Override
    public boolean accept(String alphabets){
        return this.transitions(this.startState, alphabets).contains(this.endState);
    }

    /**
     * Get quintuple form
     */
    @Override
    public NFAQuintuple getQuintuple() {
        return new NFAQuintuple(this);
    }

    /**
     * NFA pretty print
     */
    public void prettyPrint(){
        // print state ids
        List<String> statesID = new ArrayList<>();
        for(NFAState state : getStates()){
            statesID.add(String.valueOf(states.indexOf(state)));
        }
        System.out.println("States: " + statesID);

        // print start state id
        System.out.println("Start State: " + states.indexOf(this.startState));

        // print accepting state ID
        List<String> acceptingStateID = new ArrayList<>();
        for(NFAState state : getAcceptingStates()){
            acceptingStateID.add(String.valueOf(states.indexOf(state)));
        }
        System.out.println("Accepting States: " + acceptingStateID);

        // print alphabet
        System.out.println("Alphabets: " + getAlphabets());

        //print transition table
        System.out.println("Transitions: ");
        for(int i = 0; i < this.states.size(); i++){
            System.out.println("From "+i);

            for(String alphabet : this.states.get(i).transitionTable.keySet()){
                List<String> toList = new ArrayList<>();

                for(NFAState state : this.states.get(i).delta(alphabet)){
                    toList.add(String.valueOf(this.states.indexOf(state)));
                }
                System.out.println("\t" + alphabet + ": " + toList);
            }
        }
    }
}
