package compiler;

import automata.FSA;

/**
 * An interface for the RegexPattern
 */
public interface RegexPattern {
    /**
     * Getter method of the RegexString in the pattern
     * @return the regex string in the pattern
     */
    String getRegexStr();

    /**
     * Getter method of the FSA in the class
     * @param <T> either DFA or NFA
     * @return the DFA / NFA in the class
     */
    <T extends FSA> T getFSA();
}
