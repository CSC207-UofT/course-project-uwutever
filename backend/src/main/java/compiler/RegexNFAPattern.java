package compiler;

import automata.nfa.NFA;
import parser.ast.ASTNode;

public class RegexNFAPattern implements RegexPattern{
    private final String regexStr;
    private final NFA nfa;

    public RegexNFAPattern(String regexStr){
        this.regexStr = regexStr;

        ASTNode parsedAST = new RegextoASTCompiler(regexStr).compile();
        this.nfa = new ASTtoNFACompiler(parsedAST).compile();
    }

    public String getRegexStr() {
        return regexStr;
    }

    @Override
    public NFA getFSA() {
        return nfa;
    }
}
