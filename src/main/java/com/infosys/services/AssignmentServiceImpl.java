package com.infosys.services;

import com.infosys.exceptions.ServiceException;
import com.infosys.util.TriangleTypes;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by madhu.kundala on 2/5/2018.
 */

@Service
public class AssignmentServiceImpl implements AssignmentService
{

    private static final Logger logger = LoggerFactory.getLogger(AssignmentServiceImpl.class);


    /**
     * This method return the sum of fibonacci series
     *
     * @param inputvalue
     * @return
     * @throws ServiceException
     */
    @Override
    public BigInteger getFibonacciSeries(Integer inputvalue)
            throws ServiceException
    {
        if (null == inputvalue)
        {
            throw new ServiceException("Invalid input " + inputvalue);
        }
        if (inputvalue == 1 || inputvalue == 2)
        {
            return new BigInteger("1");
        }
        if (inputvalue <= 0)
        {
            throw new ServiceException("Invalid input " + inputvalue);
        }
        BigInteger fibo1 = new BigInteger("1");
        BigInteger fibo2 = new BigInteger("1");
        BigInteger fibonacci = new BigInteger("1");
        for (int i = 3;
             i <= inputvalue;
             i++)
        {
            fibonacci = fibo1.add(fibo2);
            fibo1 = fibo2;
            fibo2 = fibonacci;
        }
        logger.debug("fibonacci", fibonacci);
        return fibonacci;
    }

    /**
     * This method return the reverse of words
     *
     * @param inputvalue
     * @return
     * @throws ServiceException
     */
    @Override
    public String getReverseWords(String inputvalue) throws ServiceException, Exception
    {
        if (null == inputvalue || StringUtils.isEmpty(inputvalue))
        {
            throw new ServiceException("Input cannot be null");
        }
        String[] words = inputvalue.split(" ");
        StringBuilder reversedString = new StringBuilder("");
        String result = null;
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
        result = reversedString.toString().trim();
        return result;
    }

    /**
     * This method return the triangle types
     *
     * @param side1
     * @param side2
     * @param side3
     * @return
     * @throws ServiceException
     */
    @Override
    public String getTriangleType(Integer side1, Integer side2, Integer side3)
            throws ServiceException
    {
        if (checkValidTriangle(side1, side2, side3))
        {
            if ((side1 == side2) && (side2 == side3))
            {
                return TriangleTypes.EQUILATERAL.name();
            }
            else if (side1 == side2 || side2 == side3 || side1 == side3)
            {
                return TriangleTypes.ISOSCELES.name();
            }
            else if ((side1 != side2) && (side2 != side3))
            {
                return TriangleTypes.SCALENE.name();
            }
        }
        else
        {
            return TriangleTypes.INVALID.name();
        }
        return TriangleTypes.INVALID.name();
    }

    private boolean checkValidTriangle(Integer a, Integer b, Integer c) throws ServiceException
    {
        boolean result = false;
        if (null == a || null == b || null == c)
        {
            throw new ServiceException("Input cannot be null or empty");
        }

        else if (!(a + b < c || a + c < b || b + c < a))
        {
            result = true;
        }
        return result;
    }

    /**
     * This method return the sum of arrays.
     *
     * @param inputList
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, ArrayList> getMakeOneArray(Collection inputList) throws ServiceException
    {
        Set<Integer> hs = new TreeSet<>();

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

