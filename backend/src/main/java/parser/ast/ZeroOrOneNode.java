package parser.ast;

import lexer.Token;

import java.util.ArrayList;

public class ZeroOrOneNode extends ASTNode {

    /**
     * An <code>ASTNode</code> representing the '?' operation that matches a given character zero or one time.
     *
     * This node has only one child, which is stored in the <code>exp</code> field.
     */
    public ZeroOrOneNode(ASTNode exp) {
        super();
        this.operator = Token.createToken('?');
        this.exp = exp;
    }

    @Override
    public ArrayList<ASTNode> getChildren() { return this.children; }
}
