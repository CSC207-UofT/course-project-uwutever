package controllers;

import compiler.RegexDFAPattern;
import compiler.RegexNFAPattern;
import errors.RegexException;

public class NFAGrapher {
    private final RegexNFAPattern regexPattern;

    public NFAGrapher(String regexString) throws RegexException {
        this.regexPattern = new RegexNFAPattern(regexString);
    }

    public NFAGraphData graph(){
        NFAGraphData ret = new NFAGraphData();
        ret.startState = this.regexPattern.graph().startState;
        ret.acceptingState = this.regexPattern.graph().acceptingStates;
        ret.transitionTable = this.regexPattern.graph().transitionTable;
        return ret;
    }
}
