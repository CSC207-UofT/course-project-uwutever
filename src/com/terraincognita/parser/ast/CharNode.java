package com.terraincognita.parser.ast;

import java.util.ArrayList;

public class CharNode extends ASTNode {
    public char literal;

    public CharNode(char literal) {
        super();
        this.literal = literal;
    }

    public char getLiteral() { return this.literal; }

    @Override
    public ArrayList<ASTNode> getChildren() {
        return this.children;
    }
}
