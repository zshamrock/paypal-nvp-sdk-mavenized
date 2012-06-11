/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.exceptions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A TransactionException is thrown during SDK execution to signal a non-fatal problem with a specific SDK transaction.
 * @author PayPal DTS
 */
public class TransactionException extends PayPalException
{
    private static Log log = LogFactory.getLog(TransactionException.class);

    /**
     * Default constructor
     */
    public TransactionException()
    {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     */
    public TransactionException(String message)
    {
        super(message);
        log.error(message, this);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     */
    public TransactionException(String message, Throwable cause)
    {
        super(message, cause);
        log.error(message, cause);
    }
} // TransactionException