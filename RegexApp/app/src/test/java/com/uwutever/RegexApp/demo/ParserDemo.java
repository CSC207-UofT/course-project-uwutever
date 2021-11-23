package demo;

import com.uwutever.RegexApp.utils.automata.nfa.NFA;
import com.uwutever.RegexApp.utils.automata.states.NFAState;
import com.uwutever.RegexApp.utils.compiler.ASTtoNFACompiler;
import com.uwutever.RegexApp.utils.lexer.Lexer;
import com.uwutever.RegexApp.utils.lexer.TokenType;
import com.uwutever.RegexApp.utils.parser.Parser;
import com.uwutever.RegexApp.utils.parser.ast.ASTNode;

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
