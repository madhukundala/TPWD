package com.infosys.services;

/**
 * Created by madhu.kundala on 2/5/2018.
 */

import com.infosys.exceptions.ServiceException;
import com.infosys.exceptions.TriangleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Service
public class AssignmentServiceImpl implements AssignmentService
{

    private static final Logger logger = LoggerFactory.getLogger(AssignmentServiceImpl.class);
    @Autowired
    RestTemplate restTemplate;


    /**
     * This method return the sum of fibonacci series
     *
     * @param inputvalue
     * @return
     * @throws ServiceException
     */
    @Override
    public Long getFibonacciSeries(Integer inputvalue)
            throws ServiceException
    {
        if (null == inputvalue)
        {
            throw new ServiceException("Invalid Value");
        }

        if (inputvalue == 1 || inputvalue == 2)
        {
            return Long.valueOf(1);
        }
        int fibo1 = 1;
        int fibo2 = 1;
        int fibonacci = 1;
        for (int i = 3;
             i <= inputvalue;
             i++)
        {
            fibonacci = fibo1 + fibo2;
            fibo1 = fibo2;
            fibo2 = fibonacci;
        }
        logger.debug("fibonacci", fibonacci);
        return Long.valueOf(fibonacci);
    }

    /**
     * This method return the reverse of words
     *
     * @param inputvalue
     * @return
     * @throws ServiceException
     */
    @Override
    public String getReverseWords(String inputvalue) throws ServiceException
    {
        if (null == inputvalue)
        {
            throw new ServiceException("Invalid Value");
        }
        String[] words = inputvalue.split(" ");
        StringBuilder reversedString = new StringBuilder("");

        for (int i = 0;
             i < words.length;
             i++)
        {
            String word = words[i];
            StringBuilder reverseWord = new StringBuilder("");
            for (int j = word.length() - 1;
                 j >= 0;
                 j--)
            {
                reverseWord = reverseWord.append(word.charAt(j));
            }
            reversedString = reversedString.append(reverseWord + " ");
        }
        return reversedString.toString().trim();
    }

    /**
     * This method return the triangle types
     *
     * @param a
     * @param b
     * @param c
     * @return
     * @throws ServiceException
     */
    @Override
    public String getTriangleType(Integer a, Integer b, Integer c)
            throws ServiceException
    {
        if (a <= 0 || b <= 0 || c <= 0)
        {
            return TriangleTypes.INVALID.name();
        }
        else if (a == b && b == c)
        {
            return TriangleTypes.EQUILATERAL.name();
        }
        else if (b == c || a == b || c == a)
        {
            return TriangleTypes.ISOSCELES.name();
        }
        else if(a!=b && b!=c && c!=a)
        {
            return TriangleTypes.SCALENE.name();
        }
        return TriangleTypes.INVALID.name();
    }

    /**
     * This method return the sum of arrays.
     *
     * @param inputList
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, ArrayList> getMakeoneArray(Collection inputList) throws ServiceException
    {
        Set<Integer> hs = new HashSet<>();

        Collection<ArrayList> collection = inputList;
        for (ArrayList arrayList : collection)
        {
            for (int i = 0;
                 i < arrayList.size();
                 i++)
            {
                hs.add((Integer) arrayList.get(i));
            }
        }
        Map<String, ArrayList> listLinkedHashMap = new LinkedHashMap<>();
        listLinkedHashMap.put("Array", new ArrayList(hs));
        return listLinkedHashMap;
    }
}

