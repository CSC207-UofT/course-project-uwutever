package com.terraincognita.compiler;

import com.terraincognita.parser.ast.*;
import com.terraincognita.automata.nfa.*;

public class Compiler {

    private final Object tree;

    //    Construct a NFA from a corresponding AST

    public Compiler(Object T) {
        // TODO!
        this.tree = T;



    }

    public NFA constructNFA(Object T) {
        NFA nfa = new NFA();
        return nfa;
    }

}
