package com.pfariasmunoz.mylicences.data;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Pablo Farias on 13-09-17.
 */

public abstract class DataRobot<T extends DataRobot> {

    /**
     * create the objects that are used for the test
     */
    public T createCalendar(int year, int month, int daysOfMonth) {
        Calendar startCalendar = new GregorianCalendar(year, month, daysOfMonth);
        startCalendar.getTime();
        return (T) this;
    }
}
