package com.uwutever.RegexApp.utils.controllers;

import com.uwutever.RegexApp.utils.automata.Searcher;
import com.uwutever.RegexApp.utils.compiler.RegexDFAPattern;
import com.uwutever.RegexApp.utils.compiler.RegexNFAPattern;
import com.uwutever.RegexApp.utils.compiler.RegexPattern;
import com.uwutever.RegexApp.utils.errors.RegexException;

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
