package errors;

public class NullStartStateException extends Exception{
    public NullStartStateException(){
        super("The start state of this FSA is not set");
    }
}
