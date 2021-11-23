package com.uwutever.RegexApp.utils.errors;

import com.uwutever.RegexApp.utils.RegEx;

public class RegexSyntaxException extends RegexException {
    public RegexSyntaxException(String source, int position) {
        super(source, "Syntax error", position);
    }
}