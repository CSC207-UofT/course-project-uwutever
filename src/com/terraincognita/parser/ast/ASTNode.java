package com.terraincognita.parser.ast;

import com.terraincognita.lexer.Token;

import java.util.ArrayList;
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
            return alt;
        }
        else if (operator.getValue() == '.') {
            ConcatenationNode concat = new ConcatenationNode();
            concat.left = operandStack.pop();
            concat.right = operandStack.pop();
            return concat;
        }
        else if (operator.getValue() == '*') {
            ZeroOrMoreNode star = new ZeroOrMoreNode();
            star.exp = operandStack.pop();
            return star;
        }
        else if (operator.getValue() == '+') {
            OneOrMoreNode plus = new OneOrMoreNode();
            plus.exp = operandStack.pop();
            return plus;
        }
        else if (operator.getValue() == '?') {
            ZeroOrOneNode question = new ZeroOrOneNode();
            question.exp = operandStack.pop();
            return question;
        }
        return null;
    }
    public Token getOperator() {
        return this.operator;
    }
}
