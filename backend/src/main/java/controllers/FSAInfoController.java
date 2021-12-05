package controllers;

import compiler.RegexDFAPattern;
import compiler.RegexNFAPattern;
import compiler.RegexPattern;

import java.util.Map;

/**
 * This class construct a regexPattern
 * and users are able to get the quintuple form of the FSA by the getInfo method
 */
public class FSAInfoController {
    private final RegexPattern regexPattern;

    public FSAInfoController(String regexString, boolean compileDFA){
        if(compileDFA){
            this.regexPattern = new RegexDFAPattern(regexString);
        } else{
            this.regexPattern = new RegexNFAPattern(regexString);
        }
    }

    /**
     * Return a quintuple object which contains 5 public attributes
     * - states: Set<String>
     * - alphabets: Set<String>
     * - startState: String
     * - acceptingStates: Set<String>
     * - transitionTable: Map<String, Map<String, String or Set<String>>>
     * @return a Map with 5 objects with keys "states", "alphabets", "start state", "accepting state" and "transitions"
     */
    public Map<String, Object> getInfo() {
        return this.regexPattern.getFSAQuintuple();
    }
}
