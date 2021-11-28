package controllers;

import automata.dfa.DFA;
import automata.dfa.DFAQuintuple;
import automata.nfa.NFA;
import automata.nfa.NFAQuintuple;
import compiler.RegexPattern;

import java.util.List;

public class FSAInfoController {
    private final RegexPattern regexPattern;

    public FSAInfoController(RegexPattern regexPattern){
        this.regexPattern = regexPattern;
    }

    public Object graph() {
        if (this.regexPattern.getFSAType().equals("NFA")) {
            return new NFAQuintuple((NFA) this.regexPattern.getFsa());
        }
        if(this.regexPattern.getFSAType().equals("DFA")){
            return new DFAQuintuple((DFA) this.regexPattern.getFsa());
        }
        else{
            throw new IllegalArgumentException("Unknown FSA type");
        }
    }
}
