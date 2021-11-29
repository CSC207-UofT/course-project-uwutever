package compiler;

import automata.FSA;

import java.util.Map;

public interface RegexPattern {
    String getRegexStr();
    Map<String, Object> getFSAQuintuple();
}
