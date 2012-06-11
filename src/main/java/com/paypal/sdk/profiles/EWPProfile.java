/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.profiles;

/**
 * Interface which defines the information PayPal needs to know about a merchant in order to provide Encrypted Website Payment (EWP) services.
 * @author PayPal DTS
 */
public interface EWPProfile
{
    /**
     * Return the path to the PKCS12 file, which contains both the user certificate and private key.
     * @return the path of the profile's certificate file
     */
    public String getCertificateFile();

    /**
     * Set the path of path to the PKCS12 file, which contains both the user certificate and private key.
     * @param certificateFile the path of the profile's certificate file
     */
    public void setCertificateFile(String certificateFile);

    /**
     * Return the user private key password
     * @return the private key password
     */
    public String getPrivateKeyPassword();

    /**
     * Set the user private key password
     * @param privateKeyPassword the private key password
     */
    public void setPrivateKeyPassword(String privateKeyPassword);

    /**
     * Return the path to PayPal public certificate
     * @return the path to PayPal public certificate
     */
    public String getPayPalCertificateFile();

    /**
     * Set the path to PayPal public certificate
     * @param paypalCertificateFile the path to PayPal public certificate
     */
    public void setPayPalCertificateFile(String paypalCertificateFile);

    /**
     * Return the URL for button form POST actions, eg. https://www.paypal.com
     * @return the URL for button form POST actions
     */
    public String getUrl();

    /**
     * Set the URL for button form POST actions, eg. "https://www.paypal.com"
     * @param url the URL for button form POST actions
     */
    public void setUrl(String url);

    /**
     * Return the image for button, eg. "https://www.paypal.com/en_US/i/btn/x-click-but23.gif"
     * @return the image for the button
     */
    public String getButtonImage();

    /**
     * Set the image for button, eg. https://www.paypal.com/en_US/i/btn/x-click-but23.gif
     * @param buttonImage the image for the button
     */
    public void setButtonImage(String buttonImage);
} // EWPProfile