import errors.RegexSyntaxException;
import lexer.Lexer;
import lexer.Token;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class TestLexer {


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testMatchedDelim() {
        String inputRegex = "(())";
        Lexer lexer = new Lexer(inputRegex);
        lexer.tokenize();
        ArrayList<Token> expected = new ArrayList<Token>();
        expected.add(Token.createToken('('));
        expected.add(Token.createToken('('));
        expected.add(Token.createToken(')'));
        expected.add(Token.createToken(')'));
        assert lexer.getTokens().equals(expected);
    }

    @Test(expected = RegexSyntaxException.class)
    public void testUnmatchedDelim() {
        String inputRegex = "(()";
        Lexer lexer = new Lexer(inputRegex);
        lexer.tokenize();
    }

    @After
    public void throwDown() throws Exception {

    }
}
