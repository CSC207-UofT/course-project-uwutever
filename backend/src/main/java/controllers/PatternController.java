package controllers;

import compiler.RegexDFAPattern;
import compiler.RegexNFAPattern;
import compiler.RegexPattern;

import java.util.List;

public class PatternController {
    private final RegexPattern regexPattern;

    public PatternController(String regexString, boolean compileDFA){
        if (compileDFA){
            this.regexPattern = new RegexDFAPattern(regexString);
        } else{
            this.regexPattern = new RegexNFAPattern(regexString);
        }
    }

    public String getRegexString() {
        return this.regexPattern.getRegexStr();
    }

    public RegexPattern getRegexPattern() {
        return regexPattern;
    }

}
