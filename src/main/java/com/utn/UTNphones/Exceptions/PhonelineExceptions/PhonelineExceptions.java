package com.utn.UTNphones.Exceptions.PhonelineExceptions;

public abstract class PhonelineExceptions extends Exception {
    private String message;

    public PhonelineExceptions(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}