package com.uwutever.RegexApp.utils.controllers;


import com.uwutever.RegexApp.utils.compiler.RegexDFAPattern;
import com.uwutever.RegexApp.utils.compiler.RegexNFAPattern;
import com.uwutever.RegexApp.utils.errors.RegexException;

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