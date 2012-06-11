/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.profiles;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.paypal.sdk.exceptions.PayPalException;
import com.paypal.sdk.exceptions.WarningException;
import com.paypal.sdk.util.MessageResources;

/**
 * Stores profiles in a serialized data file
 * @author PayPal DTS
 */
public final class DefaultProfileHandler implements ProfileHandler
{
    public final Profiles retrieve() throws PayPalException
    {
        try
        {
            FileInputStream fis = new FileInputStream("profiles");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Profiles profiles = (Profiles) ois.readObject();
            ois.close();
            fis.close();
            return profiles;
        }
        catch (ClassNotFoundException e)
        {
            throw new WarningException(MessageResources.getMessage("PROFILE_RETRIEVE_ERROR"), e);
        }
        catch (NotSerializableException e)
        {
            throw new WarningException(MessageResources.getMessage("PROFILE_RETRIEVE_ERROR"), e);
        }
        catch (IOException e)
        {
            throw new WarningException(MessageResources.getMessage("PROFILE_RETRIEVE_ERROR"), e);
        }
    }

    public final void store(Profiles profiles) throws PayPalException
    {
        try
        {
            FileOutputStream fos = new FileOutputStream("profiles");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(profiles);
            oos.flush();
            oos.close();
            fos.close();
        }
        catch (NotSerializableException e)
        {
            throw new WarningException(MessageResources.getMessage("PROFILE_STORE_ERROR"), e);
        }
        catch (IOException e)
        {
            throw new WarningException(MessageResources.getMessage("PROFILE_STORE_ERROR"), e);
        }
    }
} // DefaultProfileHandler