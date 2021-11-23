package com.uwutever.RegexApp.utils.errors;

import com.uwutever.RegexApp.utils.RegEx;

public abstract class RegexException extends RuntimeException {
    public RegexException(RegEx source, String message, int location) {
        super(message);
    }
    public RegexException(String source, String message, int location) {
        super(message);
    }
}
