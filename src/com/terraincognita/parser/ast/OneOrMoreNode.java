package com.terraincognita.parser.ast;

import com.terraincognita.lexer.Token;

import java.util.ArrayList;

public class OneOrMoreNode extends ASTNode {

    /**
     * An <code>ASTNode</code> representing the Kleene plus ('+') operation.
     *
     * This node has only one child, which is stored in the <code>exp</code> field.
     */
    public OneOrMoreNode(ASTNode exp) {
        super();
        this.operator = Token.createToken('+');
        this.exp = exp;
    }

    @Override
    public ArrayList<ASTNode> getChildren() { return this.children; }
}
