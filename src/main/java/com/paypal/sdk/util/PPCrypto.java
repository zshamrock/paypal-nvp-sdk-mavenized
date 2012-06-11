/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.MessageFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.paypal.sdk.exceptions.FatalException;
import com.paypal.sdk.exceptions.PayPalException;
import com.paypal.sdk.exceptions.TransactionException;

/**
 * PayPal cryptography routines
 * @author PayPal DTS
 */
public class PPCrypto
{
    private static Log log = LogFactory.getLog(PPCrypto.class);

    public static KeyStore p12ToKeyStore(String p12Path, String password) throws FatalException
    {
        if (log.isDebugEnabled())
        {
            log.debug("PPCrypto.p12ToKeyStore, keystore = " + p12Path);
        }

        KeyStore ks = null;

        try
        {
            // Make sure *not* to load the BouncyCastle PKCS12 KeyStore, since
            // it requires the US-only policy jars to be installed in jre/lib/security.
            // This dependency exists for EWP users, but not for users of the API only.
            ks = KeyStore.getInstance("PKCS12", "SunJSSE");
        }
        catch (KeyStoreException e)
        {
            throw new FatalException(MessageResources.getMessage("JKS_ERROR"), e);
        }
        catch (NoSuchProviderException e)
        {
            throw new FatalException(MessageResources.getMessage("JKS_ERROR"), e);
        }

        FileInputStream in;
        try
        {
            in = new FileInputStream(p12Path);
            ks.load(in, password.toCharArray());
        }
        catch (FileNotFoundException e)
        {
            throw new FatalException(MessageFormat.format(MessageResources
                    .getMessage("PAYPAL_CERT_ERROR"), new Object[] { p12Path }), e);
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new FatalException(MessageResources.getMessage("JKS_ALGORITHM_ERROR"), e);
        }
        catch (CertificateException e)
        {
            throw new FatalException(MessageResources.getMessage("CERT_DECODE_ERROR"), e);
        }
        catch (IOException e)
        {
            throw new FatalException(MessageFormat.format(MessageResources
                    .getMessage("PAYPAL_CERT_ERROR"), new Object[] { p12Path }), e);
        }

        return ks;
    }



    /**
     * Reads the user's PayPal-packaged PEM format public certificate and private key and parses into two strings.
     * @param filename path to the PEM file
     * @return array of two strings: array[0] is the public cert, and array[1] is the private key
     * @throws IOException
     */
    public static String[] readPayPalCertFile(String filename) throws IOException
    {

        StringBuffer cert = new StringBuffer();
        StringBuffer key = new StringBuffer();
        StringBuffer toWrite = key; // Assumes that key is listed before certificate in file.

        BufferedReader read = new BufferedReader(new FileReader(filename));
        String line = read.readLine();
        while (line != null)
        {

            if (line.equals(""))
            {
                line = read.readLine();
                continue;
            }

            if (line.startsWith("-----BEGIN CERTIFICATE"))
            {
                toWrite = cert;
            }

            // Don't include -----BEGIN RSA PRIVATE KEY----- or -----END RSA PRIVATE KEY-----
            if (!"-----BEGIN RSA PRIVATE KEY-----".equals(line)
                    && !"-----END RSA PRIVATE KEY-----".equals(line))
            {
                toWrite.append(line + "\n");
            }

            line = read.readLine();
        }
        return new String[] { cert.toString(), key.toString() };
    } // readPayPalCertFile

    /**
     * Reads the user certificate and public key from a string
     * @param certPEM PEM-encoded public certificate
     * @throws TransactionException if there is a problem with the certificate
     */
    public static X509Certificate getUserCertFromString(String certPEM) throws TransactionException
    {
        // certificate to generate
        X509Certificate client_cert;
        // stream for reading certificate bytes
        ByteArrayInputStream inputStream = new ByteArrayInputStream(certPEM.getBytes());

        try
        {
            // this CertificateFactory process the raw PEM data into ax X509 Certificate
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            client_cert = (X509Certificate) cf.generateCertificate(inputStream);
        }
        catch (CertificateException e)
        {
            throw new TransactionException(
                    "Could not create an X509Certificate from input PEM string", e);
        }
        return client_cert;
    } // getUserCertFromString

} // PPCrypto