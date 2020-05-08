package com.kvitkov.gameofcities;

public class DeveloperException extends RuntimeException {
    public DeveloperException() {super("Invalid developer");}


    public DeveloperException(String message) {super("Invalid developer: " + message);}


    public DeveloperException(Throwable cause) {super(cause);}


    public DeveloperException(String message, Throwable cause) {super("Invalid developer: " + message, cause);}
}
