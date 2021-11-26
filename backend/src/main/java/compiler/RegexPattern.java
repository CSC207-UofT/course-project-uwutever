package compiler;

import automata.FSA;
import automata.nfa.NFA;
import parser.ast.ASTNode;

public class RegexPattern {
    private final String regexStr;
    private final String FSAType;
    private final FSA fsa;

    public RegexPattern(String regexStr){
        this.regexStr = regexStr;

        ASTNode parsedAST = new RegextoASTCompiler(regexStr).compile();
        NFA nfa = new ASTtoNFACompiler(parsedAST).compile();
        this.FSAType = "NFA";
        this.fsa = nfa;
    }

    public String getRegexStr() {
        return regexStr;
    }

    public FSA getFsa() {
        return fsa;
    }

    public String getFSAType(){
        return this.FSAType;
    }
}
