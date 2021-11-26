package api;

import automata.Matcher;
import automata.Searcher;
import compiler.RegexPattern;

import java.util.List;

public class PatternController {
    private final RegexPattern regexPattern;

    public PatternController(String regexString){
        this.regexPattern = new RegexPattern(regexString);
    }

    public String getRegexString() {
        return this.regexPattern.getRegexStr();
    }

    public RegexPattern getRegexPattern() {
        return regexPattern;
    }

    public List<List<Integer>> search(String text){
        return Searcher.search(this.regexPattern.getFsa(), text);
    }
}
