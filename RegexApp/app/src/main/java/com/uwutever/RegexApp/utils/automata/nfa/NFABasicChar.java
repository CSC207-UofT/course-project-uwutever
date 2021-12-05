package automata.nfa;

/**
 * This class implements 2 basic NFA character:
 * - epsilon
 * - single alphabet
 */
public class NFABasicChar {
    private final NFABuilder builder;

    /**
     * Constructor of this class
     */
    public NFABasicChar(){
        this.builder = new NFABuilder();
    }

    public NFA epsilonChar(){
        this.builder.reset();

        int start = builder.addNewState();
        builder.setStartState(start);
        builder.setAcceptingState(start);

        return builder.getResult();
    }

    public NFA singleAlphabet(String alphabet){
        this.builder.reset();

        int start = builder.addNewState();
        int end = builder.addNewState();

        builder.setStartState(start);
        builder.setAcceptingState(end);

        builder.addTransition(start, alphabet, end);

        return builder.getResult();
    }
}
