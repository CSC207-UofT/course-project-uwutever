package com.uwutever.RegexApp.utils.compiler;

import com.uwutever.RegexApp.utils.automata.dfa.DFA;
import com.uwutever.RegexApp.utils.automata.dfa.DFAQuintuple;
import com.uwutever.RegexApp.utils.automata.dfa.NFAtoDFAConverter;
import com.uwutever.RegexApp.utils.automata.nfa.NFA;
import com.uwutever.RegexApp.utils.parser.ast.ASTNode;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

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
