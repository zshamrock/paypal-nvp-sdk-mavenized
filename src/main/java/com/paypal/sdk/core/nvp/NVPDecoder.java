/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.core.nvp;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.paypal.sdk.exceptions.FatalException;
import com.paypal.sdk.exceptions.PayPalException;

/**
 * This class parses the String given in the NVP format and provides methods
 * to retrieve data in NVP format String.
 */
public class NVPDecoder
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
     * This method returns the value for the given Name key.
     * @param pName    The name for which the value is required
     * @return String  The value for the given name. 
     */
    public final synchronized String get(String pName)
    {
        return (String) nvp.get(pName);
    }
    
    /**
     * This method parses the String in the NVP format passed as the parameter and stores
     * them in a collection.
     * @param pPayload    The string in the NVP format.
     */
    public final synchronized void decode(String pPayload) throws PayPalException
    {
    	try
    	{
    		StringTokenizer stTok = new StringTokenizer(pPayload, "&");
    		while (stTok.hasMoreTokens())
    		{
    			StringTokenizer stInternalTokenizer = new StringTokenizer(stTok.nextToken(), "=");
    			if (stInternalTokenizer.countTokens() == 2)
    			{
    				nvp.put(URLDecoder.decode(stInternalTokenizer.nextToken(), ENCODE_TYPE),
    						URLDecoder.decode(stInternalTokenizer.nextToken(), ENCODE_TYPE));
    			}
    		}
    	}
    	catch (Exception e)
    	{
    		throw new FatalException(e.getMessage(), e);
    	}
    }
    /**
     * Return a Map for all decoded values
     * @return
     */
    public final synchronized Map getMap(){
    	Map newMap=new HashMap();
    	newMap.putAll(nvp);
    	return newMap;
    }
}
