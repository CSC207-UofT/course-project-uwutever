package com.uwutever.RegexApp.utils.parser.ast;


import com.uwutever.RegexApp.utils.lexer.Token;

import java.util.ArrayList;

public class AlternationNode extends ASTNode {

    /**
     * An <code>ASTNode</code> representing the alternation ('|') operation.
     *
     * This node has two children, which are stored in <code>left</code> and <code>right</code>,
     * respectively.
     */
    public AlternationNode(ASTNode left, ASTNode right) {
        super();
        this.operator = Token.createToken('|');
        this.left = left;
        this.right = right;
    }

    public ArrayList<ASTNode> getChildren() { return this.children; }
}
