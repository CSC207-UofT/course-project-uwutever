package controllers;

import compiler.RegexDFAPattern;
import errors.RegexException;

/**
 * Controller class for the graph function
 */
public class DFAGrapher {
    private final RegexDFAPattern regexPattern;

    /**
     * Create a NFAGrapher for graphing the DFA
     * @param regexString the regular expression corresponding to the DFA to be graphed
     * @throws RegexException
     */
    public DFAGrapher(String regexString) throws RegexException {
        this.regexPattern = new RegexDFAPattern(regexString);
    }

    /**
     * Create a graph representation of the DFA
     * @return A DFA representation of type NFAGraphData
     */
    public DFAGraphData graph(){
        DFAGraphData ret = new DFAGraphData();
        ret.startState = this.regexPattern.graph().startState;
        ret.acceptingState = this.regexPattern.graph().acceptingStates;
        ret.transitionTable = this.regexPattern.graph().transitionTable;
        return ret;
    }
}
