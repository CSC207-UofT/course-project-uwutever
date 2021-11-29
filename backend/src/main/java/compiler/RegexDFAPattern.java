package compiler;

import automata.dfa.DFA;
import automata.dfa.NFAtoDFAConverter;
import automata.nfa.NFA;
import parser.ast.ASTNode;

public class RegexDFAPattern implements RegexPattern{
    private final String regexStr;
    private final DFA dfa;

    public RegexDFAPattern(String regexStr){
        this.regexStr = regexStr;

        ASTNode parsedAST = new RegextoASTCompiler(regexStr).compile();
        NFA nfa = new ASTtoNFACompiler(parsedAST).compile();

        this.dfa = new NFAtoDFAConverter(nfa).convert();
    }

    public String getRegexStr() {
        return regexStr;
    }

    @Override
    public DFA getFSA() {
        return dfa;
    }
}
