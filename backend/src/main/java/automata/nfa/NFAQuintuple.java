package automata.nfa;

import java.util.*;

public class NFAQuintuple {
    public Set<String> states;
    public Set<String> alphabets;
    public String startState;
    public Set<String> acceptingStates;
    public Map<String, Map<String, Set<String>>> transitionTable;

    public NFAQuintuple(NFA nfa){
        setStates(nfa);
        this.alphabets = nfa.getAlphabets();
        this.startState = String.valueOf(nfa.states.indexOf(nfa.getStartState()));
        setAcceptingStates(nfa);
        setTransitionTable(nfa);
    }

    public void setStates(NFA nfa) {
        this.states = new HashSet<>();
        for(int i = 0; i < nfa.states.size(); i++){
            this.states.add(String.valueOf(i));
        }
    }

    public void setAcceptingStates(NFA nfa){
        this.acceptingStates = new HashSet<>();
        for (NFAState state : nfa.getAcceptingStates()){
            this.acceptingStates.add(String.valueOf(nfa.states.indexOf(state)));
        }
    }

    public void setTransitionTable(NFA nfa){
        this.transitionTable = new HashMap<>();

        // loop over all the nfa state
        for(NFAState fromState : nfa.getStates()){
            int fromStateIndex = nfa.states.indexOf(fromState);
            // create a specific transition table for one state
            this.transitionTable.put(String.valueOf(fromStateIndex), new HashMap<>());
            Map<String, Set<String >> fromStateTransitionTable = this.transitionTable.get(
                    String.valueOf(fromStateIndex));

            // loop over all the alphabets in the state transition table and convert it into index style
            for(String alphabet : fromState.transitionTable.keySet()){
                fromStateTransitionTable.put(alphabet, new HashSet<>());
                Set<String> toSet = fromStateTransitionTable.get(alphabet);
                for (NFAState toState : fromState.delta(alphabet)){
                    toSet.add(String.valueOf(nfa.states.indexOf(toState)));
                }
            }
        }
    }
}
