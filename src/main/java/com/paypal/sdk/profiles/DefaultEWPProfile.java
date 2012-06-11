/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.profiles;

import java.io.Serializable;

/**
 * Default implementation of an EWP Profile
 * @author PayPal DTS
 */
public class DefaultEWPProfile implements EWPProfile, Serializable
{
    /**
     * The path to the PKCS12 file, which contains both the user certificate and private key.
     */
    private String certificateFile = "";

    /**
     * The user private key password
     */
    private transient String privateKeyPassword = "";

    /**
     * The path to PayPal public certificate
     */
    private String paypalCertificateFile = "";

    /**
     * The URL for button form POST actions, eg. https://www.paypal.com
     */
    private String url = "https://www.paypal.com";

    /**
     * The image for button, eg. "https://www.paypal.com/en_US/i/btn/x-click-but23.gif"
     */
    private String buttonImage = "https://www.paypal.com/en_US/i/btn/x-click-but23.gif";

    public String getCertificateFile()
    {
        return this.certificateFile;
    }

    public void setCertificateFile(String certificateFile)
    {
        this.certificateFile = certificateFile;
    }

    public String getPrivateKeyPassword()
    {
        return this.privateKeyPassword;
    }

    public void setPrivateKeyPassword(String privateKeyPassword)
    {
        this.privateKeyPassword = privateKeyPassword;
    }

    public String getPayPalCertificateFile()
    {
        return this.paypalCertificateFile;
    }

    public void setPayPalCertificateFile(String paypalCertificateFile)
    {
        this.paypalCertificateFile = paypalCertificateFile;
    }

    public String getUrl()
    {
        return this.url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getButtonImage()
    {
        return this.buttonImage;
    }

    public void setButtonImage(String buttonImage)
    {
        this.buttonImage = buttonImage;
    }
} // DefaultEWPProfile