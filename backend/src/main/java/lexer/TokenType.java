package lexer;

/**
 * Types of token
 */
public enum TokenType {
    /**
     * Character token
     */
    Char,
    /**
     * Closure operator, including *,+,?
     */
    Closure,
    /**
     * Operator, including | and .
     */
    Operator,
    /**
     * Left delimiter
     */
    LeftDelimiter,
    /**
     * Right delimiter
     */
    RightDelimiter,
}
