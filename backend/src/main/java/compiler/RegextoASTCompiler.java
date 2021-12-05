package compiler;


import lexer.Lexer;
import parser.Parser;
import parser.ast.ASTNode;

/** Compiling AST from Lexer
 * @author Brian Ho
 * @author Kevin Gao
 */

public class RegextoASTCompiler implements Compiler{
    private Lexer lexer;
    private final Parser parser;
    private ASTNode ast;

    public RegextoASTCompiler(String regex){
        Lexer lexer = new Lexer(regex);
        lexer.tokenize();
        this.parser = new Parser(lexer.getTokens(), null);
    }

    @Override
    public ASTNode compile() {
        if(this.ast == null){
            this.ast = this.parser.parse();
        }
        return this.ast;
    }

    public ASTNode getAST() {
        return ast;
    }
}