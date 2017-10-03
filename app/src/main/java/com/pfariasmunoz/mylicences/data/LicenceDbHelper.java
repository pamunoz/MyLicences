package com.pfariasmunoz.mylicences.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.pfariasmunoz.mylicences.data.LicenceContract.LicenceEntry;

/**
 * Created by Pablo Farias on 30-09-17.
 */

/**
 * Database helper for Licences app. Manages database creation and version management.
 */
public class LicenceDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = LicenceDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "licences.db";

    /** Database version.
     * If you change the database schema, you must change its version */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link LicenceDbHelper}.
     *
     * @param context of the app
     */
    public LicenceDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // Create a String that contains the SQL statement to create the licence table
        String SQL_CREATE_LICENCE_TABLE =  "CREATE TABLE " + LicenceEntry.TABLE_NAME + " ("
                + LicenceEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LicenceEntry.COLUMN_LICENCE_NUMBER + " TEXT NOT NULL, "
                + LicenceEntry.COLUMN_LICENCE_DURATION + " INTEGER NOT NULL DEFAULT 0, "
                + LicenceEntry.COLUMN_LICENCE_START_DATE + " TEXT NOT NULL, "
                + LicenceEntry.COLUMN_LICENCE_END_DATE + " TEXT NOT NULL);";

        // Execute the SQL statement
        database.execSQL(SQL_CREATE_LICENCE_TABLE);

    }

    public void dropAndRecreateTable(SQLiteDatabase database) {
        dropTable(database);
        onCreate(database);
    }

    public void dropTable(SQLiteDatabase database) {
        database.execSQL("DROP TABLE IF EXISTS " + LicenceEntry.TABLE_NAME);
    }
    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        dropAndRecreateTable(database);
    }



}
