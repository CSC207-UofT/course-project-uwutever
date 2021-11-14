package com.terraincognita.errors;

import com.terraincognita.RegEx;

public abstract class RegexException extends RuntimeException {
    public RegexException(RegEx source, String message, int location) {
        super(message);
    }
    public RegexException(String source, String message, int location) {
        super(message);
    }
}
