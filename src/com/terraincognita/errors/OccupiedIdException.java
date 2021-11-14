package com.terraincognita.errors;

public class OccupiedIdException extends IllegalArgumentException{
    public OccupiedIdException(String Id){
        super(String.format("The ID (%s) is occupied in the FSA", Id));
    }
}
