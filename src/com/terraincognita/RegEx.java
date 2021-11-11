package com.terraincognita;

import com.terraincognita.automata.NFA;
import com.terraincognita.errors.RegexSyntaxException;

import java.util.List;

public class RegEx {
    private String regexString;
    private CompiledRegEx compiledRegEx;
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
