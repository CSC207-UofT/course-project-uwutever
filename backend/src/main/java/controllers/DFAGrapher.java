package controllers;

import compiler.RegexDFAPattern;
import errors.RegexException;

/**
 * Controller class for the graph function
 */
public class DFAGrapher {
    private final RegexDFAPattern regexPattern;

    public DFAGrapher(String regexString) throws RegexException {
        this.regexPattern = new RegexDFAPattern(regexString);
    }

    public DFAGraphData graph(){
        DFAGraphData ret = new DFAGraphData();
        ret.startState = this.regexPattern.graph().startState;
        ret.acceptingState = this.regexPattern.graph().acceptingStates;
        ret.transitionTable = this.regexPattern.graph().transitionTable;
        return ret;
    }
}
