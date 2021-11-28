package controllers;

import automata.Searcher;
import compiler.RegexNFAPattern;

import java.util.List;

public class SearchController {
    private final RegexNFAPattern regexPattern;

    public SearchController(RegexNFAPattern regexPattern){
        this.regexPattern = regexPattern;
    }
    public List<List<Integer>> search(String text){
        return Searcher.search(this.regexPattern.getFSA(), text);
    }
}
