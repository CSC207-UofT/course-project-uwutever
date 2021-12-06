import automata.nfa.NFA;
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
    private int state;

    @Before
    public void setUp() {
        nfaBuilder = new NFABuilder();
        nfaBuilder.reset();
        state = nfaBuilder.addNewState();
    }

    @Test
    public void testNFABuilderAddState() {
        int state1 = nfaBuilder.addNewState();
    }

    @Test
    public void testNFABuilderSetStartState() {
        nfaBuilder.setStartState(state);
        NFA nfa = nfaBuilder.getResult();
        assertEquals(nfa.getStates().get(state), nfa.getStartState());
    }

    @Test
    public void testNFABuilderSetStartStateException() {
        try {
            nfaBuilder.setStartState(2);
        } catch (UnknownStateIndexException e) {
            assertEquals("The index (2) is unknown to the FSA", e.getMessage());
        }
    }

    @After
    public void throwDown() {

    }
}
