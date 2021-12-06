package controllers;

import automata.Searcher;
import compiler.RegexDFAPattern;
import compiler.RegexNFAPattern;
import compiler.RegexPattern;
import errors.RegexException;

import java.util.List;

/**
 * This class construct a regexPattern
 * and perform searching with a given test string
 */
public class SearchController {
    private final RegexPattern regexPattern;

    public SearchController(String regexString, boolean compileDFA) throws RegexException {
        if (compileDFA) {
            this.regexPattern = new RegexDFAPattern(regexString);
        } else {
            this.regexPattern = new RegexNFAPattern(regexString);
        }
    }

    public List<List<Integer>> search(String text){
        return Searcher.search(this.regexPattern.getFSA(), text);
    }
}