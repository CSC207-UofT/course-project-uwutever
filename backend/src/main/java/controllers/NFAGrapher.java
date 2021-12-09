package controllers;

import compiler.RegexDFAPattern;
import compiler.RegexNFAPattern;
import errors.RegexException;

public class NFAGrapher {
    private final RegexNFAPattern regexPattern;

    /**
     * Create a NFAGrapher for graphing the NFA
     * @param regexString the regular expression corresponding to the NFA to be graphed
     * @throws RegexException
     */
    public NFAGrapher(String regexString) throws RegexException {
        this.regexPattern = new RegexNFAPattern(regexString);
    }

    /**
     * Create a graph representation of the NFA
     * @return A NFA representation of type NFAGraphData
     */
    public NFAGraphData graph(){
        NFAGraphData ret = new NFAGraphData();
        ret.startState = this.regexPattern.graph().startState;
        ret.acceptingState = this.regexPattern.graph().acceptingStates;
        ret.transitionTable = this.regexPattern.graph().transitionTable;
        return ret;
    }
}
