package com.infosys.exceptions;

public class ErrorResource
{

    private int errorCode;
    private String errorMessage;

    public ErrorResource(int code, String message)
    {
        this.errorCode = code;
        this.errorMessage = message;
    }

    public ErrorResource()
    {

    }

    public int getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(int errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }
}
