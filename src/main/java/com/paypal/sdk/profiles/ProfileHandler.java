/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.profiles;

import com.paypal.sdk.exceptions.PayPalException;

/**
 * Stores and retrieves profiles by interfacing with external storage device
 * @author PayPal DTS
 */
public interface ProfileHandler
{
    /**
     * Stores profiles into external storage
     * 
     * @param profiles profiles to store
     * @throws PayPalException if error occurs storing the data
     */
    public void store(Profiles profiles) throws PayPalException;

    /**
     * Loads profiles from external storage
     * 
     * @return profiles profiles retrieved from memory
     * @throws PayPalException if error occurs loading the data
     */
    public Profiles retrieve() throws PayPalException;
} // ProfileHandler