package com.uwutever.RegexApp.utils.automata.nfa;

public class NFAExtendOperation{

    public NFA ZeroOrOne(NFA midNFA){
        NFA epsilon = new NFABasicChar().epsilonChar();
        return new NFABasicOperation().union(midNFA, epsilon);
    }

    public NFA OneOrMore(NFA midNFA){
        NFA kleene = new NFABasicOperation().kleeneStar(midNFA);
        return new NFABasicOperation().concatenation(midNFA, kleene);
    }

}
