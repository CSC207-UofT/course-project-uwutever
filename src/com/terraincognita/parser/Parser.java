package com.terraincognita.parser;

import com.terraincognita.automata.NFA;
import com.terraincognita.lexer.Token;

import java.util.Hashtable;
import java.util.List;

public class Parser {
    private Hashtable<String, String> config = new Hashtable<String, String>();
    private List<Token> tokens;

    public Parser(List<Token> tokens, Hashtable<String, String> config) {
        this.tokens = tokens;
        this.config = config;
    }

    public NFA parse() {
        throw new UnsupportedOperationException();
    }
}
