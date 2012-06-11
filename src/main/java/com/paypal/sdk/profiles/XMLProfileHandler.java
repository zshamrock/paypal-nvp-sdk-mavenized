/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.profiles;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.paypal.sdk.exceptions.PayPalException;
import com.paypal.sdk.exceptions.WarningException;
import com.paypal.sdk.util.MessageResources;

/**
 * XML implementation of ProfileHandler which stores profile data in an external XML file
 * @author PayPal DTS
 */
public class XMLProfileHandler implements ProfileHandler
{
    public Profiles retrieve() throws PayPalException
    {
        Profiles profiles = null;
        XMLDecoder decoder = null;
        try
        {
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("profiles")));
            profiles = (Profiles) decoder.readObject();
        }
        catch (IOException e)
        {
            throw new WarningException(MessageResources.getMessage("PROFILE_RETRIEVE_ERROR"), e);
        }
        finally
        {
            if (decoder != null)
                decoder.close();
        }
        return profiles;
    } // retrieve

    public void store(Profiles profiles) throws PayPalException
    {
        XMLEncoder encoder = null;
        try
        {
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("profiles")));
            encoder.writeObject(profiles);
        }
        catch (IOException e)
        {
            throw new WarningException(MessageResources.getMessage("PROFILE_STORE_ERROR"), e);
        }
        finally
        {
            if (encoder != null)
            {
                encoder.close();
            }
        }
    } // store
} // XMLProfileHandler