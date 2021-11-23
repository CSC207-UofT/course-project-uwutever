package com.uwutever.RegexApp.utils;

public class Match {
    private int start = 0;
    private int end = 0;
    private String content = "";

    public Match(int start, int end, String content) {
        this.start = start;
        this.end = end;
        this.content = content;
    }

    public int getStart() { return this.start; }

    public int getEnd() { return this.end; }

    public String getContent() { return this.content; }
}
