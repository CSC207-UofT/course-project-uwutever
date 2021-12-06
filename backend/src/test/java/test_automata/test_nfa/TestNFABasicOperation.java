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

    @Before
    public void setUp() {
        nfaBasicOperation = new NFABasicOperation();
        NFABasicChar nfaBasicChar1 = new NFABasicChar();
        NFA midnfa1 = nfaBasicChar1.singleAlphabet("a");
        NFABasicChar nfaBasicChar2 = new NFABasicChar();
        NFA midnfa2 = nfaBasicChar2.singleAlphabet("b");
    }

    @After
    public void throwDown() throws Exception {

    }

    @Test
    public void testUnion() {
        // TODO
    }

    @Test
    public void testConcat() {

    }

    @Test
    public void testStar() {

    }
}
