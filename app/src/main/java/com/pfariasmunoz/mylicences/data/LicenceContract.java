package com.pfariasmunoz.mylicences.data;

/**
 * Created by Pablo Farias on 30-09-17.
 */

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * API Contract for the Licences app.
 */
public class LicenceContract {
    public static final String TAG = LicenceContract.class.getSimpleName();

    /**
     * the Content Authority which is used to help identify the Content Provider
     * which we’d setup before in the AndroidManifest
     */
    public static final String CONTENT_AUTHORITY = "com.pfariasmunoz.mylicences";

    /**
     * we concatonate the CONTENT_AUTHORITY constant with the scheme “content://”
     * we will create the BASE_CONTENT_URI which will be shared by every URI
     * associated with {@link LicenceContract}
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * This constants stores the path for each of the tables which will be appended
     * to the base content URI.
     */
    public static final String PATH_LICENCES = "licences";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private LicenceContract(){}

    /**
     * Inner class that defines constant values for the licences database table.
     * Each entry in the table represents a single licence.
     */
    public static class LicenceEntry implements BaseColumns {
        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of pets.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LICENCES;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LICENCES;

        /**
         * inside each of the Entry classes in the contract,
         * we create a full URI for the class as a constant called CONTENT_URI.
         * The Uri.withAppendedPath() method appends the BASE_CONTENT_URI
         * (which contains the scheme and the content authority) to the path segment.
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_LICENCES);

        /** Name of database table for licences */
        public static final String TABLE_NAME = "licences";

        /**
         * Unique ID number for the licence (only for use in the database table).
         *
         * Type: INTEGER
         */
        public static final String _ID = BaseColumns._ID;

        /**
         * Number of the licence.
         *
         * Type: TEXT
         */
        public static final String COLUMN_LICENCE_NUMBER = "number";

        /**
         * Start Date of the licence.
         *
         * Type: TEXT
         */
        public static final String COLUMN_LICENCE_START_DATE = "start_date";

        /**
         * End Date of the licence.
         *
         * Type: TEXT
         */
        public static final String COLUMN_LICENCE_END_DATE = "end_date";

        /**
         * Duration of the licence.
         *
         * Type: INTEGER
         */
        public static final String COLUMN_LICENCE_DURATION = "duration";

    }
}
