package demo;

import lexer.Lexer;
import parser.Parser;
import parser.ast.ASTNode;

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
