package compiler;

import automata.FSA;

public interface RegexPattern {
    String getRegexStr();
    <T extends FSA> T getFSA();
}
