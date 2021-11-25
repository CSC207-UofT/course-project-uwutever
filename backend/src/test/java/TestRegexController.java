import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.HashMap;

public class TestRegexController {


    @Before
    public void setUp() {
        RegexController regexController = new RegexController();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetMatches() {
        try {
            RegexController regexController = new RegexController();
            List<Match> actual = regexController.getMatches("hallo", "yahalloiamopera");
            HashMap<String, Integer> actualMapInt = new HashMap<>();
            HashMap<String, String> actualMapStr = new HashMap<>();

            for (Match match : actual) {
                actualMapInt.put("Start", match.getStart());
                actualMapInt.put("End", match.getEnd());
                actualMapStr.put("Content", match.getContent());
            }

            HashMap<String, String> expectMapStr = new HashMap<>();
            expectMapStr.put("Content", "hallo");

            HashMap<String, Integer> expectMapInt = new HashMap<>();
            expectMapInt.put("Start", 2);
            expectMapInt.put("End", 7);

            assert expectMapStr.equals(actualMapStr);
            assert expectMapInt.equals(actualMapInt);


        } catch (Exception e) {
            System.out.println("Something went wrong with testing getMatches...");
        }
    }

}
