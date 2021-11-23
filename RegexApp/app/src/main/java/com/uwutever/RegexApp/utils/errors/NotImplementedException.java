package com.uwutever.RegexApp.utils.errors;

public class NotImplementedException extends RuntimeException {
    private String className;

    public NotImplementedException(String srcClass) {
        super("Method/Function not implemented in {}".formatted(srcClass));
        this.className = srcClass;
    }

    public String getClassName() { return this.className; }
}
