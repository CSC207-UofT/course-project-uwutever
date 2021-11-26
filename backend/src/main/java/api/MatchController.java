package api;

import automata.Matcher;
import compiler.RegexPattern;

public class MatchController {
    private final RegexPattern regexPattern;

    public MatchController(RegexPattern regexPattern){
        this.regexPattern = regexPattern;
    }

    public boolean match(String text){
        return Matcher.match(this.regexPattern.getFsa(), text);
    }
}
