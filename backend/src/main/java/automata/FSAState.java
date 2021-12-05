package automata;

public abstract class FSAState{
    /**
     * Return the output of the delta function with the given alphabet
     * Return null if there is no transition for the given alphabet
     *
     * @return the output of the delta function
     */
    public abstract Object delta(String alphabet);
}
