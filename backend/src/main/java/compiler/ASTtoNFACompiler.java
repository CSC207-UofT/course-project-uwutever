package compiler;


import automata.nfa.NFA;
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

    /**
     * Construct a NFA from a corresponding AST
     */

    public ASTtoNFACompiler(ASTNode T) {
        this.ast = T;
    }

    /**
     * Compile the ast to an NFA
     * @return
     */
    @Override
    public NFA compile(){
        if(this.nfa == null){
            this.nfa = constructNFA(this.ast);
        }
        return this.nfa;
    }

    /**
     * Helper method of the compile method
     * @param T The AST to be constructed into NFA
     * @return the NFA constructed
     */
    public NFA constructNFA(ASTNode T) {
        NFA nfa = new NFA();

        if (T.operator == null) {
            CharNode node = (CharNode) T;
            nfa = new NFABasicChar().singleAlphabet(Character.toString(node.getLiteral()));
            return nfa;
        }

        if (T.operator.getValue() == '|') {
            NFA midnfa1 = constructNFA(T.left);
            NFA midnfa2 = constructNFA(T.right);

            nfa = new NFABasicOperation().union(midnfa1, midnfa2);
        }
        else if (T.operator.getValue() == '.') {
            NFA midnfa1 = constructNFA(T.left);
            NFA midnfa2 = constructNFA(T.right);

            nfa = new NFABasicOperation().concatenation(midnfa1, midnfa2);
        }
        else if (T.operator.getValue() == '*') {
            NFA midnfa1 = constructNFA(T.exp);

            nfa = new NFABasicOperation().kleeneStar(midnfa1);
        }
        else if (T.operator.getValue() == '+') {
            NFA midnfa1 = constructNFA(T.exp);

            nfa = new NFAExtendOperation().OneOrMore(midnfa1);
        }
        else if (T.operator.getValue() == '?') {
            NFA midNFA = constructNFA(T.exp);
            nfa = new NFAExtendOperation().ZeroOrOne(midNFA);
        }

        return nfa;
    }
}