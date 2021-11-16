package com.terraincognita;

import com.terraincognita.automata.dfa.DFA;
import com.terraincognita.compiler.ASTtoNFACompiler;
import com.terraincognita.compiler.RegextoASTCompiler;

public class CompiledRegEx {
    private DFA dfa;
    private String regexStr;

    public CompiledRegEx(String regexStr){

    }

    public DFA getDfa() {
        return dfa;
    }
}
