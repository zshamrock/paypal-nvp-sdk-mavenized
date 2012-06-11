/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.core;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import com.paypal.sdk.util.MessageResources;

/**
 * The EndpointsReader class is responsible for parsing the paypal-endpoints file.
 *
 * @author PayPal DTS
 */
public class EndpointsReader extends DefaultHandler
{
    private static Log log = LogFactory.getLog(EndpointsReader.class);

    private boolean record = false;

    private String environment, port, version;

    private Map endpoints;

    private StringBuffer chars = new StringBuffer();

    public EndpointsReader(String version)
    {
        super();
        this.endpoints = new HashMap();
        this.version = version;
    }

    public boolean isSupported(String minVersionStr, String maxVersionStr)
    {
        if (version != null)
        {
            try
            {
                double minVersion = Double.parseDouble(minVersionStr);
                double maxVersion = Double.parseDouble(maxVersionStr);
                double value = Double.parseDouble(version);
                return (value >= minVersion && value <= maxVersion);
            }
            catch (NumberFormatException e)
            {
                if (log.isWarnEnabled())
                {
                    log.warn(e.getMessage(), e);
                }
                return false;
            }
        }
        return false;
    }

    public void startElement(String uri, String name, String qName, Attributes atts)
    {
        chars.setLength(0);

        if (name.equals("wsdl"))
        {
            record = true;
        }
        else if (name.equals("environment"))
        {
            environment = atts.getValue("name");
        }
        else if (name.equals("port"))
        {
            Boolean threetoken = new Boolean(atts.getValue("threetoken"));
            if (threetoken.booleanValue())
            {
                port = atts.getValue("name") + "-threetoken";
            }
            else
            {
                port = atts.getValue("name");
            }
        }
    }

    public void endElement(String uri, String name, String qName)
    {
        if (record && name.equals("port") && (environment != null) && (port != null))
        {
            endpoints.put(environment.toLowerCase() + "-" + port.toLowerCase(), chars.toString());
            if (log.isDebugEnabled())
            {
                log.debug(MessageFormat.format(MessageResources.getMessage("ENDPOINT_FOUND"),
                        new Object[] { environment, port, version, chars.toString() }));
            }
        }

        chars.setLength(0);

        if (name.equals("wsdl"))
            record = false;
    }

    public void characters(char ch[], int start, int length)
    {
        chars.append(ch, start, length);
    }

    /**
     * Return the endpoints
     * @return Map of endpoints
     */
    public Map getEndpoints()
    {
        return endpoints;
    }
} // EndpointsReader