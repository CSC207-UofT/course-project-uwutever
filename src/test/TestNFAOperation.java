import com.terraincognita.automata.nfa.NFABuilder;
import com.terraincognita.automata.nfa.NFAOperations;
import com.terraincognita.automata.states.NFAState;

public class TestNFAOperation {
    public static void main(String[] args) {
        NFABuilder nfaBuilder = new NFABuilder();
        nfaBuilder.reset();
        nfaBuilder.addState("1", true);
        nfaBuilder.setStartState("1");

        NFABuilder nfaBuilder1 = new NFABuilder();
        nfaBuilder1.reset();
        nfaBuilder1.addState("1");
        nfaBuilder1.setStartState("1");
        nfaBuilder1.addState("2", true);
        nfaBuilder1.addTransition("1", "epsilon",  "2");
        nfaBuilder1.setEndState("2");

        NFAOperations.appendMidNFA(nfaBuilder, nfaBuilder1.getResult(), 2);
    }
}
