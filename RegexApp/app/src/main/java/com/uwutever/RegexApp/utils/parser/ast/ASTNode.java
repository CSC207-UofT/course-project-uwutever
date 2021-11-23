package com.uwutever.RegexApp.utils.parser.ast;

import com.uwutever.RegexApp.utils.lexer.Token;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public abstract class ASTNode {
    ArrayList<ASTNode> children = new ArrayList<>();

    public ASTNode left;
    public ASTNode right;
    public ASTNode exp;
    public Token operator;

    /**
     * Get the children of the given <code>ASTNode</code>
     * @return The children as an <code>ArrayList</code> of <code>ASTNode</code>
     */
    public abstract ArrayList<ASTNode> getChildren();

    /**
     * Create the appropriate AST node given the operator.
     * @param operator  The operator used to create the <code>ASTNode</code>
     * @param operandStack  The operand stack, which may be modified while creating the <code>ASTNode</code>
     * @return  An object of one of the subclasses of  <code>ASTNode</code>
     */
    public static ASTNode createASTNode(Token operator, Stack<ASTNode> operandStack) {
        if (operator.getValue() == '|') {
            ASTNode left = operandStack.pop();
            ASTNode right = operandStack.pop();
            AlternationNode alt = new AlternationNode(left, right);
            alt.children.add(alt.left);
            alt.children.add(alt.right);
            return alt;
        }
        else if (operator.getValue() == '.') {
            ASTNode left = operandStack.pop();
            ASTNode right = operandStack.pop();
            ConcatenationNode concat = new ConcatenationNode(left, right);
            concat.children.add(concat.left);
            concat.children.add(concat.right);
            return concat;
        }
        else if (operator.getValue() == '*') {
            ASTNode exp = operandStack.pop();
            ZeroOrMoreNode star = new ZeroOrMoreNode(exp);
            star.children.add(star.exp);
            return star;
        }
        else if (operator.getValue() == '+') {
            ASTNode exp = operandStack.pop();
            OneOrMoreNode plus = new OneOrMoreNode(exp);
            plus.children.add(plus.exp);
            return plus;
        }
        else if (operator.getValue() == '?') {
            ASTNode exp = operandStack.pop();
            ZeroOrOneNode question = new ZeroOrOneNode(exp);
            question.children.add(question.exp);
            return question;
        }
        return null;
    }

    /**
     * Get the operator associated with this node (if this is not a CharNode)
     * @return The operator
     */
    public Token getOperator() {
        return this.operator;
    }

    /**
     * Generate a string representation of this AST.
     * @return The AST represented as a string.
     */
    public String prettyPrintAST() {
        return this.prettyPrintAST(new StringBuilder(), "", "");
    }

    /**
     * Generate a string representation of this AST. This implementation is adapted from
     * <a href="https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java">
     *     StackOverflow
     * </a>
     * with some modifications.
     *
     * @param stringBuilder
     * @param prefix
     * @param childrenPrefix
     * @return The string representation of this AST
     */
    private String prettyPrintAST(StringBuilder stringBuilder, String prefix, String childrenPrefix) {
        if (stringBuilder == null) {
            stringBuilder = new StringBuilder();
        }
        stringBuilder.append(prefix);
        stringBuilder.append(this.getClass().getSimpleName() + "\t");
        if (this.children.isEmpty()) {
            if (this instanceof CharNode) {
                stringBuilder.append(((CharNode) this).literal);
                stringBuilder.append('\n');
            }
        }
        else {
            stringBuilder.append(this.operator.getValue());
            stringBuilder.append('\n');
            for (Iterator<ASTNode> it = this.children.iterator(); it.hasNext();) {
                ASTNode nextNode = it.next();
                if (nextNode != null) {
                    if (it.hasNext()) {
                        nextNode.prettyPrintAST(stringBuilder, childrenPrefix + "├── ", childrenPrefix + "│   ");
                    }
                    else {
                        nextNode.prettyPrintAST(stringBuilder, childrenPrefix + "└── ", childrenPrefix + "    ");
                    }
                }
            }
        }
        return stringBuilder.toString();
    }
}
