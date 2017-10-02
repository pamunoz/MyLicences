package com.pfariasmunoz.mylicences.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

// Local imports
import com.pfariasmunoz.mylicences.data.LicenceContract.LicenceEntry;

/**
 * Created by Pablo Farias on 30-09-17.
 */

public class LicenceProvider extends ContentProvider {

    /** Tag for the log messages */
    public static final String LOG_TAG = LicenceProvider.class.getSimpleName();

    /** URI matcher code for the content URI for the licences table */
    private static final int LICENCES = 100;

    /** URI matcher code for the content URI for a single licence in the licences table */
    private static final int LICENCE_ID = 101;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        // The content URI of the form "content://com.pfariasmunoz.mylicences/licences" will map to the
        // integer code {@link #LICENCES}. This URI is used to provide access to MULTIPLE rows
        // of the licences tale.
        sUriMatcher.addURI(LicenceContract.CONTENT_AUTHORITY, LicenceContract.PATH_LICENCES, LICENCES);

        // The content URI of the form "content://com.pfariasmunoz.mylicences/licences/#" will map to the
        // integer code {@link #LICENCE_ID}. This URI is used to provide access to ONE single row
        // or the licences table.
        //
        // In this case, the "#" wildcard is used where "#" can e substituted for an integer,.
        // For example, "content://com.pfariasmunoz.mylicences/licences/3" matches, or
        // "content://com.pfariasmunoz.mylicences/licences" (without a number at the end) doesn't match.
        sUriMatcher.addURI(
                LicenceContract.CONTENT_AUTHORITY, LicenceContract.PATH_LICENCES +"/#", LICENCE_ID);
    }

    /** Database helper object */
    private LicenceDbHelper mDbHelper;

    /**
     * Initialize the provider and the database helper object.
     */
    @Override
    public boolean onCreate() {
        // Make sure the variable is a global variable, so it can be referenced from other
        // ContentProvider methods.
        mDbHelper = new LicenceDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(
            @NonNull Uri uri,
            @Nullable String[] projection,
            @Nullable String selection,
            @Nullable String[] selectionArgs,
            @Nullable String sortOrder) {

        // Get readable database
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        // This cursor will hold the the result of the query
        Cursor cursor;

        // Find out if the uriMatcher can match the uri to a specific code.
        int match = sUriMatcher.match(uri);

        switch (match) {
            case LICENCES:
                // For the LICENCES code query directly the licences table with the given
                // projection, selection, selection arguments, and sort order. The cursor could
                // contain multiples rows of the licences table.
                cursor = database.query(LicenceEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case LICENCE_ID:
                // For the LICENCE_ID code, extract out the ID from the URI.
                // for an example URI, such ass: "content://com.pfariasmunoz.mylicences/licences/3",
                // the selection will be "_id=?" and the selection argument will be a
                // String array containing the actual ID of 3 in this case.
                //
                // For every "?" in the selection, we need to have an element in the selection
                // argument that will fill in the "?". Since we have one "?" in the selection
                // we have 1 String in the selection arguments String array.
                selection = LicenceEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};

                // this will perform a query in the licences table where the id equals 3 to return a
                // Cursor containing that row of the table.
                cursor = database.query(LicenceEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown uri: " + uri);
        }
        // Set the notification URI on the Cursor
        // so we know what content URI the Cursor was created for.
        // iF the data at this URI changes, then we know we need to update the cursor.
        if (getContext() != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        // Return the Cursor.
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(
            @NonNull Uri uri,
            @Nullable ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case LICENCES:
                return insert(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
}
