/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.core;

import java.io.IOException;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.net.ssl.KeyManagerFactory;
import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.paypal.sdk.exceptions.PayPalException;
import com.paypal.sdk.exceptions.TransactionException;
import com.paypal.sdk.exceptions.WarningException;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.CertificateAPIProfile;
import com.paypal.sdk.profiles.PermissionAPIProfile;
import com.paypal.sdk.profiles.SignatureAPIProfile;
import com.paypal.sdk.profiles.UniPayAPIProfile;
import com.paypal.sdk.util.MessageResources;
import com.paypal.sdk.util.PPCrypto;
import com.paypal.sdk.util.Util;

/**
 * Abstract superclass for API accessor toolkits
 */
public abstract class APICallerBase
{
    private static Log log = LogFactory.getLog(APICallerBase.class);

    /**
     * Default XML parser name
     */
    private static final String DEFAULT_PARSER_NAME = "org.apache.xerces.parsers.SAXParser";

    /**
     * Map of the PayPal endpoints
     */
    protected static Map endpoints;


    static
    {
        readProxyInfo();
    }

    public void finalize()
    {
    	Keys.unregisterKeys(String.valueOf(this.hashCode()));
    }

    /**
     * This method parses the XML file and sets up the handler.
     * @param message          the wsdl file to parsed
     * @param handler          the handler object
     * @throws SAXException    if a SAXException occurs
     * @throws IOException     if an IOException occus
     */
    protected static void setupHandler(final String message, DefaultHandler handler)
            throws SAXException, IOException
    {
        ClassLoader cl = APICallerBase.class.getClassLoader();
        URL url = cl.getResource(message);

        XMLReader xr = XMLReaderFactory.createXMLReader(DEFAULT_PARSER_NAME);
        xr.setContentHandler(handler);
        xr.parse(new InputSource(url.openStream()));
    }

    /**
     * This method reads the endpoints in the given XML file.
     * @param message  the XML resource name
     */
    protected static void readEndpoints(String message)
    {
        try
        {
            EndpointsReader handler = new EndpointsReader(Constants.DEFAULT_API_VERSION);
            setupHandler(message, handler);
            endpoints = handler.getEndpoints();
        }
        catch (Exception e)
        {
            if (log.isErrorEnabled())
            {
                log.error(MessageResources.getMessage("READ_ENDPOINTS_ERROR"), e);
            }
        }
        finally
        {
            if ((endpoints == null) || (endpoints.isEmpty()))
            {
                if (log.isWarnEnabled())
                {
                    log.warn(MessageResources.getMessage("ENDPOINTS_EMPTY"));
                }
            }
        }
    }

    /**
     * This method sets the proxy properties.
     *  @param message  the resource name
    */
    private static void readProxyInfo()
    {
    	try {
            /*
             * Reading proxy properties from sdkproxy.properties file.
             */
    		String message = "sdkproxy";
            ResourceBundle bundle = ResourceBundle.getBundle(message, Locale.getDefault());
			String useProxy = bundle.getString("PROXY_SET");
			if("true".equalsIgnoreCase(useProxy))
			{
	            String host = bundle.getString("PROXY_HOST");
	            String port = bundle.getString("PROXY_PORT");
	            //Make sure the port is a valid integer
	            Integer.parseInt(port);
	            final Properties systemProperties = System.getProperties();
				systemProperties.put("https.proxySet", useProxy);
				systemProperties.put("https.proxyHost", host);
				systemProperties.put("https.proxyPort", port);

				final String username = bundle.getString("PROXY_USERNAME");
				if (!Util.isEmpty(username))
					systemProperties.put("https.proxyUser", username);
				final String password = bundle.getString("PROXY_PASSWORD");
				if (!Util.isEmpty(password))
					systemProperties.put("https.proxyPassword", password);
	            if (log.isInfoEnabled()) {
	            	log.info(MessageFormat.format(MessageResources.getMessage("PROXY_SET"), new Object[] {
	                    host, port}));
	            }
			}
        }
        catch(MissingResourceException e)
        {
	   if (log.isInfoEnabled()) {
            log.info(MessageResources.getMessage("PROXY_NOT_SET"));
	   }
        }
        catch (NumberFormatException e)
        {
            if (log.isDebugEnabled()) {
            	log.debug(MessageResources.getMessage("PROXY_PORT_INVALID"), e);
            }
        }
        catch (Exception e)
        {
            if (log.isDebugEnabled()) {
            	log.debug(MessageResources.getMessage("PROXY_INVALID"), e);
            }
        }
    }

    /**
     * Ensures that essential Profile values have been defined.
     *
     * @param _profile
     * @throws WarningException
     * @throws TransactionException
     */
    protected abstract void validateProfile(APIProfile _profile) throws PayPalException;


    /**
     * Initialize binding objects to the PayPal ports
     *
     * @throws PayPalException if an error occurs
     */
    public synchronized void setupConnection(APIProfile _profile) throws PayPalException
    {
        if (_profile == null)
            throw new WarningException(MessageResources.getMessage("PROFILE_INVALID"));

        // Validate the profile
        this.validateProfile(_profile);

        // Set-up the connection parameters
        this.registerKey(_profile);
    }

    /**
     * Initialize binding objects to the PayPal ports
     *
     * @throws PayPalException if an error occurs
     */
    protected void registerKey(APIProfile _profile) throws PayPalException
    {
        if (Keys.containsKey(String.valueOf(this.hashCode())))
        {
            Keys.unregisterKeys(String.valueOf(this.hashCode()));
        }

        if (_profile instanceof CertificateAPIProfile)
        {
        	try
            {
        		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        		KeyStore ks = PPCrypto.p12ToKeyStore(_profile.getCertificateFile(),
            		_profile.getPrivateKeyPassword());
        		kmf.init(ks, _profile.getPrivateKeyPassword().toCharArray());
        		Keys.registerKeys(String.valueOf(this.hashCode()), kmf.getKeyManagers());

            }
        	catch (NoSuchAlgorithmException e)
        	{
        		throw new TransactionException(MessageResources.getMessage("KEYMANAGERS_ERROR"), e);
        	}
        	catch (KeyStoreException e)
        	{
        		throw new TransactionException(MessageResources.getMessage("KEYMANAGERS_ERROR"), e);
        	}
        	catch (UnrecoverableKeyException e)
        	{
        		throw new TransactionException(MessageResources.getMessage("KEYMANAGERS_ERROR"), e);
        	}
        }
        if (_profile instanceof PermissionAPIProfile)
        {
        	try
            {
        		if(_profile.getCertificateFile()!=null && _profile.getCertificateFile().length()>0)
        		{
        			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            		KeyStore ks = PPCrypto.p12ToKeyStore(_profile.getCertificateFile(),
                		_profile.getPrivateKeyPassword());
            		kmf.init(ks, _profile.getPrivateKeyPassword().toCharArray());
            		Keys.registerKeys(String.valueOf(this.hashCode()), kmf.getKeyManagers());
        		}
            }
        	catch (NoSuchAlgorithmException e)
        	{
        		throw new TransactionException(MessageResources.getMessage("KEYMANAGERS_ERROR"), e);
        	}
        	catch (KeyStoreException e)
        	{
        		throw new TransactionException(MessageResources.getMessage("KEYMANAGERS_ERROR"), e);
        	}
        	catch (UnrecoverableKeyException e)
        	{
        		throw new TransactionException(MessageResources.getMessage("KEYMANAGERS_ERROR"), e);
        	}
        }
    }

    /**
     * Ges the APIVersion
     * @return String ApPIVersion
     */
    public String getAPIVersion()
    {
        return Constants.DEFAULT_API_VERSION;
    }

    /**
     * This method returns the endpoints URL
     * @param _profile   the APIProfile object
     * @param name        the QName object
     * @return String endpointurl
     */
    protected String getEndpointUrl(APIProfile _profile, QName name)
    {
        String endpointName = _profile.getEnvironment().toLowerCase() + "-"
                + name.getLocalPart().toLowerCase();
        if (_profile instanceof CertificateAPIProfile)
        {
            return (String) endpoints.get(endpointName);
        }
        if (_profile instanceof UniPayAPIProfile)
		{
		            return (String) endpoints.get(endpointName);
        }
        if (_profile instanceof SignatureAPIProfile)
        {
            return (String) endpoints.get(endpointName + "-threetoken");
        }
        if (_profile instanceof PermissionAPIProfile)
        {
            return (String) endpoints.get(endpointName);
        }
        return null;
    }
}