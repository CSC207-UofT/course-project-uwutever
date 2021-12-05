import automata.dfa.DFA;
import automata.dfa.DFABuilder;

import org.junit.Before;
import org.junit.Test;

public class TestDFA {
    private DFA dfa;

    /**
     * Set up a sample DFA with a few state
     *
     * Sample:
     *       __ a
     *     ./  \
     * -> s0 -> s1 (accepting)
     *       a
     * Note that this DFA only accepts odd numbers of a
     *For example: a, aaa, aaaaa is accepted
     * "", aa is not accepted
     */
    @Before
    public void setUp() throws Exception {
        DFABuilder dfaBuilder = new DFABuilder();
        dfaBuilder.reset();

        // add states s0 and s1
        int s0 = dfaBuilder.addNewState();
        int s1 = dfaBuilder.addNewState();
        int dead = dfaBuilder.addNewState(); // add a dead state for unknown alphabets

        // set start state and accepting state
        dfaBuilder.setStartState(s0);
        dfaBuilder.setAcceptingState(s1);
        dfaBuilder.setDeadState(dead);

        // set transitions
        dfaBuilder.addTransition(s0, "a", s1);
        dfaBuilder.addTransition(s1, "a", s0);

        this.dfa = dfaBuilder.getResult();
    }

    /**
     * Test whether the dfa accepts some odd numbers of "a"s
     */
    @Test
    public void testAccept(){
        String[] testStrings = {"a", "aaaaa", "aaa"};
        for(String testStr: testStrings){
            assert this.dfa.accept(testStr);
        }
    }

    /**
     * Test whether the dfa not accepts some even numbers of "a"s and strange cases
     */
    @Test
    public void testNotAccept(){
        String[] testStrings = {"", "aa", "b", "//`"};
        for(String testStr: testStrings){
            assert !this.dfa.accept(testStr);
        }
    }
}

