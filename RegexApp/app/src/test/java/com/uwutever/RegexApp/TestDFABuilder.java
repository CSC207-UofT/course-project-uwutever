import com.terraincognita.automata.dfa.DFABuilder;
import com.terraincognita.errors.*;
import com.terraincognita.lexer.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/*
These unit tests serve to test the NFABuilder class which implements the FSABuilder interface.
We don't test reset(), getResult()
 */
public class TestDFABuilder {

    private DFABuilder dfaBuilder;

    @Before
    public void setUp() throws Exception {
        dfaBuilder = new DFABuilder();
        dfaBuilder.reset();
        dfaBuilder.addState("1");
    }

    @Test
    public void testNFABuilderAddState() {
        dfaBuilder.addState("2");
        assert dfaBuilder.getResult().getStatesID().contains("2");
    }

    @Test
    public void testNFABuilderAddStateException() {
        try {
            dfaBuilder.addState("1");
        } catch (OccupiedIdException e) {
            assertEquals("The ID (1) is occupied in the FSA", e.getMessage());
        }
    }

    @Test
    public void testNFABuilderSetStartState() {
        dfaBuilder.setStartState("1");
        assertEquals("1", dfaBuilder.getResult().getStartState().getId());
    }

    @Test
    public void testNFABuilderSetStartStateException() {
        try {
            dfaBuilder.setStartState("2");
        } catch (UnknownIdException e) {
            assertEquals("The ID (2) is unknown to the FSA", e.getMessage());
        }
    }

    @After
    public void throwDown() throws Exception {

    }
}
