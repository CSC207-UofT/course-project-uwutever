package com.terraincognita.parser;

import com.terraincognita.lexer.Token;
import com.terraincognita.lexer.TokenType;
import com.terraincognita.parser.ast.ASTNode;
import com.terraincognita.parser.ast.CharNode;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;

public class Parser {
    private final HashMap<Character, Integer> OP_PREC = new HashMap<>() {{
        put('?', 3);
        put('+', 3);
        put('*', 3);
        put('.', 2);
        put('|', 1);
    }};
    private Hashtable<String, String> config = new Hashtable<String, String>();
    private List<Token> tokens;

    public Parser(List<Token> tokens, Hashtable<String, String> config) {
        this.tokens = tokens;
        this.config = config;
    }

    public ASTNode parse() {
        Stack<Token> operators = new Stack<>();
        Stack<ASTNode> operands = new Stack<>();
        for (int i = 0; i < this.tokens.size(); i++) {
            Token currToken = this.tokens.get(i);
            if (currToken.getTokenType() == TokenType.LeftDelimiter) {
                if (currToken.getValue() == '(')
                    operators.push(currToken);
            }
            else if (currToken.getTokenType() == TokenType.RightDelimiter) {
                if (currToken.getValue() == ')') {
                    Token operatorTop = operators.pop();
                    while (operatorTop.getValue() != '(' && !operators.empty()) {
                        operands.push(ASTNode.createASTNode(operatorTop, operands));
                        operatorTop = operators.pop();
                    }
                }
            }
            else if (currToken.getTokenType() == TokenType.Closure ||
                    currToken.getTokenType() == TokenType.Operator) {
                while (!operators.empty()) {
                    Token operatorTop = operators.pop();
                    if (operatorTop.getValue() != '(' &&
                            OP_PREC.get(currToken.getValue()) <= OP_PREC.get(operatorTop.getValue())) {
                        operands.push(ASTNode.createASTNode(operatorTop, operands));
                    }
                    else {
                        operators.push(operatorTop);
                        break;
                    }
                }
                operators.push(currToken);
            }
            else if (currToken.getTokenType() == TokenType.Char) {
                operands.push(new CharNode(currToken.getValue()));
            }
        }
        while (!operators.empty()) {
            Token operatorTop = operators.pop();
            operands.push(ASTNode.createASTNode(operatorTop, operands));
        }
        return operands.pop();
    }


}
