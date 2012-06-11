/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.util;

import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.exceptions.TransactionException;
import com.paypal.sdk.services.NVPCallerServices;

/**
 * This class tests whether the connection with server is successfull or not.
 *
 */
public class TestConnection
{

    /**
     * This method tests whether the client could establish the connection with the
     * server successfully or not
     * @param caller    NVPCallerServices object containig the credential information
     * @return STring     The response from the server in the NVP format         
     * @throws Exception  if any exception occurs
     */
    public String testServerConnection(NVPCallerServices caller) throws TransactionException
    {
        try
        {

            
            String strResult = caller
                    .call("ENDDATE=2006-9-3T0%3A0%3A0&STARTDATE=2006-9-1T0%3A0%3A0&TRXTYPE=Q&METHOD=TransactionSearch&VERSION=2.3&USER=sri-usbiz1user_api1.paypal.com&SOURCE=SDK_5.0_JAVA_PAYPAL");
            
            NVPDecoder lDecoder = new NVPDecoder();
            lDecoder.decode(strResult);
            String strErrorCode0 = lDecoder.get("L_ERRORCODE0");
            if (strErrorCode0 == null || strErrorCode0.equals(""))
            {
                return " Successfully Connected";
            }
            else if (strErrorCode0 != null && strErrorCode0.length() > 0)
            {
                StringBuffer sbErrorMessages = new StringBuffer("Connection Failed ;   ");
                int i = 0;
                while (lDecoder.get("L_LONGMESSAGE" + i) != null
                        && !lDecoder.get("L_LONGMESSAGE" + i).equals(""))
                {
                    sbErrorMessages.append(lDecoder.get("L_LONGMESSAGE" + i) + "  ;  ");
                    i++;
                }
                return sbErrorMessages.toString();
            }
            else
            {
                return strResult;
            }
        }
        catch (Exception e)
        {
            throw new TransactionException(e.getMessage(), e);
        }
    }
}
