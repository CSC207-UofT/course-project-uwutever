package com.terraincognita.parser.ast;

import com.terraincognita.lexer.Token;

import java.util.ArrayList;

public class AlternationNode extends ASTNode {

    public AlternationNode() {
        super();
        this.operator = Token.createToken('|');
    }

    public ArrayList<ASTNode> getChildren() { return this.children; }
}
