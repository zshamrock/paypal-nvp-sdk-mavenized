/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.core.nvp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.Socket;
import javax.net.ssl.SSLSocketFactory;

import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;

import com.paypal.sdk.core.Keys;
import com.paypal.sdk.util.Util;

public class NVPSSLSocketFactory implements SecureProtocolSocketFactory
{
	String callerID = null;

	public NVPSSLSocketFactory(String _callerID)
	{
		callerID = _callerID;
	}
	
	public Socket createSocket(String host, int port) throws IOException, UnknownHostException 
	{
		return Util.getSSLContext(null).getSocketFactory().createSocket(host,port);
	}
	
	public Socket createSocket(String host, int port, InetAddress clientHost, int clientPort) throws IOException, UnknownHostException 
	{
		return Util.getSSLContext(null).getSocketFactory().createSocket(host, port, clientHost, clientPort);
	}
	
	public Socket createSocket(String host,int port,InetAddress localAddress, int localPort, HttpConnectionParams params) throws IOException, UnknownHostException 
	{	
		SSLSocketFactory socketfactory = Util.getSSLContext(Keys.getKeyManagers(callerID)).getSocketFactory();
        return socketfactory.createSocket(host, port, localAddress, localPort);
	}

    public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException 
    {
    	SSLSocketFactory socketfactory = Util.getSSLContext(Keys.getKeyManagers(callerID)).getSocketFactory();
        return socketfactory.createSocket(socket, host, port, autoClose);
    }
    
	public boolean equals(Object obj) 
	{
		return ((obj != null) && obj.getClass().equals(NVPSSLSocketFactory.class));
	}

	public int hashCode() 
	{
		return NVPSSLSocketFactory.class.hashCode();
	}
} // NVPSSLSocketFactory