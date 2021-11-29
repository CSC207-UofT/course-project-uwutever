package controllers;

import compiler.RegexDFAPattern;
import compiler.RegexNFAPattern;
import compiler.RegexPattern;

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
     * You can get these attributes by using .[attribute name] as they are public
     * @return a NFAQuintuple object or a DFAQuintuple object
     */
    public Object getInfo() {
        return this.regexPattern.getFSA().getQuintuple();
    }
}
