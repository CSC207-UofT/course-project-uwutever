package com.uwutever.RegexApp.utils;

import com.uwutever.RegexApp.utils.controllers.*;

import java.util.List;

public class ControllerDemo {
    /**
     * Return a list of integer pairs. Each pair indicate the start and end index of the match
     */
    public List<List<Integer>> getMatch(String text, String regex){
        SearchController searchController = new SearchController(regex, false);
        return searchController.search(text);
    }

    /**
     * Return whether the test string matches the regex pattern
     *
     */
    public boolean getMatchCheck(String text, String regex){
        MatchController matchController = new MatchController(regex, false);
        return matchController.match(text);
    }

    /**
     * Return a DFAGraphData class
     */
    public DFAGraphData getDFAVisualization(String regex){
        DFAGrapher dfaGrapher = new DFAGrapher(regex);
        return dfaGrapher.graph();
    }

    /**
     * Return an NFAGraphData class
     */
    public NFAGraphData getNFAVisualization(String regex){
        NFAGrapher nfaGrapher = new NFAGrapher(regex);
        return nfaGrapher.graph();
    }


}