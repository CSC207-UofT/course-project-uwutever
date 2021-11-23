package com.uwutever.RegexApp.utils;

import com.uwutever.RegexApp.utils.automata.dfa.DFA;
import com.uwutever.RegexApp.utils.compiler.ASTtoNFACompiler;
import com.uwutever.RegexApp.utils.compiler.RegextoASTCompiler;

public class CompiledRegEx {
    private DFA dfa;
    private String regexStr;

    public CompiledRegEx(String regexStr){

    }

    public DFA getDfa() {
        return dfa;
    }
}
