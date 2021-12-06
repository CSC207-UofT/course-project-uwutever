package lexer;


import errors.RegexSyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Lexer {
    private List<Token> tokens;
    private final String inputStr;

    // for testing purpose only, remove in production.
    public static void main(String[] args) {
        Lexer lexer = new Lexer("((a)+b*)+e+|f*(g**)+");
        lexer.tokenize();
        for (Token t: lexer.getTokens()) {
            System.out.print(t.getTokenType() + " " + t.getValue());
            System.out.println();
        }
    }

    /**
     * Initialize a <code>Lexer</code>. Note that this constructor only constructs the <code>Lexer</code>
     * without actually tokenize the input.
     * @param input     The expression in plain string.
     */
    public Lexer(String input) {
        this.inputStr = input;
    }

    /**
     * Tokenize the string input into a list of tokens.
     */
    public void tokenize() {
        Stack<Token> stack = new Stack<>();
        this.tokens = new ArrayList<>();

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

    /**
     * Insert the concatenation operators for the following cases:
     *  - closure and something that is not right parentheses, operator, or closure
     *  - pair of characters
     *  - character and left parentheses
     *  - right parentheses and character
     *  - right parentheses and left parentheses
     *
     * For example, an expression of "(ab*)+c*|d" will become "(a.b*)+.c*|d" after the insertions.
     */
    private void insertConcat() {
        for (int i = this.tokens.size() - 1; i > 0; i-=2) {
            TokenType right = tokens.get(i).getTokenType();
            TokenType left = tokens.get(i-1).getTokenType();
            if (left == TokenType.Closure &&
                    !(right == TokenType.RightDelimiter || right == TokenType.Operator || right == TokenType.Closure)) {
                tokens.add(i, Token.createConcat());
            }
            else if (right == TokenType.Char || right == TokenType.LeftDelimiter) {
                if (left == TokenType.Char || left == TokenType.RightDelimiter) {
                    tokens.add(i, Token.createConcat());
                }
            }
        }
    }

    /**
     * Get the tokens as a list.
     * @return <code>List</code> containing all tokens
     */
    public List<Token> getTokens() {
        return this.tokens;
    }
}