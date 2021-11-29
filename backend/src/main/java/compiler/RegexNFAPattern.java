package compiler;

import automata.nfa.NFA;
import automata.nfa.NFAQuintuple;
import parser.ast.ASTNode;

import java.util.HashMap;
import java.util.Map;

public class RegexNFAPattern implements RegexPattern{
    private final String regexStr;
    private final NFA nfa;

    public RegexNFAPattern(String regexStr){
        this.regexStr = regexStr;

        ASTNode parsedAST = new RegextoASTCompiler(regexStr).compile();
        this.nfa = new ASTtoNFACompiler(parsedAST).compile();
    }

    public String getRegexStr() {
        return regexStr;
    }

    @Override
    public Map<String, Object> getFSAQuintuple() {
        NFAQuintuple quintuple = new NFAQuintuple(this.nfa);
        Map<String, Object> ret = new HashMap<>();

        ret.put("states", quintuple.states);
        ret.put("alphabets", quintuple.alphabets);
        ret.put("start state" , quintuple.startState);
        ret.put("accepting state", quintuple.acceptingStates);
        ret.put("transitions", quintuple.transitionTable);

        return ret;
    }
}
