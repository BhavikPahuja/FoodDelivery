package com.jpa.fooddelivery.Exceptions;

public class InvalidFilePathException extends RuntimeException {

    public InvalidFilePathException(String message) {
        super(message);
    }
    public InvalidFilePathException() {
        super("Invalid path");
    }
}
