/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.profiles;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.paypal.sdk.core.Constants;

/**
 * Base implementation for the API Profile.
 * @author PayPal DTS
 */
public abstract class BaseAPIProfile implements APIProfile, Serializable
{
    private static Log log = LogFactory.getLog(BaseAPIProfile.class);

    /**
     * The username used to access the PayPal API
     */
    private String apiUsername = "";

    /**
     * The password used to access the PayPal API
     */
    private transient String apiPassword = "";

    /**
     * The name of the entity on behalf of which this profile is issuing calls
     */
    private transient String subject = "";

    /**
     * The PayPal environment (eg. live, sandbox)
     */
    private String environment = "sandbox";

    /**
     * The connection timeout in milliseconds
     */
    private int timeout;

    /**
     * The maximum number of retries
     */
    private int maximumRetries;

    /**
     * The delay time bewteen each retry call in milliseconds
     */
    private int delayTime;

    public BaseAPIProfile()
    {
        try
        {
            this.timeout = Constants.TIMEOUT;
            this.maximumRetries = Constants.MAXIMUM_RETRIES;
            this.delayTime = Constants.DELAY_TIME;
        }
        catch (NumberFormatException e)
        {
            log.warn(e.getMessage(), e);
        }
    }

    /**
     * Return the username this profile uses to access the PayPal API
     * @return apiUsername the username this profile uses to access the PayPal API
     */
    public String getAPIUsername()
    {
        return this.apiUsername;
    }

    /**
     * Return the password this profile uses to access the PayPal API
     * @return the password this profile uses to access the PayPal API
     */
    public String getAPIPassword()
    {
        return this.apiPassword;
    }

    /**
     * Return the name of the merchant on behalf of which api calls are made
     * @return name of the merchant
     */
    public String getSubject()
    {
        return this.subject;
    }

    /**
     * Return the PayPal environment (eg. live, sandbox)
     * @return the PayPal environment (eg. live, sandbox)
     */
    public String getEnvironment()
    {
        return this.environment;
    }

    /**
     * Return the connection timeout in milliseconds
     * @return the timeout in milliseconds
     */
    public int getTimeout()
    {
        return timeout;
    }

    /**
     * Return the maximum number of retries
     * @return the maximum number of retries
     */
    public int getMaximumRetries()
    {
        return maximumRetries;
    }

    /**
     * Return the delay time bewteen each retry call in milliseconds
     * @return the delay time bewteen each retry call in milliseconds
     */
    public int getDelayTime()
    {
        return delayTime;
    }

    /**
     * Set the username this profile uses to access the PayPal API
     * @param apiUsername the username this profile uses to access the PayPal API
     */
    public void setAPIUsername(String apiUsername)
    {
        this.apiUsername = apiUsername;
    }

    /**
     * Set the password this profile uses to access the PayPal API
     * @param apiPassword the password this profile uses to access the PayPal API
     */
    public void setAPIPassword(String apiPassword)
    {
        this.apiPassword = apiPassword;
    }

    /**
     * Set name of the merchant on behalf of which api calls are made
     * @param subject name of the merchant
     */
    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    /**
     * Set the PayPal environment (eg. live, sandbox)
     * @param environment the PayPal environment (eg. sandbox, live)
     */
    public void setEnvironment(String environment)
    {
        this.environment = environment;
    }

    /**
     * Return the connection timeout in milliseconds
     * @return the timeout in milliseconds
     */
    public void setTimeout(int timeout)
    {
        if (timeout >= 0)
            this.timeout = timeout;
    }

    /**
     * Set the maximum number of retries
     * @param maximumRetries the maximum number of retries
     */
    public void setMaximumRetries(int maximumRetries)
    {
        if (maximumRetries >= 0)
            this.maximumRetries = maximumRetries;
    }

    /**
     * Set the delay time bewteen each retry call in milliseconds
     * @param delayTime the delay time bewteen each retry call in milliseconds
     */
    public void setDelayTime(int delayTime)
    {
        if (delayTime >= 0)
            this.delayTime = delayTime;
    }
} // BaseAPIProfile