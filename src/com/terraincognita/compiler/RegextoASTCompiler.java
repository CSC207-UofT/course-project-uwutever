package com.terraincognita.compiler;

import com.terraincognita.lexer.Lexer;
import com.terraincognita.parser.Parser;
import com.terraincognita.parser.ast.ASTNode;

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
