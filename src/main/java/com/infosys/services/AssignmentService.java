package com.infosys.services;

import com.infosys.exceptions.ServiceException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


public interface AssignmentService
{
    Long getFibonacciSeries(Integer inputValue) throws ServiceException;

    String getReverseWords(String inputValue) throws ServiceException;

    String getTriangleType(Integer a, Integer b, Integer c) throws ServiceException;

    Map<String, ArrayList> getMakeoneArray(Collection inputValue) throws ServiceException;

}

