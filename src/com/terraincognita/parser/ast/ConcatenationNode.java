package com.terraincognita.parser.ast;

import com.terraincognita.lexer.Token;

import java.util.ArrayList;

public class ConcatenationNode extends ASTNode {

    public ConcatenationNode() {
        super();
        this.operator = Token.createConcat();
    }

    @Override
    public ArrayList<ASTNode> getChildren() { return this.children; }
}
