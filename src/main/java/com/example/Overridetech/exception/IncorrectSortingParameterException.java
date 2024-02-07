package com.example.Overridetech.exception;

public class IncorrectSortingParameterException extends RuntimeException {
    private String message;

    public IncorrectSortingParameterException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
