package demo;

import com.terraincognita.automata.nfa.NFA;
import com.terraincognita.automata.states.NFAState;
import com.terraincognita.compiler.ASTtoNFACompiler;
import com.terraincognita.lexer.Lexer;
import com.terraincognita.lexer.TokenType;
import com.terraincognita.parser.Parser;
import com.terraincognita.parser.ast.ASTNode;

import java.util.Set;

/** Combined demo for lexer and parser
 * @author Kevin Gao
 */
public class ParserDemo {
    public static void main(String[] args) {
        Lexer lexer = new Lexer("(a|b)cd*");
        lexer.tokenize();
        Parser parser = new Parser(lexer.getTokens(), null);
        ASTNode parsedAST = parser.parse();
        System.out.print(parsedAST.prettyPrintAST());
    }
}
