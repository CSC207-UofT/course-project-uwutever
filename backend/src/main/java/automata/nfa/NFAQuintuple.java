package automata.nfa;

import automata.states.NFAState;

import java.util.*;

public class NFAQuintuple {
    public Set<String> states;
    public Set<String> alphabets;
    public String startState;
    public Set<String> acceptingStates;
    public Map<String, Map<String, String>> transitionTable;

    public NFAQuintuple(NFA nfa){
        List<NFAState> orderedStates = new ArrayList<>(nfa.getStates());
        setStates(orderedStates);
        this.alphabets = nfa.getAlphabets();
        this.startState = String.valueOf(orderedStates.indexOf(nfa.getStartState()));

    }

    public void setStates(List<NFAState> orderedStates) {
        this.states = new HashSet<>();
        for(int i = 0; i < orderedStates.size(); i++){
            this.states.add(String.valueOf(i));
        }
    }

    public void setAcceptingStates(NFA nfa, List<NFAState> orderedStates){
        this.acceptingStates = new HashSet<>();
        for (NFAState state : nfa.getAcceptingStates()){
            this.acceptingStates.add(String.valueOf(orderedStates.indexOf(state)));
        }
    }

    public void setTransitionTable(NFA nfa, List<NFAState> orderedStates){
        //TODO
    }
}
