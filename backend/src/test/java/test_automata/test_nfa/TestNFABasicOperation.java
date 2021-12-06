package test_automata.test_nfa;

import automata.nfa.NFABasicChar;
import automata.nfa.NFABasicOperation;
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
public class TestNFABasicOperation {

    private NFABasicOperation nfaBasicOperation;
    private NFA midnfa1;
    private NFA midnfa2;

    @Before
    public void setUp() {
        nfaBasicOperation = new NFABasicOperation();
        NFABasicChar nfaBasicChar1 = new NFABasicChar();
        midnfa1 = nfaBasicChar1.singleAlphabet("a");
        NFABasicChar nfaBasicChar2 = new NFABasicChar();
        midnfa2 = nfaBasicChar2.singleAlphabet("b");
    }

    @After
    public void throwDown() {

    }

    @Test
    public void testUnion() {
        NFA nfa = nfaBasicOperation.union(midnfa1, midnfa2);
        assertTrue(nfa.accept("b"));
        assertTrue(nfa.accept("a"));
    }

    @Test
    public void testConcat() {
        NFA nfa = nfaBasicOperation.concatenation(midnfa1, midnfa2);
        assertTrue(nfa.accept("ab"));
        assertFalse(nfa.accept("b"));
        assertFalse(nfa.accept("a"));
    }

    @Test
    public void testStar() {
        NFA nfa = nfaBasicOperation.kleeneStar(midnfa1);
        assertTrue(nfa.accept("aa"));
    }
}
