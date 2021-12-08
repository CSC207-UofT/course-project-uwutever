package com.uwutever.RegexApp.utils.lexer;


import com.uwutever.RegexApp.utils.errors.NotImplementedException;

import java.util.Arrays;
import java.util.HashSet;

public class Token {
    private final TokenType tokenType;
    private final char value;

    protected static final HashSet<Character> LEFT = new HashSet<Character>(Arrays.asList('(', '[', '{'));
    protected static final HashSet<Character> RIGHT = new HashSet<Character>(Arrays.asList(')', ']', '}'));
    protected static final HashSet<Character> OPS = new HashSet<Character>(Arrays.asList('|'));
    protected static final HashSet<Character> QUANTIFIERS = new HashSet<>(Arrays.asList('*', '+', '?'));

    private Token(TokenType tokenType, char value) {
        this.tokenType = tokenType;
        this.value = value;
    }

    public static Token createConcat() {
        return new Token(TokenType.Operator, '.');
    }

    public static Token createToken(char token) {
        if (LEFT.contains(token)) {
            return new Token(TokenType.LeftDelimiter, token);
        } else if (RIGHT.contains(token)) {
            return new Token(TokenType.RightDelimiter, token);
        } else if (OPS.contains(token)) {
            return new Token(TokenType.Operator, token);
        } else if (QUANTIFIERS.contains(token)) {
            if (token == '*')
                return new Token(TokenType.Closure, token);
            else if (token == '+')
                return new Token(TokenType.Closure, token);
            else
                throw new NotImplementedException("Token");
        } else {
            return new Token(TokenType.Char, token);
        }
    }

    public TokenType getTokenType() {
        return this.tokenType;
    }

    public char getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Token) {
            if (((Token) other).getTokenType() == this.getTokenType() && ((Token) other).getValue() == this.getValue())
                return true;
            return false;
        }
        return false;
    }

}