package com.terraincognita;

import java.util.ArrayList;
import java.util.List;

// for testing purpose only
import java.util.regex.*;

public class RegexController {
    private static RegexController controller_instance = null;

    public static RegexController getController() {
        if (controller_instance == null) {
            controller_instance = new RegexController();
        }
        return controller_instance;
    }
    public List<Match> getMatches(String regexPattern, String testStr) {
        RegEx reg = new RegEx(regexPattern);

        // for testing purpose only: use Java's built-in RegEx engine
        // This part will be replaced by our own regex engine in the future
        List<Match> matches = new ArrayList<Match>();
        Pattern compiledPattern = Pattern.compile(regexPattern);
        Matcher matcher = compiledPattern.matcher(testStr);
        int count = matcher.groupCount();
        while (matcher.find()) {
            Match match = new Match(matcher.start(), matcher.end(), matcher.group());
            matches.add(match);
        }
        return matches;
    }

    public RegEx simplifyRegex(RegEx regex) {
        return null;
    }

}
