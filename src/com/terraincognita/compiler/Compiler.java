package com.terraincognita.compiler;

import com.terraincognita.parser.ast.*;
import com.terraincognita.automata.nfa.*;

public class Compiler {

    public final NFA nfa;
    protected int maxStates;

    //    Construct a NFA from a corresponding AST
    public Compiler(ASTNode T) {
        this.nfa = this.constructNFA(T, true, 1);
        this.maxStates = this.nfa.getMaxCount();
    }

    public NFA constructNFA(ASTNode T, boolean terminating, Integer counter) {
        NFA nfa = new NFA();

        if (T.operator == null) {
            CharNode node = (CharNode) T;
            nfa = NFAChar.build(Character.toString(node.getLiteral()), true, counter);
            return nfa;
        }

        if (T.operator.getValue() == '|') {
            NFA midnfa1 = constructNFA(T.left, false, counter);
            counter = midnfa1.getMaxCount() + 1;
            NFA midnfa2 = constructNFA(T.right, false, counter);
            counter = midnfa2.getMaxCount() + 1;

            nfa = NFAUnion.build(midnfa1, midnfa2, terminating, counter);
        }
        else if (T.operator.getValue() == '.') {
            NFA midnfa1 = constructNFA(T.left, false, counter);
            counter = midnfa1.getMaxCount() + 1;
            NFA midnfa2 = constructNFA(T.right, false, counter);
            counter = midnfa2.getMaxCount() + 1;

            nfa = NFAConcat.build(midnfa1, midnfa2, terminating, counter);
        }
        else if (T.operator.getValue() == '*') {
            NFA midnfa1 = constructNFA(T.exp, false, counter);
            counter = midnfa1.getMaxCount() + 1;
            NFA midnfa2 = NFAEpsilon.build(false, counter);
            counter = midnfa2.getMaxCount() + 1;
            NFA midnfa3 = NFAClosure.build(midnfa1, false, counter);
            counter = midnfa3.getMaxCount() + 1;

            nfa = NFAUnion.build(midnfa1, midnfa2, terminating, counter);
        }
        else if (T.operator.getValue() == '+') {
            NFA midnfa = constructNFA(T.exp, false, counter);
            counter = midnfa.getMaxCount() + 1;
            nfa = NFAClosure.build(midnfa, terminating, counter);
        }
        else if (T.operator.getValue() == '?') {
            NFA midNFA = constructNFA(T.exp, false, counter);
            counter = midNFA.getMaxCount() + 1;
            nfa = NFAZeroOrOne.build(midNFA, terminating, counter);
        }

        return nfa;
    }

}