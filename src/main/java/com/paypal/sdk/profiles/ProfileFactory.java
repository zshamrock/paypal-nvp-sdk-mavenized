/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.profiles;

import java.text.MessageFormat;

import com.paypal.sdk.exceptions.PayPalException;
import com.paypal.sdk.exceptions.TransactionException;
import com.paypal.sdk.util.MessageResources;

/**
 * Utility class used to create profile objects.
 * @author PayPal DTS
 */
public class ProfileFactory

{
    private static final String DEFAULT_SSL_API_PROFILE = "com.paypal.sdk.profiles.CertificateAPIProfile";
    private static final String DEFAULT_UNIPAY_API_PROFILE = "com.paypal.sdk.profiles.UniPayAPIProfile";
    private static final String DEFAULT_SIGNATURE_API_PROFILE = "com.paypal.sdk.profiles.SignatureAPIProfile";
    private static final String DEFAULT_PERMISSION_API_PROFILE="com.paypal.sdk.profiles.PermissionAPIProfile";
    
    private static final String DEFAULT_EWP_PROFILE = "com.paypal.sdk.profiles.DefaultEWPProfile";
    

    /**
     * Create a blank SSL API Profile
     * @return new API Profile
     */
    public static APIProfile createSSLAPIProfile() throws PayPalException
    {
        try
        {
            APIProfile profile = (APIProfile) Class.forName(DEFAULT_SSL_API_PROFILE).newInstance();
            return profile;
        }
        catch (Exception e)
        {
            throw new TransactionException(MessageFormat.format(MessageResources
                    .getMessage("PROFILE_INSTANTIATION_ERROR"),
                    new Object[] { DEFAULT_SSL_API_PROFILE }));
        }
    } // createSSLAPIProfile

    /**
     * Create a blank SSL API Profile
     * @return new API Profile
     */
    public static APIProfile createSignatureAPIProfile() throws PayPalException
    {
        try
        {
            APIProfile profile = (APIProfile) Class.forName(DEFAULT_SIGNATURE_API_PROFILE)
                    .newInstance();
            return profile;
        }
        catch (Exception e)
        {
            throw new TransactionException(MessageFormat.format(MessageResources
                    .getMessage("PROFILE_INSTANTIATION_ERROR"),
                    new Object[] { DEFAULT_SIGNATURE_API_PROFILE }));
        }
    } // createSSLAPIProfile


    /**
        * Create a blank UniPay API Profile
        * @return new API Profile
        */
       public static APIProfile createUniPayAPIProfile() throws PayPalException
       {
           try
           {
               APIProfile profile = (APIProfile) Class.forName(DEFAULT_UNIPAY_API_PROFILE)
                       .newInstance();
               return profile;
           }
           catch (Exception e)
           {
               throw new TransactionException(MessageFormat.format(MessageResources
                       .getMessage("PROFILE_INSTANTIATION_ERROR"),
                       new Object[] {DEFAULT_UNIPAY_API_PROFILE }));
           }
       } // createUniPayAPIProfile

    /**
     * Create a blank EWP Profile
     * @return new EWPProfile
     */
    public static EWPProfile createEWPProfile() throws PayPalException
    {
        try
        {
            EWPProfile profile = (EWPProfile) Class.forName(DEFAULT_EWP_PROFILE).newInstance();
            return profile;
        }
        catch (Exception e)
        {
            throw new TransactionException(MessageFormat.format(MessageResources
                    .getMessage("PROFILE_INSTANTIATION_ERROR"),
                    new Object[] { DEFAULT_EWP_PROFILE }));
        }
    } // createEWPProfile
    
    public static APIProfile createPermissionAPIProfile() throws PayPalException{
    	try{
    		APIProfile profile=(APIProfile)Class.forName(DEFAULT_PERMISSION_API_PROFILE).newInstance();
    		return profile;
    	}catch (Exception e) {
    		throw new TransactionException(MessageFormat.format(MessageResources
                    .getMessage("PROFILE_INSTANTIATION_ERROR"),
                    new Object[] { DEFAULT_PERMISSION_API_PROFILE }));
    	}
    }
    
} // ProfileFactory