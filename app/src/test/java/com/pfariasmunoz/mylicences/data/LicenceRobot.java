package com.pfariasmunoz.mylicences.data;

import com.pfariasmunoz.mylicences.data.model.Licence;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Pablo Farias on 13-09-17.
 */

public class LicenceRobot {

    private Date expectedDate;
    private Date actualDate;


    public LicenceRobot checkEndDate(Licence licence) {
        int duration = licence.getDuration();
        Calendar startCalendar = new GregorianCalendar(2017, 0, 1);
        Calendar endCalendar = new GregorianCalendar(startCalendar.get(Calendar.YEAR),
                startCalendar.get(Calendar.MONTH),
                startCalendar.get(Calendar.DAY_OF_MONTH) + duration);
        expectedDate = endCalendar.getTime();
        actualDate = licence.getEndDate();
        return this;
    }

    public LicenceRobot doSomething() {
        return this;
    }

    public LicenceRobot finish() {
        return this;
    }

}
