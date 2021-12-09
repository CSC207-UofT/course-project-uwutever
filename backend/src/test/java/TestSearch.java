import controllers.SearchController;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestSearch {


    @Before
    public void setUp() throws Exception {
        SearchController matchController = new SearchController("aa*", false);
    }

    @Test
    public void testSearchController() {
        String testRegex = "aa*";
        String testStr = "aa a";
        SearchController searchController = new SearchController(testRegex, false);
        List<List<Integer>> result = searchController.search(testStr);

        List<Integer> matchGroup1 = new ArrayList<>(List.of(0, 2));
        List<Integer> matchGroup2 = new ArrayList<>(List.of(3, 4));
        List<List<Integer>> expected = new ArrayList<>(List.of(matchGroup1, matchGroup2));

        assert result.equals(expected);
    }

    @Test
    public void testSearchController2() {
        String testRegex = "ab*a";
        String testStr = "abba aaaba";
        SearchController searchController = new SearchController(testRegex, false);
        List<List<Integer>> result = searchController.search(testStr);

        List<Integer> matchGroup1 = new ArrayList<>(List.of(0, 4));
        List<Integer> matchGroup2 = new ArrayList<>(List.of(5, 7));
        List<Integer> matchGroup3 = new ArrayList<>(List.of(7, 10));
        List<List<Integer>> expected = new ArrayList<>(List.of(matchGroup1, matchGroup2, matchGroup3));

        assert result.equals(expected);
    }

}
