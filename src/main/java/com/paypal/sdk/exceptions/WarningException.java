/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.exceptions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A WarningException is thrown during SDK execution to signal a minor problem.
 * @author PayPal DTS
 */
public class WarningException extends PayPalException
{
    private static Log log = LogFactory.getLog(WarningException.class);

    /**
     * Default constructor
     */
    public WarningException()
    {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     */
    public WarningException(String message)
    {
        super(message);
        log.warn(message, this);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     */
    public WarningException(String message, Throwable cause)
    {
        super(message, cause);
        log.warn(message, cause);
    }
} // WarningException