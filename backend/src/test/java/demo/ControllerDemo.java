package demo;
import controllers.*;
import errors.RegexException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

//    /**
//     * Return an adjacency list
//     */
//    public getDFAVisualization(String text, String regex){
//        DFAGrapher dfaGrapher = new DFAGrapher(regex);
//        DFAGraphData dfaGraphData = dfaGrapher.graph();
//    }
}
