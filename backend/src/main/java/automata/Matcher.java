package automata;

public class Matcher {
    /**
     * Return whether the given fsa accepts text
     * @param fsa the given FSA
     * @param text the given text
     * @return whether nfa accepts text
     */
    public static boolean match(FSA fsa, String text){
        return fsa.accept(text);
    }
}
