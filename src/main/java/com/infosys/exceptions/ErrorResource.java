package com.infosys.exceptions;

public class ErrorResource
{

    private int code;
    private String message;

    public ErrorResource(int code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public ErrorResource()
    {

    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

}
