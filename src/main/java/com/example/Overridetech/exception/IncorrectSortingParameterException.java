package com.example.Overridetech.exception;

public class IncorrectSortingParameterException extends RuntimeException {
    private String parameter;

    public IncorrectSortingParameterException(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
