package compiler;

import automata.nfa.NFA;
import automata.nfa.NFAQuintuple;
import parser.ast.ASTNode;

import java.util.HashMap;
import java.util.Map;

public class RegexNFAPattern implements RegexPattern{
    private final String regexStr;
    private final NFA nfa;

    /**
     * Constructor of the class
     * Construct the object by giving a regex pattern string
     * @param regexStr the given regex pattern string
     */
    public RegexNFAPattern(String regexStr){
        this.regexStr = regexStr;

        ASTNode parsedAST = new RegextoASTCompiler(regexStr).compile();
        this.nfa = new ASTtoNFACompiler(parsedAST).compile();
    }

    @Override
    public String getRegexStr() {
        return regexStr;
    }

    @Override
    public NFA getFSA(){
        return this.nfa;
    }

    public NFAQuintuple graph() {
        return new NFAQuintuple(this.nfa);
    }
}
