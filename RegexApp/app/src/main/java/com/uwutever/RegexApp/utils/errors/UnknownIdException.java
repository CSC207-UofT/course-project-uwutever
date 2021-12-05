package errors;

public class UnknownIdException extends IllegalArgumentException{
    public UnknownIdException(String id){
        super(String.format("The ID (%s) is unknown to the FSA", id));
    }
}
