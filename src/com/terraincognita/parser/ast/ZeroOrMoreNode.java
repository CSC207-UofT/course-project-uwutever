package com.terraincognita.parser.ast;

import com.terraincognita.lexer.Token;

import java.util.ArrayList;

public class ZeroOrMoreNode extends ASTNode {

    public ZeroOrMoreNode() {
        super();
        this.operator = Token.createToken('*');
    }

    @Override
    public ArrayList<ASTNode> getChildren() { return this.children; }
}
