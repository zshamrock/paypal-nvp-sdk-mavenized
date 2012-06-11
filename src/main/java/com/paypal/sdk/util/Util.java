/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */
package com.paypal.sdk.util;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Properties;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.paypal.sdk.core.Constants;

/**
 * Default TrustManager checks that a cert is signed by a well known
 * certificate authority, like Verisign or Thawte.
 */
class RelaxedX509TrustManager implements X509TrustManager {
    public boolean checkClientTrusted(java.security.cert.X509Certificate[] chain){ return true; }
    public boolean isServerTrusted(java.security.cert.X509Certificate[] chain){ return true; }
    public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {}
    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {}
} // RelaxedX509TrustManager

public abstract class Util
{
    private static Log log = LogFactory.getLog(Util.class);

    /**
     * This method returns the SSLContext object.
     * @param keymanagers KeyManager[] The key managers
     * @return SSLContext
     * @throws IOException if an IOException occurs
     */
    public static SSLContext getSSLContext(KeyManager[] keymanagers) throws IOException
    {
        try
        {
        	SSLContext ctx = SSLContext.getInstance("SSL"); // TLS, SSLv3, SSL
        	SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(System.currentTimeMillis());
            if (Constants.TRUST_ALL)
            {
                TrustManager[] tm = { new RelaxedX509TrustManager() }; // customized,
                ctx.init(keymanagers, tm, random);
            }
            else
            {
                ctx.init(keymanagers, null, random);
            }
            return ctx;
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            throw new IOException(e.getMessage());
        }
    } // getSSLContext

    public static boolean isStage(String name ) {
	    	boolean stage=false;
	     	if (name.indexOf("stage") >= 0 )
	        	stage=true;
	     	return stage;
    }
    public static boolean isEmpty(String s)
    {
        return s == null || s.length() == 0;
    }
    public static float round(float Rval, int Rpl) {
		float p = (float)Math.pow(10,Rpl);
		Rval = Rval * p;
		float tmp = Math.round(Rval);
		return (float)tmp/p;
    }
}
