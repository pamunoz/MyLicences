package com.pfariasmunoz.mylicences.data;

/**
 * Created by Pablo Farias on 05-10-17.
 */

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.pfariasmunoz.mylicences.data.LicenceContract.LicenceEntry;

/**
 * Utility class for creating a fake database
 */
public class TestUtil {
    public static void insertFakeData(SQLiteDatabase db) {
        if (db == null) {
            return;
        }
        // Create a List of fake Licences
        List<ContentValues> list = new ArrayList<>();

        ContentValues cv = new ContentValues();
        cv.put(LicenceEntry.COLUMN_LICENCE_NUMBER, "12345678");
        cv.put(LicenceEntry.COLUMN_LICENCE_DURATION, 30);
        cv.put(LicenceEntry.COLUMN_LICENCE_START_DATE, "10-12-2016");
        cv.put(LicenceEntry.COLUMN_LICENCE_END_DATE, "10-01-2017");
        list.add(cv);

        cv = new ContentValues();
        cv.put(LicenceEntry.COLUMN_LICENCE_NUMBER, "23456789");
        cv.put(LicenceEntry.COLUMN_LICENCE_DURATION, 31);
        cv.put(LicenceEntry.COLUMN_LICENCE_START_DATE, "10-12-2015");
        cv.put(LicenceEntry.COLUMN_LICENCE_END_DATE, "10-01-2016");
        list.add(cv);

        cv = new ContentValues();
        cv.put(LicenceEntry.COLUMN_LICENCE_NUMBER, "12345677");
        cv.put(LicenceEntry.COLUMN_LICENCE_DURATION, 30);
        cv.put(LicenceEntry.COLUMN_LICENCE_START_DATE, "20-12-2015");
        cv.put(LicenceEntry.COLUMN_LICENCE_END_DATE, "20-01-2016");
        list.add(cv);

        cv = new ContentValues();
        cv.put(LicenceEntry.COLUMN_LICENCE_NUMBER, "11123456");
        cv.put(LicenceEntry.COLUMN_LICENCE_DURATION, 28);
        cv.put(LicenceEntry.COLUMN_LICENCE_START_DATE, "20-12-2015");
        cv.put(LicenceEntry.COLUMN_LICENCE_END_DATE, "20-01-2016");
        list.add(cv);

        cv = new ContentValues();
        cv.put(LicenceEntry.COLUMN_LICENCE_NUMBER, "11122234");
        cv.put(LicenceEntry.COLUMN_LICENCE_DURATION, 10);
        cv.put(LicenceEntry.COLUMN_LICENCE_START_DATE, "20-12-2015");
        cv.put(LicenceEntry.COLUMN_LICENCE_END_DATE, "20-01-2016");
        list.add(cv);

        // Insert all licences in one transaction
        try {
            db.beginTransaction();
            // clear the table first
            db.delete(LicenceEntry.TABLE_NAME, null, null);
            // go through the list and add one by one
            for (ContentValues value : list) {
                db.insert(LicenceEntry.TABLE_NAME, null, value);
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            // too bad
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

    }
}
