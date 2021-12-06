import automata.dfa.DFA;
import automata.dfa.NFAtoDFAConverter;
import automata.nfa.NFA;
import automata.nfa.NFABuilder;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static  org.junit.Assert.*;

public class TestNFAtoDFAConverter {

    private NFA nfa;
    private DFA dfa;

    /**
     * Set up a sample NFA with a few state
     * Convert the NFA to DFA
     *
     * Sample:
     *             a
     *     e / s1 -- s3 \ e
     * -> s0            s5 (accept)
     *     e \ s2 -- s4 / e
     *             b
     *
     * Note that this NFA only accepts the character a or the character b (a|b)
     */
    @Before
    public void setUp() throws Exception {
        NFABuilder nfaBuilder = new NFABuilder();
        nfaBuilder.reset();

        // add 6 states and record the indexes
        int s0 = nfaBuilder.addNewState();
        int s1 = nfaBuilder.addNewState();
        int s2 = nfaBuilder.addNewState();
        int s3 = nfaBuilder.addNewState();
        int s4 = nfaBuilder.addNewState();
        int s5 = nfaBuilder.addNewState();

        // s0 is start state
        // s5 is accepting state
        nfaBuilder.setStartState(s0);
        nfaBuilder.setAcceptingState(s5);

        // add epsilon transitions s0->s1, s0->s2, s3->s5, s4->s5
        nfaBuilder.addTransition(s0, "epsilon", s1);
        nfaBuilder.addTransition(s0, "epsilon", s2);
        nfaBuilder.addTransition(s3, "epsilon", s5);
        nfaBuilder.addTransition(s4, "epsilon", s5);

        // add alphabet transitions s1 a-> a3, s2 b-> s4
        nfaBuilder.addTransition(s1, "a", s3);
        nfaBuilder.addTransition(s2, "b", s4);

        // finally, set the instance attributes
        this.nfa = nfaBuilder.getResult();
        this.dfa = new NFAtoDFAConverter(this.nfa).convert();
    }

    /**
     * Test the equivalence of set of alphabets
     */
    @Test
    public void testAlphabets(){
        Set<String> dfaAlphabets = new HashSet<>(this.nfa.getAlphabets());
        dfaAlphabets.remove("epsilon");
        assertEquals(dfaAlphabets, this.dfa.getAlphabets());
    }

    /**
     * set up a few test cases to see whether this.nfa and this.dfa are equivalence
     */
    @Test
    public void testAcceptance(){
        String[] testStrings = {"a", "b", "c"};
        for (String testStr : testStrings){
            assert this.nfa.accept(testStr) == this.dfa.accept(testStr);
        }
    }
}
