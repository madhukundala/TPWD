package com.infosys.exceptions;

public class ServiceException extends Exception
{

    private static final long serialVersionUID = -1669907120996449023L;

    public ServiceException()
    {

    }

    public ServiceException(String string)
    {
        super(string);
    }

    public ServiceException(Exception exception)
    {
        super(exception);
    }

    public ServiceException(String string, Exception exception)
    {
        super(string, exception);
    }

}
