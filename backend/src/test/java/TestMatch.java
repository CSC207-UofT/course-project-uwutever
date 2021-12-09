import controllers.MatchController;
import org.junit.*;

public class TestMatch {


    @Before
    public void setUp() throws Exception {
        MatchController matchController = new MatchController("aa*", false);
    }

    @Test
    public void testMatchController() {
        String testRegex = "aa*";
        String testStr = "aaaa";
        MatchController matchController = new MatchController(testRegex, false);
        boolean matched = matchController.match(testStr);
        assert matched == true;
        matched = matchController.match("b");
        assert matched == false;
    }

}
