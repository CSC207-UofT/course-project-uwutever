package errors;

public class UnknownStateIndexException extends IndexOutOfBoundsException{
    public UnknownStateIndexException(int index){
        super(String.format("The index (%s) is unknown to the FSA", index));
    }
}
