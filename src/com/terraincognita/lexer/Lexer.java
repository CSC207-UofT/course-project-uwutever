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

    /**
     * Tokenize the string input into a list of tokens.
     */
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
            this.insertConcat();
        }
        if (!stack.empty()) {
            throw new RegexSyntaxException(this.inputStr, this.inputStr.length()-1);
        }
    }

    private void insertConcat() {
        for (int i = this.tokens.size() - 1; i > 0; i-=2) {
            if (tokens.get(i).getTokenType() == TokenType.Char &&
                    tokens.get(i-1).getTokenType() == TokenType.Char) {
                tokens.add(i, Token.createConcat());
            }
            else if (tokens.get(i).getTokenType() == TokenType.Char &&
                    tokens.get(i-1).getTokenType() == TokenType.RightDelimiter) {
                tokens.add(i, Token.createConcat());
            }
        }
    }

    public List<Token> getTokens() {
        return this.tokens;
    }
}