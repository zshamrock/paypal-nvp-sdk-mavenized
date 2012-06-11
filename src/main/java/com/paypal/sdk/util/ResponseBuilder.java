package com.paypal.sdk.util;

import java.util.Iterator;

import com.paypal.sdk.core.nvp.NVPDecoder;

public class ResponseBuilder {
	public static String BuildResponse(Object response, String header1,
			String header2)throws Exception
	{
		if (response != null)
		{
			NVPDecoder resultValues=null;
			try{
			  resultValues =(NVPDecoder)response;
			}catch(Exception ex){
				throw ex;
			}

			String res = "<center>";

			if (header1 != null)
				res = res + "<font size=2 color=black face=Verdana><b>"
							+ header1 + "</b></font>";
				res = res + "<br>";
				res = res + "<br>";

			if (header2 != null)
				res = res + "<b>" + header2 + "</b><br>";
			    res = res + "<br>";
			    res = res + "<table width=400 class=api>";
            
		    Iterator itr=resultValues.getMap().keySet().iterator();	    
			while(itr.hasNext()){
				String key=(String)itr.next();
				res = res + "<tr><td class=field> "
						+ key + ":</td>";
				res = res + "<td>" + resultValues.getMap().get(key).toString() + "</td>";
				res = res + "</tr>";
				res = res + "<tr>";
			}
			res = res + "</table>";
			res = res + "</center>";
			return res;
		}
		else
		{
			return "Requested action not allowed";
		}

	}

}
