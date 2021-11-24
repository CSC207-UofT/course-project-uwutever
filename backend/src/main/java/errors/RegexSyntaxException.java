package errors;

public class RegexSyntaxException extends RegexException {
    public RegexSyntaxException(String source, int position) {
        super(source, "Syntax error", position);
    }
}