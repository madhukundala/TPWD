package com.infosys.services;

import com.infosys.exceptions.ServiceException;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.json.simple.parser.ParseException;


public interface AssignmentService
{
    BigInteger getFibonacciSeries(Integer inputValue) throws ServiceException;

    String getReverseWords(String inputValue) throws ServiceException, Exception;

    String getTriangleType(Integer a, Integer b, Integer c) throws ServiceException;

    Map<String, ArrayList> getMakeOneArray(Collection inputValue) throws ServiceException;
     void SechuledMethod() throws IOException, ParseException;
}

