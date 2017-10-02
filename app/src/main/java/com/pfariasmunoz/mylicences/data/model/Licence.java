package com.pfariasmunoz.mylicences.data.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Pablo Farias on 12-09-17.
 */

public class Licence {
    private Date startDate;
    private Date endDate;
    private int duration;
    private String mId;


    public Licence(Date startDate, int duration, String id) {
        this.startDate = startDate;
        this.duration = duration;
        mId = id;
        setEndDate();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    private void setEndDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        Calendar endCal = new GregorianCalendar(year, month, day);
        endCal.add(Calendar.DAY_OF_MONTH, duration);
        endDate = endCal.getTime();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }
}
