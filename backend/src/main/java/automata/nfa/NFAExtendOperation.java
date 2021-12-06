package automata.nfa;

public class NFAExtendOperation{

    public NFA zeroOrOne(NFA midNFA){
        NFA epsilon = new NFABasicChar().epsilonChar();
        return new NFABasicOperation().union(midNFA, epsilon);
    }

    public NFA oneOrMore(NFA midNFA){
        NFA kleene = new NFABasicOperation().kleeneStar(midNFA);
        return new NFABasicOperation().concatenation(midNFA, kleene);
    }

}
