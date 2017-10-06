package com.pfariasmunoz.mylicences.util;

import android.text.method.SingleLineTransformationMethod;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.R.attr.format;
import static org.junit.Assert.*;

/**
 * Created by Pablo Farias on 05-10-17.
 */
public class SimpleDateUtilTest {

    @Test
    public void date_to_string_test() {
        Date date = new Date();
        String expected = "05 oct 2017";
        String actual = SimpleDateUtil.getStringDate(date);
        assertEquals("The method return a wrong formatted string date", expected, actual);
    }

    @Test
    public void add_days_test() {
        Date date = new Date();
        Date newDate = SimpleDateUtil.addDays(date, 2);
        String actual = SimpleDateUtil.getStringDate(newDate);
        String expected = "07 oct 2017";
        assertEquals("The days on the date are not correctly added", expected, actual);
    }

}