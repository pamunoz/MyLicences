package com.pfariasmunoz.mylicences.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.test.ProviderTestCase2;
import android.test.mock.MockContentResolver;

import com.pfariasmunoz.mylicences.data.LicenceContract.LicenceEntry;

import org.junit.Test;

/**
 * Created by Pablo Farias on 03-10-17.
 */

public class LicenceProviderTest extends ProviderTestCase2<LicenceProvider> {

    // A URI that the provider does not offer, for testing error handling.
    private static final Uri INVALID_URI =
            Uri.withAppendedPath(LicenceContract.LicenceEntry.CONTENT_URI, "invalid");
    // Contains a reference to the mocked content resolver for the provider under test.
    private MockContentResolver mMockResolver;

    // Contains an SQLite database, used as test data
    private SQLiteDatabase mDb;

    // Contains the test data, as an array of NoteInfo instances.
    private final LicenceInfo[] TEST_LICENCES = {
            new LicenceInfo("1", 1, "today", "tomorrow"),
            new LicenceInfo("2", 2, "tomorrow", "today"),
            new LicenceInfo("3", 3, "today", "tomorrow"),
            new LicenceInfo("4", 4, "tomorrow", "today"),
            new LicenceInfo("5", 5, "today", "tomorrow")
    };

    // Sets a MIME type filter, used to test provider methods that return more than one MIME type
    // for a particular note. The filter will retrieve any MIME types supported for the content URI.
    private final static String MIME_TYPES_ALL = "*/*";

    // Sets a MIME type filter, used to test provider methods that return more than one MIME type
    // for a particular note. The filter is nonsense, so it will not retrieve any MIME types.
    private final static String MIME_TYPES_NONE = "qwer/qwer";

    // Sets a MIME type filter for plain text, used to the provider's methods that only handle
    // plain text
    private final static String MIME_TYPE_TEXT = "text/plain";

    public LicenceProviderTest() {
        super(LicenceProvider.class, LicenceContract.CONTENT_AUTHORITY);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Gets the resolver for this test.
        mMockResolver = getMockContentResolver();

        /*
         * Gets a handle to the database underlying the provider. Gets the provider instance
         * created in super.setUp(), gets the DatabaseOpenHelper for the provider, and gets
         * a database object from the helper.
         */
        mDb = getProvider().getDbOpenHelperForTest().getWritableDatabase();
    }

    /*
     *  This method is called after each test method, to clean up the current fixture. Since
     *  this sample test case runs in an isolated context, no cleanup is necessary.
     */

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Sets up test data.
     * The test data is in an SQL database. It is created in setUp() without any data,
     * and populated in insertData if necessary.
     */
    private void insertData() {
        // Creates an instance of the ContentValues map type expected by database insertions
        ContentValues values = new ContentValues();

        // Sets up test data
        for (int index = 0; index < TEST_LICENCES.length; index++) {
            // Adds a record to the database.
            mDb.insertOrThrow(
                    LicenceEntry.TABLE_NAME,
                    null,
                    TEST_LICENCES[index].getContentValues()
            );
            
        }

    }

     /*
     * Tests the provider's publicly available URIs. If the URI is not one that the provider
     * understands, the provider should throw an exception. It also tests the provider's getType()
     * method for each URI, which should return the MIME type associated with the URI.
     */
     

    // A utility for converting note data to a ContentValues map.
    private static class LicenceInfo {
        String number;
        int duration;
        String startDate;
        String endDate;

        /*
         * Constructor for a LicenceInfo instance. This class helps create a licence and
         * return its values in a ContentValues map expected by data model methods.
         * The licence's id is created automatically when it is inserted into the data model.
         */
        public LicenceInfo(String number, int duration, String startDate, String endDate) {
            this.number = number;
            this.duration = duration;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        /*
         * Returns a ContentValues instance (a map) for this LicenceInfo instance.
         * This is useful for inserting a LicenceInfo into a database.
         */
        public ContentValues getContentValues() {
            // Gets a new ContentValues object
            ContentValues values = new ContentValues();

            // Adds map entries for the user-controlled fields in the map
            values.put(LicenceEntry.COLUMN_LICENCE_NUMBER, number);
            values.put(LicenceEntry.COLUMN_LICENCE_DURATION, duration);
            values.put(LicenceEntry.COLUMN_LICENCE_START_DATE, startDate);
            values.put(LicenceEntry.COLUMN_LICENCE_END_DATE, endDate);
            return values;
        }
    }
}