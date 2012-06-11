/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.core.nvp;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.paypal.sdk.exceptions.FatalException;
import com.paypal.sdk.exceptions.PayPalException;

public class NVPEncoder
{
	/**
	 * Encoding type
	 */
    private static final String ENCODE_TYPE = "UTF-8";
    
	/**
	 * Name-Value pairs hashmap
	 */
    private HashMap nvp = new HashMap();

    /**
     * This method adds the given name and value as a pair as a new entity
     * @param pstrName  The String containing the name.
     * @param pstrValue The String containing the value for the given name.
     */
    public final synchronized void add(String name, String value)
    {
        nvp.put(name, value);
    }

    /**
     * This method removes the given name along with the value for that name
     * @param pstrName  The String containing the name.
     */
    public final synchronized void remove(String name)
    {
       nvp.remove(name);
    }

    /**
     * This method clears all the name value pair data
     *
     */
    public final synchronized void clear()
    {
        nvp.clear();
    }

    /**
     * This method forms an URL encoded string in the NVP format. To form the encoded
     * string it takes all the name and values added in this object .
     * @return String  The URL encoded string in the NVP format.
     */
    public final synchronized String encode() throws PayPalException
    {
        try
        {	
        	StringBuffer buffer = new StringBuffer();
            Set keys = nvp.keySet();
            Iterator iter = keys.iterator();
            boolean firstpair = true;
            while (iter.hasNext())
            {
                String key = (String) iter.next();
                if (!firstpair) 
                {
                	buffer.append("&");
                }
                buffer.append(URLEncoder.encode(key, ENCODE_TYPE));
                buffer.append("=");
                buffer.append(URLEncoder.encode("" + nvp.get(key), ENCODE_TYPE));
                firstpair = false;
            }
            return buffer.toString();
        }
        catch (Exception e)
        {
            throw new FatalException(e.getMessage(), e);
        }
    }
}