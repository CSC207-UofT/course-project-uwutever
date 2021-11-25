package errors;

public class UnknownAlphabetException extends IllegalArgumentException{
    public UnknownAlphabetException(String alphabet){
        super(String.format("The alphabet (%s) is not in the FSA alphabet set", alphabet));
    }
}
