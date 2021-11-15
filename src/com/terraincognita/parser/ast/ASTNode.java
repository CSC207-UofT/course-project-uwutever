package com.terraincognita.parser.ast;

import com.terraincognita.lexer.Token;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public abstract class ASTNode {
    ArrayList<ASTNode> children = new ArrayList<>();

    public ASTNode left;
    public ASTNode right;
    public ASTNode exp;
    public Token operator;

    public abstract ArrayList<ASTNode> getChildren();

    public static ASTNode createASTNode(Token operator, Stack<ASTNode> operandStack) {
        if (operator.getValue() == '|') {
            AlternationNode alt = new AlternationNode();
            alt.left = operandStack.pop();
            alt.right = operandStack.pop();
            alt.children.add(alt.left);
            alt.children.add(alt.right);
            return alt;
        }
        else if (operator.getValue() == '.') {
            ConcatenationNode concat = new ConcatenationNode();
            concat.left = operandStack.pop();
            concat.right = operandStack.pop();
            concat.children.add(concat.left);
            concat.children.add(concat.right);
            return concat;
        }
        else if (operator.getValue() == '*') {
            ZeroOrMoreNode star = new ZeroOrMoreNode();
            star.exp = operandStack.pop();
            star.children.add(star.exp);
            return star;
        }
        else if (operator.getValue() == '+') {
            OneOrMoreNode plus = new OneOrMoreNode();
            plus.exp = operandStack.pop();
            plus.children.add(plus.exp);
            return plus;
        }
        else if (operator.getValue() == '?') {
            ZeroOrOneNode question = new ZeroOrOneNode();
            question.exp = operandStack.pop();
            question.children.add(question.exp);
            return question;
        }
        return null;
    }
    public Token getOperator() {
        return this.operator;
    }

    public String prettyPrintAST() {
        return this.prettyPrintAST(new StringBuilder(), "", "");
    }
    // https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java
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
