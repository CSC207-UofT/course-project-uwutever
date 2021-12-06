package test_automata.test_nfa;

import automata.nfa.NFABuilder;
import errors.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*
These unit tests serve to test the NFABuilder class which implements the FSABuilder interface.
We don't test reset(), getResult()
 */
public class TestNFABuilder {

    private NFABuilder nfaBuilder;

    @Before
    public void setUp() {
        nfaBuilder = new NFABuilder();
        nfaBuilder.reset();
    }

    @Test
    public void testNFABuilderAddNewState() {
        nfaBuilder.addNewState();
        assertFalse(nfaBuilder.getResult().getStates().isEmpty());
    }

    @Test
    public void testNFABuilderSetStartState() {
        nfaBuilder.addNewState();
        nfaBuilder.setStartState(0);
        assertEquals(nfaBuilder.getResult().getStartState(), nfaBuilder.getResult().getStates().get(0));
    }

//    @Test
//    public void testNFABuilderSetStartStateException() {
//        try {
//            nfaBuilder.setStartState("2");
//        } catch (UnknownIdException e) {
//            assertEquals("The ID (2) is unknown to the FSA", e.getMessage());
//        }
//    }

    @After
    public void throwDown() {

    }
}
