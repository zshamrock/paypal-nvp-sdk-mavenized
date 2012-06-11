/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.exceptions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A FatalException is thrown during SDK execution to signal a major problem that prevents the SDK from functioning properly.
 * @author PayPal DTS
 */
public class FatalException extends PayPalException
{
    private static Log log = LogFactory.getLog(FatalException.class);

    /*
     * Default constructor.
     */
    public FatalException()
    {
        super();
    }

    /*
     * Constructs a new exception with the specified detail message.
     */
    public FatalException(String message)
    {
        super(message);
        log.fatal(message, this);
    }

    /*
     * Constructs a new exception with the specified detail message and cause.
     */
    public FatalException(String message, Throwable cause)
    {
        super(message, cause);
        log.fatal(message, cause);
    }
} // FatalException