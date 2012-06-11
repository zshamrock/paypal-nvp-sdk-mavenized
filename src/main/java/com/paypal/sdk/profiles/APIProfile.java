/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.profiles;

import com.paypal.sdk.exceptions.PayPalException;

/**
 * Interface which defines the information PayPal needs to know about a merchant in order to provide API services.
 * @author PayPal DTS
 */
public interface APIProfile
{
    /**
     * Return the username this profile uses to access the PayPal API
     * @return the username this profile uses to access the PayPal API
     */
    public String getAPIUsername();

    /**
     * Set the username this profile uses to access the PayPal API
     * @param apiUsername the username this profile uses to access the PayPal API
     */
    public void setAPIUsername(String apiUsername);

    /**
     * Return the password this profile uses to access the PayPal API
     * @return the password this profile uses to access the PayPal API
     */
    public String getAPIPassword();

    /**
     * Set the password this profile uses to access the PayPal API
     * @param apiPassword the password this profile uses to access the PayPal API
     */
    public void setAPIPassword(String apiPassword);

    /**
     * Return the certificate filename used by this profile to authenticate itself to PayPal
     * @return the certificate filename used by this profile to authenticate itself to PayPal
     */
    public String getCertificateFile();

    /**
     * Set the certificate filename used by this profile to authenticate itself to PayPal
     * @param certificateFile the certificate filename used by this profile to authenticate itself to PayPal
     */
    public void setCertificateFile(String certificateFile) throws PayPalException;

    /**
     * Return the private key password
     * @return the private key password
     */
    public String getPrivateKeyPassword();

    /**
     * Set the private key password
     * @param privateKeyPassword the private key password
     */
    public void setPrivateKeyPassword(String privateKeyPassword) throws PayPalException;

    /**
     * Return the signature for Three Token authentication
     * @return the signature
     */
    public String getSignature();

    /**
     * Set the signature for Three Token authentication
     * @param signature the signature
     */
    public void setSignature(String signature) throws PayPalException;

    /**
     * Return the name of the merchant on behalf of which api calls are made
     * @return name of the merchant
     */
    public String getSubject();

    /**
     * Set name of the merchant on behalf of which api calls are made
     * @param subject name of the merchant
     */
    public void setSubject(String subject);

    /**
     * Return the PayPal environment (eg. live, sandbox)
     * @return the PayPal environment (eg. live, sandbox)
     */
    public String getEnvironment();

    /**
     * Set the PayPal environment (eg. live, sandbox)
     * @param environment the PayPal environment (eg. sandbox, live)
     */
    public void setEnvironment(String environment);

    /**
     * Return the connection timeout in milliseconds
     * @return the timeout in milliseconds
     */
    public int getTimeout();

    /**
     *  Set the connection timeout in milliseconds
     * @param timeout the timeout in milliseconds
     */
    public void setTimeout(int timeout);

    /**
     * Return the maximum number of retries
     * @return the maximum number of retries
     */
    public int getMaximumRetries();

    /**
     * Set the maximum number of retries
     * @param maximumRetries the maximum number of retries
     */
    public void setMaximumRetries(int maximumRetries);

    /**
     * Return the delay time bewteen each retry call in milliseconds
     * @return the delay time bewteen each retry call in milliseconds
     */
    public int getDelayTime();

    /**
     * Set the delay time bewteen each retry call in milliseconds
     * @param delayTime the delay time bewteen each retry call in milliseconds
     */
    public void setDelayTime(int delayTime);

    public void setFirstPartyEmail(String str) throws PayPalException;

    public String getFirstPartyEmail();
    
    // Permission methods
    public String getOauth_Signature();
    public void setOauth_Signature(String oauth_Signature)throws PayPalException;
    public String getOauth_Token();
    public void setOauth_Token(String oauth_Token)throws PayPalException;
    public String getOauth_Timestamp();
    public void setOauth_Timestamp(String oauth_Timestamp)throws PayPalException;

} // APIProfile