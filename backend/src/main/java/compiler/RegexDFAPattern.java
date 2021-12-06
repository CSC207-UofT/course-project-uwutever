package compiler;

import automata.dfa.DFA;
import automata.dfa.DFAQuintuple;
import automata.dfa.NFAtoDFAConverter;
import automata.nfa.NFA;
import parser.ast.ASTNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegexDFAPattern implements RegexPattern{
    private final String regexStr;
    private final DFA dfa;

    /**
     * Constructor of the class
     * Construct the object by giving a regex pattern string
     * @param regexStr the given regex pattern string
     */
    public RegexDFAPattern(String regexStr){
        this.regexStr = regexStr;

        ASTNode parsedAST = new RegextoASTCompiler(regexStr).compile();
        NFA nfa = new ASTtoNFACompiler(parsedAST).compile();

        this.dfa = new NFAtoDFAConverter(nfa).convert();
    }

    @Override
    public String getRegexStr() {
        return regexStr;
    }

    @Override
    public DFA getFSA(){
        return this.dfa;
    }

    public DFAQuintuple graph() {
        return new DFAQuintuple(this.dfa);
    }
}
