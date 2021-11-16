package demo;

import com.terraincognita.automata.nfa.NFA;
import com.terraincognita.automata.dfa.*;
import com.terraincognita.compiler.*;
import com.terraincognita.errors.NullStartStateException;
import com.terraincognita.parser.ast.ASTNode;

/** Demo for back-end for Phase 1
 * @author Arkaprava Choudhury
 */
public class DemoPhase1 {
    public static void main(String[] args) {
        // Input your regex and test string here. We will integrate this with the frontend in phase 2.
        demo("", "");
    }

    public static void demo(String regex, String demo) {
        ASTNode parsedAST = new RegextoASTCompiler(regex).getAST();
        System.out.print(parsedAST.prettyPrintAST());

        NFA nfa = new ASTtoNFACompiler(parsedAST).getNFA();
        DFA dfa = NFAtoDFAConverter.convert(nfa);

        try {
            System.out.println(dfa.accept(demo));
        } catch(NullStartStateException e) {
            System.out.println(e.getMessage());
        }

    }
}
