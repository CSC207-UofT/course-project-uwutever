package compiler;


import automata.nfa.NFA;
import automata.nfa.NFAChar;
import automata.nfa.*;
import parser.ast.ASTNode;
import parser.ast.CharNode;

/** Compiling NFA from AST
 * @author Arkaprava Choudhury
 * @author Brian Ho
 */

public class ASTtoNFACompiler implements Compiler{

    private final ASTNode ast;
    private NFA nfa;

    //    Construct a NFA from a corresponding AST
    public ASTtoNFACompiler(ASTNode T) {
        this.ast = T;
    }

    @Override
    public NFA compile(){
        if(this.nfa == null){
            this.nfa = constructNFA(this.ast, true, 1);
        }
        return this.nfa;
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

            nfa = NFAConcat.build(midnfa2, midnfa1, terminating, counter);
        }
        else if (T.operator.getValue() == '*') {
            NFA midnfa1 = constructNFA(T.exp, false, counter);
            counter = midnfa1.getMaxCount() + 1;

            nfa = NFAClosure.build(midnfa1, terminating, counter);
        }
        else if (T.operator.getValue() == '+') {
            NFA midnfa1 = constructNFA(T.exp, false, counter);
            counter = midnfa1.getMaxCount() + 1;

            NFA midnfa2 = NFAClosure.build(midnfa1, false, counter);
            counter = midnfa2.getMaxCount() + 1;

            nfa = NFAConcat.build(midnfa1, midnfa2, terminating, counter);
        }
        else if (T.operator.getValue() == '?') {
            NFA midNFA = constructNFA(T.exp, false, counter);
            counter = midNFA.getMaxCount() + 1;
            nfa = NFAZeroOrOne.build(midNFA, terminating, counter);
        }

        return nfa;
    }

    public NFA getNFA() {
        return nfa;
    }
}