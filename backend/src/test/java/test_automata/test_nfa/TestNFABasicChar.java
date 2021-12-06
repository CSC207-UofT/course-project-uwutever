package test_automata.test_nfa;

import automata.nfa.NFABasicChar;
import automata.nfa.NFA;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
//import automata.nfa.NFAOperations;

/*
The following unit tests assume that the NFABasicChar class operations work as expected,
because of the TestNFABasicChar unit tests.
 */
public class TestNFABasicChar {

    private NFABasicChar nfaBasicChar;

    @Before
    public void setUp() {
        nfaBasicChar = new NFABasicChar();
    }

    @After
    public void throwDown() {

    }

    @Test
    public void testSingleAlphabet() {
        NFA nfa = nfaBasicChar.singleAlphabet("a");
        assertTrue(nfa.accept("a"));
    }

    @Test
    public void testEpsilon() {
        NFA nfa = nfaBasicChar.epsilonChar();
        assertTrue(nfa.accept(""));
        assertFalse(nfa.accept("a"));
    }

}
