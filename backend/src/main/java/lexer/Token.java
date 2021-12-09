package lexer;


import errors.NotImplementedException;

import java.util.Arrays;
import java.util.HashSet;

public class Token {
    private final TokenType tokenType;
    private final char value;

    protected static final HashSet<Character> LEFT = new HashSet<Character>(Arrays.asList('(', '[', '{'));
    protected static final HashSet<Character> RIGHT = new HashSet<Character>(Arrays.asList(')', ')', '}'));
    protected static final HashSet<Character> OPS = new HashSet<Character>(Arrays.asList('|'));
    protected static final HashSet<Character> QUANTIFIERS = new HashSet<>(Arrays.asList('*', '+', '?'));

    private Token(TokenType tokenType, char value) {
        this.tokenType = tokenType;
        this.value = value;
    }

    /**
     * Create the special concatenation operator
     * @return a concatenation operator token
     */
    public static Token createConcat() {
        return new Token(TokenType.Operator, '.');
    }

    /**
     * Given a token of type char, return the correct token
     * @param token: character representation of the token
     * @return the Token
     */
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

    /**
     * Get the type of this Token
     * @return type of Token
     */
    public TokenType getTokenType() {
        return this.tokenType;
    }

    /**
     * Get the character representation of this Token
     * @return character representation of this Toke
     */
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