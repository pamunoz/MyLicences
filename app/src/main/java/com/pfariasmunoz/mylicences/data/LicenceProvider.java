package com.pfariasmunoz.mylicences.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
