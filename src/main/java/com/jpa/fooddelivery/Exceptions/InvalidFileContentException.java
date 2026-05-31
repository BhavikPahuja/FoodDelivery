package com.jpa.fooddelivery.Exceptions;

public class InvalidFileContentException extends RuntimeException{
    public InvalidFileContentException(String message) {
        super(message);
    }
    public InvalidFileContentException() {
        super("Invalid file content");
    }
}
