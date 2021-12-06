package com.uwutever.RegexApp.utils.parser.ast;


import com.uwutever.RegexApp.utils.lexer.Token;

import java.util.ArrayList;

public class ConcatenationNode extends ASTNode {

    /**
     * An <code>ASTNode</code> representing the concatenation operation.
     *
     * This node has children, which are stored in <code>left</code> and <code>right</code>,
     * respectively.
     */
    public ConcatenationNode(ASTNode left, ASTNode right) {
        super();
        this.operator = Token.createConcat();
        this.left = left;
        this.right = right;
    }

    @Override
    public ArrayList<ASTNode> getChildren() { return this.children; }
}
