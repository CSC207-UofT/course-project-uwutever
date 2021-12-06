import automata.nfa.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestNFAOperation {

    private NFABuilder nfaBuilder;
    private NFABasicOperation basicOperation;
    private NFAExtendOperation extendOperation;
    private NFA nfa1;
    private NFA nfa2;

    // private method to initialize NFA for use in testing the operations
    private void initNFAs() {
        nfaBuilder = new NFABuilder();
        nfaBuilder.reset();
        int state1 = nfaBuilder.addNewState();
        int state2 = nfaBuilder.addNewState();
        nfaBuilder.addTransition(state1, "a", state2);
        nfaBuilder.setStartState(state1);
        nfaBuilder.setAcceptingState(state2);
        nfa1 = nfaBuilder.getResult();  // this NFA accepts "a"

        nfaBuilder.reset();
        int state3 = nfaBuilder.addNewState();
        int state4 = nfaBuilder.addNewState();
        nfaBuilder.addTransition(state3, "b", state4);
        nfaBuilder.setStartState(state3);
        nfaBuilder.setAcceptingState(state4);
        nfa2 = nfaBuilder.getResult();  // this NFA accepts "b"
    }

    @Before
    public void setUp() {
        nfaBuilder = new NFABuilder();
        basicOperation = new NFABasicOperation();
        extendOperation = new NFAExtendOperation();
    }

    @Test
    public void testUnion() {
        initNFAs();
        NFA result = basicOperation.union(nfa1, nfa2);
        assertTrue(result.accept("a"));
        assertTrue(result.accept("b"));
        assertFalse(result.accept("ab"));
    }

    @Test
    public void testConcatenation() {
        initNFAs();
        NFA result = basicOperation.concatenation(nfa1, nfa2);
        assertTrue(result.accept("ab"));
        assertFalse(result.accept("a"));
        assertFalse(result.accept("b"));
    }

    @Test
    public void testKleeneStar() {
        initNFAs();
        NFA result = basicOperation.kleeneStar(nfa1);
        assertTrue(result.accept(""));
        assertTrue(result.accept("a"));
        assertTrue(result.accept("aa"));
        assertFalse(result.accept("b"));
    }

    @Test
    public void testZeroOrOne() {
        initNFAs();
        NFA result = extendOperation.zeroOrOne(nfa1);
        assertTrue(result.accept(""));
        assertTrue(result.accept("a"));
        assertFalse(result.accept("aa"));
        assertFalse(result.accept("b"));
    }

    @Test
    public void testOneOrMore() {
        initNFAs();
        NFA result = extendOperation.oneOrMore(nfa1);
        assertFalse(result.accept(""));
        assertTrue(result.accept("a"));
        assertTrue(result.accept("aa"));
        assertFalse(result.accept("b"));
    }

    @After
    public void throwDown() {

    }
}
