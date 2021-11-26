package automata.nfa;

import automata.FSA;
import automata.states.NFAState;
import errors.*;

import java.util.*;

/** Represents a NFA
 * @author Arkaprava Choudhury
 * @author Brian Ho
 */
public class NFA extends FSA<NFAState> {

    /** Represents the set of all states in the NFA.
     */
    protected Map<String, NFAState> states;

    /** Represents the start state of the NFA.
     */
    protected NFAState startState;

    /** Represents the final state of the NFA.
     * In our implementation, we always have a unique final state.
     */
    protected NFAState endState;

    /** Represents the alphabets which can have a transition to the final state.
     * Any character (or string) not included in this set is not accepted.
     */
    protected Set<String> alphabets;

    /** Represents all the transitions in the NFA.
     */
    protected Map<String, Map<String, Set<NFAState>>> transitionTable;

    /** Represents the maximum among all IDs of states in the NFA.
     */
    protected int maxCount;

    /** Initializes an empty NFA with no states.
     */
    public NFA(){
        this.states = new HashMap<>();
        this.startState = null;
        this.endState = null;
        this.alphabets = new HashSet<>();
        this.transitionTable = new HashMap<>();
    }

    /** Gets the start state of the NFA
     * @return the initial state
     */
    @Override
    public NFAState getStartState() {
        return this.startState;
    }

    @Override
    public List<NFAState> getAcceptingStates() {
        List<NFAState> ret = new ArrayList<>();
        for(NFAState state: this.states.values()){
            if(state.isAccepting()){
                ret.add(state);
            }
        }
        return ret;
    }

    @Override
    public Collection<NFAState> getStates() {
        return this.states.values();
    }

    @Override
    public Collection<String> getStatesID() {
        return this.states.keySet();
    }

    @Override
    public Set<String> getAlphabets() {
        return this.alphabets;
    }

    /**
     *
     * @param fromState the state where the transition starts
     * @param alphabet the alphabet for the transition
     * @return the set of possible
     */
    @Override
    public Set<NFAState> delta(NFAState fromState, String alphabet) {
        String fromID = fromState.getId();
        if (this.transitionTable.get(fromID).containsKey(alphabet)) {
            return this.transitionTable.get(fromID).get(alphabet);
        } else {
            // return an empty set if there is no transitions for this alphabet
            return new HashSet<>();
        }
    }

    @Override
    public Set<NFAState> transitions(NFAState from, String alphabets){
        // starts from the epsilon of startState
        Set<NFAState> fromStates = new HashSet<>(epsilon(from));

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

    /**
     * Getter method of maxCount
     * @return integer maxCount
     */
    public int getMaxCount(){
        return this.maxCount;
    }

    /**
     * Setter method of maxCount
     * @param maxCount integer maxCount
     */
    public void setMaxCount(int maxCount){
        this.maxCount = maxCount;
    }

    @Override
    public boolean accept(String alphabets){
        for(NFAState state:transitions(this.startState, alphabets)){
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

        //DFS
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
     * NFA pretty print
     */
    public void prettyPrint(){
        // print state ids
        List<String> statesID = new ArrayList<>();
        for(NFAState state : getStates()){
            statesID.add(state.getId());
        }
        System.out.println("States: " + statesID);

        // print start state id
        System.out.println("Start State: " + getStartState().getId());

        // print accepting state ID
        List<String> acceptingStateID = new ArrayList<>();
        for(NFAState state : getAcceptingStates()){
            acceptingStateID.add(state.getId());
        }
        System.out.println("Accepting States: " + acceptingStateID);

        // print alphabet
        System.out.println("Alphabets: " + getAlphabets());

        //print transition table
        System.out.println("Transitions: ");
        for(String fromID : this.transitionTable.keySet()){
            System.out.println("From "+fromID);

            for(String alphabet : this.transitionTable.get(fromID).keySet()){
                List<String> toList = new ArrayList<>();
                for(NFAState toState : this.transitionTable.get(fromID).get(alphabet)){
                    toList.add(toState.getId());
                }
                System.out.println("\t" + alphabet + ": " + toList);
            }
        }
    }
}
