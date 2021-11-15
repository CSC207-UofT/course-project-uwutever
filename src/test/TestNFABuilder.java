import com.terraincognita.automata.nfa.NFABuilder;
import com.terraincognita.errors.*;

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
        nfaBuilder.addState("1");
    }

    @Test
    public void testNFABuilderAddState() {
        nfaBuilder.addState("2");
        assert nfaBuilder.getResult().getStatesID().contains("2");
    }

    @Test
    public void testNFABuilderAddStateException() {
        try {
            nfaBuilder.addState("1");
        } catch (OccupiedIdException e) {
            assertEquals("The ID (1) is occupied in the FSA", e.getMessage());
        }
    }

    @Test
    public void testNFABuilderSetStartState() {
        nfaBuilder.setStartState("1");
        assertEquals("1", nfaBuilder.getResult().getStartState().getId());
    }

    @Test
    public void testNFABuilderSetStartStateException() {
        try {
            nfaBuilder.setStartState("2");
        } catch (UnknownIdException e) {
            assertEquals("The ID (2) is unknown to the FSA", e.getMessage());
        }
    }

    @After
    public void throwDown() {

    }
}
