package com.infosys.services;

/**
 * Created by madhu.kundala on 2/5/2018.
 */

import com.infosys.exceptions.ServiceException;
import com.infosys.exceptions.TriangleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

@Service
public class AssignmentServiceImpl implements AssignmentService
{

    @Autowired
    RestTemplate restTemplate;


    /**
     * @param inputvalue
     * @return
     * @throws ServiceException
     */
    @Override
    public Long getFibonacciSeries(Integer inputvalue)
            throws ServiceException
    {
        if (inputvalue == 1 || inputvalue == 2)
        {
            return Long.valueOf(1);
        }
        int fibo1 = 1, fibo2 = 1, fibonacci = 1;
        for (int i = 3;
             i <= inputvalue;
             i++)
        {
            fibonacci = fibo1 + fibo2;
            fibo1 = fibo2;
            fibo2 = fibonacci;
        }
        return Long.valueOf(fibonacci);
    }

    /**
     * @param inputvalue
     * @return
     * @throws ServiceException
     */
    @Override
    public String getReverseWords(String inputvalue) throws ServiceException
    {
        String[] words = inputvalue.split(" ");
        String reversedString = "";
        for (int i = 0;
             i < words.length;
             i++)
        {
            String word = words[i];
            String reverseWord = "";
            for (int j = word.length() - 1;
                 j >= 0;
                 j--)
            {
                reverseWord = reverseWord + word.charAt(j);
            }
            reversedString = reversedString + reverseWord + " ";
        }
        return reversedString;
    }

    /**
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
        if (a == b && b == c)
        {
            return TriangleTypes.EQUILATERAL.name();
        }
        if (a >= b + c || c >= b + a || b >= a + c)
        {
            return TriangleTypes.INVALID.name();
        }
        if (b == c || a == b || c == a)
        {
            return TriangleTypes.ISOSCELES.name();
        }
        return TriangleTypes.SCALENE.name();
    }

    /**
     * @param inputList
     * @return
     * @throws ServiceException
     */
    @Override
    public LinkedHashMap<String, ArrayList> getMakeoneArray(Collection<ArrayList> inputList) throws ServiceException
    {
        Set<Integer> hs = new HashSet<Integer>();
        LinkedHashMap<String, ArrayList> listLinkedHashMap = new LinkedHashMap<String, ArrayList>();
        for (ArrayList arrayList : inputList)
        {
            for (int i = 0;
                 i < arrayList.size();
                 i++)
            {
                hs.add((Integer) arrayList.get(i));
            }
        }
        listLinkedHashMap.put("Array", new ArrayList(hs));
        return listLinkedHashMap;
    }
}

