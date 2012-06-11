/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.util;

import java.util.ResourceBundle;

/**
 * Utility class that provides SDK configuration values
 * @author PayPal DTS
 */
public abstract class SDKResources
{
    private static ResourceBundle bundle;

    static
    {
        initializeBundle();
    }

    private static void initializeBundle()
    {
        bundle = ResourceBundle.getBundle("com.paypal.sdk.resources.SDK");
    }

    public static String getMessage(String key)
    {
        return bundle.getString(key);
    }
} // SDKResources