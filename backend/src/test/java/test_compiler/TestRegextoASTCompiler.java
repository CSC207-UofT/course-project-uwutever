package test_compiler;

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
public class TestRegextoASTCompiler {

    private RegextoASTCompiler compiler;

    @Before
    public void setUp() {
        ASTNode parsedAST = new RegextoASTCompiler("(a|b)cd*").compile();
        compiler = new RegextoASTCompiler("(a|b)cd*");
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testCompiler() {
        // TODO
    }
}
