package test_automata.test_nfa;

import automata.nfa.NFA;
import automata.nfa.NFABuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestNFA {
    private NFA nfa;

    @Before
    public void setUp() {
        NFABuilder nfaBuilder = new NFABuilder();
        nfaBuilder.reset();

        // add states s0 and s1
        int s0 = nfaBuilder.addNewState();
        int s1 = nfaBuilder.addNewState();
        int s2 = nfaBuilder.addNewState();

        // set start state and accepting state
        nfaBuilder.setStartState(s0);
        nfaBuilder.setAcceptingState(s2);

        // set transitions
        nfaBuilder.addTransition(s0, "a", s1);
        nfaBuilder.addTransition(s1, "c", s2);
        nfaBuilder.addTransition(s1, "c", s0);
        nfaBuilder.addTransition(s1, "c", s1);
        nfaBuilder.addTransition(s1, "b", s2);
        nfaBuilder.addTransition(s2, "b", s2);

        this.nfa = nfaBuilder.getResult();
    }

    /*
    Test whether the NFA is initialized properly
    This unit test is responsible for testing multiple aspects of the NFA class, viz. getStates(), getAlphabets(),
    getStartStates()
     */
    @Test
    public void testNFAConstruction() {
        String testString = "ab";
        String testStringNotAccepted = "aab";
        assert this.nfa.accept(testString);
        assert !this.nfa.accept(testStringNotAccepted);
    }

    @Test
    public void testNFASelfLoopConstruction() {
        String testString = "abbbbb";
        String testStringNotAccepted = "aba";
        assert this.nfa.accept(testString);
        assert !this.nfa.accept(testStringNotAccepted);
    }

    @Test
    public void testNFAMultipleTransition() {
        String testString = "ac";
        String testStringNotAccepted = "aba";
        assert this.nfa.accept(testString);
        assert !this.nfa.accept(testStringNotAccepted);
    }

    @After
    public void throwDown() throws Exception {

    }
}
