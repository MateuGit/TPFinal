package com.utn.UTNphones.exceptions.cityExceptions;

public abstract class CityExceptions extends RuntimeException {
    private final String message;

    public CityExceptions(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}