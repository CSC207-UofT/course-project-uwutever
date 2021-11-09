package com.terraincognita.errors;

import com.terraincognita.RegEx;

public class RegexSyntaxException extends RegexException {
    public RegexSyntaxException(String source, int position) {
        super(source, "Syntax error", position);
    }
}
