package automata.dfa;

import java.util.*;

public class DFAQuintuple {
    public Set<String> states;
    public Set<String> alphabets;
    public String startState;
    public Set<String> acceptingStates;
    public Map<String, Map<String, String>> transitionTable;

    public DFAQuintuple(DFA dfa){
        setStates(dfa);
        this.alphabets = dfa.getAlphabets();
        this.startState = String.valueOf(dfa.states.indexOf(dfa.getStartState()));
        setAcceptingStates(dfa);
        setTransitionTable(dfa);
    }

    public void setStates(DFA dfa) {
        this.states = new HashSet<>();
        for(int i = 0; i < dfa.states.size(); i++){
            this.states.add(String.valueOf(i));
        }
    }

    public void setAcceptingStates(DFA dfa){
        this.acceptingStates = new HashSet<>();
        for (DFAState state : dfa.getAcceptingStates()){
            this.acceptingStates.add(String.valueOf(dfa.states.indexOf(state)));
        }
    }

    public void setTransitionTable(DFA dfa){
        this.transitionTable = new HashMap<>();

        // loop over all the dfa state
        for(DFAState fromState : dfa.getStates()){
            int fromStateIndex = dfa.states.indexOf(fromState);
            // create a specific transition table for one state
            this.transitionTable.put(String.valueOf(fromStateIndex), new HashMap<>());
            Map<String, String> fromStateTransitionTable = this.transitionTable.get(String.valueOf(fromStateIndex));

            // loop over all the alphabets in the state transition table and convert it into index style
            for(String alphabet : fromState.transitionTable.keySet()){
                fromStateTransitionTable.put(alphabet, String.valueOf(dfa.states.indexOf(fromState.delta(alphabet))));
            }
        }
    }
}
