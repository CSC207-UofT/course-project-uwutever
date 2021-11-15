package com.terraincognita.compiler;

import com.terraincognita.parser.ast.*;
import com.terraincognita.automata.nfa.*;

public class Compiler {

    private final ASTNode tree;
    public final NFA nfa;
    protected int maxStates;

    //    Construct a NFA from a corresponding AST

    public Compiler(ASTNode T) {
        this.tree = T;
        this.nfa = this.constructNFA(T, true, 1);
        this.maxStates = this.nfa.getMaxCount();
    }

    public NFA constructNFA(ASTNode T, boolean terminating, Integer counter) {
        NFA nfa = new NFA();
        if (T.operator != null) {
            if (T.operator.getValue() == '|') {
                NFA midNFA1 = constructNFA(T.left, false, counter);
                counter = midNFA1.getMaxCount() + 1;
                NFA midNFA2 = constructNFA(T.right, false, counter);
                counter = midNFA2.getMaxCount() + 1;

                nfa = new NFAUnion(midNFA1, midNFA2, terminating, counter);
            }
            else if (this.tree.operator.getValue() == '.') {
                NFA midNFA1 = constructNFA(T.left, false, counter);
                counter = midNFA1.getMaxCount() + 1;
                NFA midNFA2 = constructNFA(T.right, false, counter);
                counter = midNFA2.getMaxCount() + 1;

                nfa = new NFAConcat(midNFA1, midNFA2, terminating, counter);
            }
            else if (this.tree.operator.getValue() == '*') {
                NFA midNFA1 = constructNFA(T.exp, false, counter);
                counter = midNFA1.getMaxCount() + 1;
                NFA midNFA2 = new NFAEpsilon(false, counter);
                counter = midNFA2.getMaxCount() + 1;
                NFA midNFA3 = new NFAClosure(midNFA1, false, counter);
                counter = midNFA3.getMaxCount() + 1;

                nfa = new NFAUnion(midNFA1, midNFA2, terminating, counter);
            }
            else if (this.tree.operator.getValue() == '+') {
                NFA midNFA = constructNFA(T.exp, false, counter);
                counter = midNFA.getMaxCount() + 1;
                nfa = new NFAClosure(midNFA, terminating, counter);
            }
            else if (this.tree.operator.getValue() == '?') {
                NFA midNFA = constructNFA(T.exp, false, counter);
                counter = midNFA.getMaxCount() + 1;
                nfa = new NFAZeroOrOne(midNFA, terminating, counter);
            }
        }
        return nfa;
    }

}
