package automata.nfa;

/**
 * This class implements the 3 basic operations:
 * - Union
 * - Concatenation
 * - kleene star closure
 */
public class NFABasicOperation {
    protected NFABuilder builder;

    /**
     * Constructor of the NFAOperation class with a new NFABuilder
     */
    public NFABasicOperation(){
        this.builder = new NFABuilder();
    }

    public NFA union(NFA midNFA1, NFA midNFA2){
        this.builder.reset();

        int start = builder.addNewState();
        builder.setStartState(start);

        builder.addTransition(start, "epsilon", midNFA1);
        builder.addTransition(start, "epsilon", midNFA2);

        int end = builder.addNewState();
        builder.setAcceptingState(end);
        builder.addTransition(midNFA1, "epsilon", end);
        builder.addTransition(midNFA2, "epsilon", end);

        return this.builder.getResult();
    }

    public NFA concatenation(NFA midNFA1, NFA midNFA2){
        this.builder.reset();

        int start = builder.addNewState();
        builder.setStartState(start);

        builder.addTransition(start, "epsilon", midNFA1);
        builder.addTransition(midNFA1, "epsilon", midNFA2);

        int end = builder.addNewState();
        builder.setAcceptingState(end);
        builder.addTransition(midNFA2, "epsilon", end);

        return this.builder.getResult();
    }

    public NFA kleeneStar(NFA midNFA){
        this.builder.reset();

        int start = builder.addNewState();
        builder.setStartState(start);
        builder.setAcceptingState(start);

        builder.addTransition(start, "epsilon", midNFA);
        builder.addTransition(midNFA, "epsilon", start);

        return builder.getResult();
    }
}
