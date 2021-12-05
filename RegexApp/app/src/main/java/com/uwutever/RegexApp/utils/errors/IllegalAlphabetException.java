package errors;

public class IllegalAlphabetException extends IllegalArgumentException{
    public IllegalAlphabetException(String alphabet){
        super(String.format("The alphabet (%s) is illegal for this FSA", alphabet));
    }
}
