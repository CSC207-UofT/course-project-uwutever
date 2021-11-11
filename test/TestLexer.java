import com.terraincognita.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestMatch {


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testUnmatchedDelim() {
        String inputRegex = "(())";
        Lexer lexer = new Lexer(inputRegex);
        ArrayList<Token> expected = new ArrayList<Token>();
        expected.add(new Token(TokenType.LeftDelimiter, '('));
        expected.add(new Token(TokenType.LeftDelimiter, '('));
        expected.add(new Token(TokenType.RightDelimiter, ')'));
        expected.add(new Token(TokenType.RightDelimiter, ')'));
        assert lexer.getTokens.equals(expected);
    }
}
