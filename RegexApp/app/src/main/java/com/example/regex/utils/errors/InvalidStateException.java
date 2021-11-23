package com.terraincognita.errors;

public class InvalidStateException extends IllegalArgumentException{
    public InvalidStateException(Object state){
        super(String.format("The state (%s) is not in the FSA", state));
    }
}