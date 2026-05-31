package com.jpa.fooddelivery.Exceptions;

public class FileUploadException extends RuntimeException{
    public FileUploadException(String message){
        super(message);
    }
    public FileUploadException(){
        super("Error while uploading file");
    }
}
