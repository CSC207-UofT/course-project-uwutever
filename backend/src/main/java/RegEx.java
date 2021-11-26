import automata.nfa.NFA;
import compiler.RegexPattern;
import errors.RegexSyntaxException;

import java.util.List;

public class RegEx {
    private String regexString;
    private RegexPattern compiledRegEx;
    private NFA parsedNFA;

    public RegEx(String pattern) {
        this.regexString = pattern;

    }

    private void parse() throws RegexSyntaxException {

    }

    public List<Match> matches(String testStr) {
        throw new UnsupportedOperationException();
    }
}
