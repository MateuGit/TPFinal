package com.utn.UTNphones.Exceptions;



public class ParametersException extends Exception{
private String error;
    public ParametersException(){
        super();
        this.error="no parameter can be null.";
    }

    @Override
    public String getMessage() {
        return error;
    }
}
