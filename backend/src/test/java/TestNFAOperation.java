import automata.nfa.NFA;
import automata.nfa.NFABuilder;
import automata.nfa.NFABasicOperation;
import automata.nfa.NFAExtendOperation;

public class TestNFAOperation {
    public static void main(String[] args) {
        NFABuilder nfaBuilder = new NFABuilder();
        nfaBuilder.reset();
        int state = nfaBuilder.addNewState();
        nfaBuilder.setStartState(state);

        NFABuilder nfaBuilder1 = new NFABuilder();
        nfaBuilder1.reset();
        int state1 = nfaBuilder1.addNewState();
        nfaBuilder1.setStartState(state1);
        int state2 = nfaBuilder1.addNewState();
        nfaBuilder1.addTransition(state1, "epsilon", state2);
        nfaBuilder1.setAcceptingState(state2);

        NFABasicOperation basicOperation = new NFABasicOperation();
        NFA union = basicOperation.union(nfaBuilder.getResult(), nfaBuilder1.getResult());
    }
}
