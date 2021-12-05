package parser.ast;

import java.util.ArrayList;

public class CharNode extends ASTNode {
    public char literal;

    /**
     * A character node. This is always the leaf of an AST.
     *
     * It does not contain any children and the character literal is stored in the field <code>literal</code>
     * @param literal
     */
    public CharNode(char literal) {
        super();
        this.literal = literal;
    }

    /**
     * Get the literal associated with this node.
     * @return The character literal.
     */
    public char getLiteral() { return this.literal; }

    @Override
    public ArrayList<ASTNode> getChildren() {
        return null;
    }
}
