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

    /**
     * Constructor of the class
     * Construct a RegextoASTCompiler object with a given regex pattern string
     * @param regex a regex patter string
     */
    public RegextoASTCompiler(String regex){
        Lexer lexer = new Lexer(regex);
        lexer.tokenize();
        this.parser = new Parser(lexer.getTokens(), null);
    }

    /**
     * Compile the ast in the class
     * @return an ast for the regex pattern in the class
     */
    @Override
    public ASTNode compile() {
        if(this.ast == null){
            this.ast = this.parser.parse();
        }
        return this.ast;
    }

    /**
     * Getter method of the ast
     * @return the ast in the class
     */
    public ASTNode getAST() {
        return ast;
    }
}
