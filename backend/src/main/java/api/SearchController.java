package api;

import automata.Searcher;
import compiler.RegexPattern;

import java.util.List;

public class SearchController {
    private final RegexPattern regexPattern;

    public SearchController(RegexPattern regexPattern){
        this.regexPattern = regexPattern;
    }
    public List<List<Integer>> search(String text){
        return Searcher.search(this.regexPattern.getFsa(), text);
    }
}
