package parser;

import lexer.Token;
import lexer.TokenType;
import parser.ast.ASTNode;
import parser.ast.CharNode;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;

public class Parser {
    /***
     * HashMap storing the precedence of operators.
     */
    private final HashMap<Character, Integer> OP_PREC = new HashMap<Character, Integer>() {{
        put('?', 3);
        put('+', 3);
        put('*', 3);
        put('.', 2);
        put('|', 1);
    }};
    private Hashtable<String, String> config = new Hashtable<String, String>();
    private List<Token> tokens;

    /**
     * Create a <code>Parser</code> object.
     * @param tokens    The list of tokens to be parsed
     * @param config    The configuration of the <code>Parser</code>
     */
    public Parser(List<Token> tokens, Hashtable<String, String> config) {
        this.tokens = tokens;
        this.config = config;
    }

    /**
     * Run the parser.
     * @return Abstract syntax tree from parsing the tokens
     */
    public ASTNode parse() {
        // Create operator and operand stack
        Stack<Token> operators = new Stack<>();
        Stack<ASTNode> operands = new Stack<>();
        // Read the tokens sequentially and construct the AST similar
        // to the process of converting an expression into Reversed Polish Notation
        for (Token currToken : this.tokens) {
            if (currToken.getTokenType() == TokenType.LeftDelimiter) {
                if (currToken.getValue() == '(')
                    operators.push(currToken);
            } else if (currToken.getTokenType() == TokenType.RightDelimiter) {
                if (currToken.getValue() == ')') {
                    Token operatorTop = operators.pop();
                    // Keep popping from the operator stack until a open parenthesis is found
                    while (operatorTop.getValue() != '(' && !operators.empty()) {
                        operands.push(ASTNode.createASTNode(operatorTop, operands));
                        operatorTop = operators.pop();
                    }
                }
            } else if (currToken.getTokenType() == TokenType.Closure ||
                    currToken.getTokenType() == TokenType.Operator) {
                while (!operators.empty()) {
                    Token operatorTop = operators.pop();
                    if (operatorTop.getValue() != '(' &&
                            OP_PREC.get(currToken.getValue()) <= OP_PREC.get(operatorTop.getValue())) {
                        operands.push(ASTNode.createASTNode(operatorTop, operands));
                    } else {
                        operators.push(operatorTop);
                        break;
                    }
                }
                operators.push(currToken);
            } else if (currToken.getTokenType() == TokenType.Char) {
                operands.push(new CharNode(currToken.getValue()));
            }
        }
        // Output the remaining operands
        while (!operators.empty()) {
            Token operatorTop = operators.pop();
            operands.push(ASTNode.createASTNode(operatorTop, operands));
        }
        return operands.pop();
    }


}
