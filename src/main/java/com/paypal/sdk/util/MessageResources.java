/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Utility class that provides static methods for string localization
 * @author PayPal DTS
 */
public abstract class MessageResources
{
    private static ResourceBundle bundle;

    static
    {
        initializeBundle(Locale.getDefault());
    }

    /**
     * This method initializes resource bundle with the given 
     * the given properties file
     * @param locale The locale object
     */
    private static void initializeBundle(Locale locale)
    {
        bundle = ResourceBundle.getBundle("com.paypal.sdk.resources.Resource", locale);
    }

    /**
     * Get a localized message
     * @param key key of message
     * @return localized message
     */
    public static String getMessage(String key)
    {
        return bundle.getString(key);
    }

    /**
     * Set a particular language and country for the resources
     * @param language language to set
     * @param country country to set
     */
    public static void setLocale(String language, String country)
    {
        initializeBundle(new Locale(language, country));
    }

    /**
     * Set a particluar locale for the resources
     * @param locale locale to set
     */
    public static void setLocale(Locale locale)
    {
        initializeBundle(locale);
    }
} // MessageResources