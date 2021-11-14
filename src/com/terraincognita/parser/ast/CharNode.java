package com.terraincognita.parser.ast;

import java.util.ArrayList;

public class CharNode extends ASTNode {
    @Override
    public ArrayList<ASTNode> getChildren() {
        return this.children;
    }
}
