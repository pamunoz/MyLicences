package com.pfariasmunoz.mylicences.data;

import com.pfariasmunoz.mylicences.data.model.Licence;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Created by Pablo Farias on 12-09-17.
 */
public class LicenceTest {

    @Test
    public void checkForDates() {
        int duration = 2;
        Calendar startCalendar = new GregorianCalendar(2017, 0, 1);
        Calendar endCalendar = new GregorianCalendar(startCalendar.get(Calendar.YEAR),
                startCalendar.get(Calendar.MONTH),
                startCalendar.get(Calendar.DAY_OF_MONTH) + duration);

        Licence licence = new Licence(startCalendar.getTime(), duration, "23487449");
        Date expectedEndDate = endCalendar.getTime();
        Date actualEndDate = licence.getEndDate();
        assertEquals("Dates", expectedEndDate, actualEndDate);
        new LicenceRobot().checkEndDate(licence).doSomething().finish();
    }
}