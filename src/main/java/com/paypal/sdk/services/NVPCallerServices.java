/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.services;

import com.paypal.sdk.core.nvp.NVPAPICaller;
import com.paypal.sdk.exceptions.PayPalException;
import com.paypal.sdk.profiles.APIProfile;

/**
 * Facade class that implements API Caller services.
 */
public class NVPCallerServices
{
    /*
     * The HTTP API caller
     */
    private NVPAPICaller caller;

    /**
     * Default constructor.
     */
    public NVPCallerServices()
    {
    	caller = new NVPAPICaller();
    }

    /**
     * Get the version of the WSDL in use
     * @return WSDL version
     */
    public final String getAPIVersion() throws PayPalException
    {
        return caller.getAPIVersion();
    }

    /**
     * Specifies the account on behalf of which the API Caller makes calls. 
     * @param profile profile to make active
     * @throws PayPalException if profile cannot be found
     */
    public final void setAPIProfile(APIProfile profile) throws PayPalException
    {
        caller.setupConnection(profile);
    } // setAPIProfile

    /**
     * Invokes an API call from a request object
     * @param payload the request string
     * @return response string
     * @throws PayPalException if no profile is set, or an error occurs while executing the call
     */
    public final String call(String payload) throws PayPalException
    {
        return caller.call(payload);
    } // call
} // NVPCallerServices