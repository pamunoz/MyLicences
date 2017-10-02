package com.pfariasmunoz.mylicences.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
