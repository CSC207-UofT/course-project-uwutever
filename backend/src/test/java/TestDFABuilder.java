import automata.dfa.DFABuilder;
import automata.dfa.DFAState;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
These unit tests serve to test the NFABuilder class which implements the FSABuilder interface.
We don't test reset(), getResult()
 */
public class TestDFABuilder {

    private DFABuilder dfaBuilder;

    @Before
    public void setUp() throws Exception {
        dfaBuilder = new DFABuilder();
        dfaBuilder.reset();
    }

    @Test
    public void testDFABuilderAddState() {
        int index = dfaBuilder.addNewState();
        assert dfaBuilder.getResult().getStates().get(index) != null;
    }

    @Test
    public void testDFABuilderSetStartState() {
        int index = dfaBuilder.addNewState();
        dfaBuilder.setStartState(index);
        assertEquals(dfaBuilder.getResult().getStartState(), dfaBuilder.getResult().getStates().get(index));
    }

    @Test
    public void testDFABuilderSetAcceptingState(){
        int index = dfaBuilder.addNewState();
        dfaBuilder.setAcceptingState(index);
        DFAState expectedAcceptingState = dfaBuilder.getResult().getStates().get(index);
        assert dfaBuilder.getResult().getAcceptingStates().contains(expectedAcceptingState);
    }

    @Test
    public void testDFABuilderAddTransition(){
        int s0 = dfaBuilder.addNewState();
        int s1 = dfaBuilder.addNewState();
        dfaBuilder.addTransition(s0, "a", s1);

        DFAState fromState = dfaBuilder.getResult().getStates().get(s0);
        DFAState expectedToState = dfaBuilder.getResult().getStates().get(s1);

        assertEquals(expectedToState, dfaBuilder.getResult().delta(fromState, "a"));
    }
}
