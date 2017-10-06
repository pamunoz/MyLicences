package com.pfariasmunoz.mylicences.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pablo Farias on 05-10-17.
 */

public class SimpleDateUtil {

    public static final String DATE_FORMAT = "dd MMM yyyy";

    /**
     * Method for transforming a date to a string format
     * with correct format.
     * @param date the date to be transformed to a string.
     * @return a formatted string date.
     */
    public static String getStringDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(date);
    }

    /**
     * Method for adding days to a specific date object.
     * @param numberOfDays the days to be added to the {@link Date} object.
     * @return a {@link Date} object with the added days.
     */
    public static Date addDays(Date date, int numberOfDays) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, numberOfDays);
        return cal.getTime();
    }
}
