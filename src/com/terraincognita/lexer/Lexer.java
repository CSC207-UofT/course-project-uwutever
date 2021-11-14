package com.terraincognita.lexer;

import com.terraincognita.errors.RegexSyntaxException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Lexer {
    private List<Token> tokens;
    private String inputStr;

    // for testing purpose only, remove in production.
    public static void main(String[] args) {
        Lexer lexer = new Lexer("((xx))**");
        lexer.tokenize();
        for (Token t: lexer.getTokens()) {
            System.out.print(t.getTokenType() + " " + t.getValue());
            System.out.println();
        }
    }

    public Lexer(String input) {
        this.inputStr = input;
    }

    public void tokenize() {
        Stack<Token> stack = new Stack<Token>();
        this.tokens = new ArrayList<Token>();

        for (int i = 0; i < this.inputStr.length(); i++) {
            char currentChar = this.inputStr.charAt(i);
            Token currentToken = Token.createToken(currentChar);
            switch (currentToken.getTokenType()) {
                case LeftDelimiter:
                    stack.push(currentToken);
                    this.tokens.add(currentToken);
                    break;
                case RightDelimiter:
                    if (stack.empty())
                        throw new RegexSyntaxException(this.inputStr, i);
                    else {
                        char top = stack.pop().getValue();
                        if (top != '(') {
                            // shift by two (ASCII) to get the corresponding closing bracket
                            if (currentChar != top + 2)
                                throw new RegexSyntaxException(this.inputStr, i);
                            else {
                                this.tokens.add(currentToken);
                            }
                        }
                        else {
                            if (currentChar != ')')
                                throw new RegexSyntaxException(this.inputStr, i);
                            else
                                this.tokens.add(currentToken);
                        }
                    }
                    break;
                default:
                    this.tokens.add(currentToken);
            }
        }
        if (!stack.empty()) {
            throw new RegexSyntaxException(this.inputStr, this.inputStr.length()-1);
        }
    }

    public List<Token> getTokens() {
        return this.tokens;
    }
}