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

        if (T.operator.getValue() == '|') {
            NFA midnfa1 = constructNFA(T.left, false, counter);
            counter = midnfa1.getMaxCount() + 1;
            NFA midnfa2 = constructNFA(T.right, false, counter);
            counter = midnfa2.getMaxCount() + 1;

            nfa = new NFAUnion(midnfa1, midnfa2, terminating, counter);
        }
        else if (this.tree.operator.getValue() == '.') {
            NFA midnfa1 = constructNFA(T.left, false, counter);
            counter = midnfa1.getMaxCount() + 1;
            NFA midnfa2 = constructNFA(T.right, false, counter);
            counter = midnfa2.getMaxCount() + 1;

            nfa = new NFAConcat(midnfa1, midnfa2, terminating, counter);
        }
        else if (this.tree.operator.getValue() == '*') {
            NFA midnfa1 = constructNFA(T.exp, false, counter);
            counter = midnfa1.getMaxCount() + 1;
            NFA midnfa2 = new NFAEpsilon(false, counter);
            counter = midnfa2.getMaxCount() + 1;
            NFA midnfa3 = new NFAClosure(midnfa1, false, counter);
            counter = midnfa3.getMaxCount() + 1;

            nfa = new NFAUnion(midnfa1, midnfa2, terminating, counter);
        }
        else if (this.tree.operator.getValue() == '+') {
            NFA midnfa = constructNFA(T.exp, false, counter);
            counter = midnfa.getMaxCount() + 1;
            nfa = new NFAClosure(midnfa, terminating, counter);
        }
        else if (this.tree.operator.getValue() == '?') {
            NFA midNFA = constructNFA(T.exp, false, counter);
            counter = midNFA.getMaxCount() + 1;
            nfa = new NFAZeroOrOne(midNFA, terminating, counter);
        }

        return nfa;
    }

}
