/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.exceptions;

/**
 * A PayPalException is thrown to signal a problem during SDK execution.
 * @author PayPal DTS
 */
public abstract class PayPalException extends Exception
{
    /*
     * Default constructor
     */
    public PayPalException()
    {
        super();
    }

    /*
     * Constructs a new exception with the specified detail message.
     */
    public PayPalException(String message)
    {
        super(message);
    }

    /*
     * Constructs a new exception with the specified detail message and cause.
     */
    public PayPalException(String message, Throwable cause)
    {
        super(message, cause);
    }
} // PayPalException