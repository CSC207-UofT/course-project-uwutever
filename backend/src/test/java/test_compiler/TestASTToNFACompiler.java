package test_compiler;

import compiler.ASTtoNFACompiler;
import compiler.RegextoASTCompiler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import parser.ast.ASTNode;

import static org.junit.Assert.*;


/**
 * Assumes that all steps up to AST construction is fault-less.
 * Tests construction of NFA when given AST.
 */
public class TestASTToNFACompiler {

    private ASTtoNFACompiler compiler;

    @Before
    public void setUp() {
        ASTNode parsedAST = new RegextoASTCompiler("(a|b)cd*").compile();
        compiler = new ASTtoNFACompiler(parsedAST);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testCompiler() {
        assertTrue(compiler.compile().accept("acd"));
    }
}
