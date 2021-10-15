package com.terraincognita.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Lexer {
    private List<Token> tokens;
    private String inputStr;

    public Lexer(String input) {
        this.inputStr = input;
    }

    private void initializeStack() {
        Stack<String> stack = new Stack<String>();
        
    }

    public void analyze() {
        this.tokens = new ArrayList<Token>();

    }
}
