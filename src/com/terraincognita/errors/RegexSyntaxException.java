package com.terraincognita.errors;

import com.terraincognita.RegEx;

public class RegexSyntaxException extends RegexException {
    public RegexSyntaxException(RegEx source, int position) {
        super(source, "Syntax error", position);
    }
}
