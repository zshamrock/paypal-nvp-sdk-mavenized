/*
 * Copyright 2005 PayPal, Inc. All Rights Reserved.
 */

package com.paypal.sdk.util.converters;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * Custom XStream converter handles object-to-xml conversion and vice-versa for axis-generated types
 * @author PayPal DTS
 */
public class CalendarConverter implements Converter
{
    private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);

    public boolean canConvert(Class type)
    {
        return type.equals(GregorianCalendar.class);
    }

    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context)
    {
        writer.setValue(df.format(((Calendar) source).getTime()));
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context)
    {
        Calendar calendar = Calendar.getInstance();
        try
        {
            calendar.setTime(df.parse(reader.getValue()));
        }
        catch (Exception e)
        {
        }
        return calendar;
    }
   public static Calendar convertToCalendar(String date){
	   Calendar calendar = Calendar.getInstance();
	   try
       {
           calendar.setTime(df.parse(date));
       }
       catch (Exception e)
       {
       }
       return calendar;
   }
} // TokenConverter