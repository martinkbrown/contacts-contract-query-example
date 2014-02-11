package co.martinbrown.example.contactscontract;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

public class ContactsContractExampleActivity extends ListActivity {

    Cursor mCursor;
    CursorAdapter mCursorAdapter;

    String[] mProjection;
    String[] mListColumns;
    String mSelectionClause;
    String[] mSelectionArgs;
    String mOrderBy;

    int[] mListItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getListView().setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {

                Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, "" + rowId));
                startActivity(viewIntent);

            }
        });

        query1();
    }

    @SuppressWarnings("deprecation")
    public void query1() {

        /* SELECT * FROM ContactsContract.Contacts */

        mCursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                null);

        mListColumns = new String[]
                {
                ContactsContract.Contacts.DISPLAY_NAME
                };

        mListItems = new int[]
                {
                R.id.contact_name
                };

        mCursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.query1,
                mCursor,
                mListColumns,
                mListItems);

        setListAdapter(mCursorAdapter);
    }

    @SuppressWarnings("deprecation")
    public void query2() {

        /* SELECT _ID, DISPLAY_NAME FROM ContactsContract.Contacts */

        mProjection = new String[]
                {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
                };

        mCursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                mProjection,
                null,
                null,
                null);

        mListColumns = new String[]
                {
                ContactsContract.Contacts.DISPLAY_NAME
                };

        mListItems = new int[]
                {
                R.id.contact_name
                };

        mCursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.query1,
                mCursor,
                mListColumns,
                mListItems);

        setListAdapter(mCursorAdapter);
    }

    public void query3() {

        /* SELECT _ID, DISPLAY_NAME FROM ContactsContract.Contacts WHERE HAS_PHONE_NUMBER = 1 */

        mProjection = new String[]
                {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
                };

        mSelectionClause = ContactsContract.Contacts.HAS_PHONE_NUMBER + " = ? ";
        mSelectionArgs = new String[]{"1"};

        mCursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                mProjection,
                mSelectionClause,
                mSelectionArgs,
                null);

        mListColumns = new String[]
                {
                ContactsContract.Contacts.DISPLAY_NAME
                };

        mListItems = new int[]
                {
                R.id.contact_name
                };

        mCursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.query1,
                mCursor,
                mListColumns,
                mListItems,
                CursorAdapter.NO_SELECTION);

        setListAdapter(mCursorAdapter);
    }

    public void query4() {

        /* SELECT _ID, DISPLAY_NAME, TIMES_CONTACTED FROM ContactsContract.Contacts WHERE HAS_PHONE_NUMBER = 1 AND TIMES_CONTACTED > 5 */

        mProjection = new String[]
                {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.TIMES_CONTACTED,
                };

        mSelectionClause = ContactsContract.Contacts.HAS_PHONE_NUMBER + " = ? AND " +
                ContactsContract.Contacts.TIMES_CONTACTED + " > ? ";

        mSelectionArgs = new String[]{"1", "5"};

        mCursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                mProjection,
                mSelectionClause,
                mSelectionArgs,
                null);

        mListColumns = new String[]
                {
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.TIMES_CONTACTED,
                };

        mListItems = new int[]
                {
                R.id.contact_name,
                R.id.times_contacted
                };

        mCursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.query4,
                mCursor,
                mListColumns,
                mListItems,
                CursorAdapter.NO_SELECTION);

        setListAdapter(mCursorAdapter);
    }

    public void query5() {

        /* SELECT _ID, DISPLAY_NAME FROM ContactsContract.Contacts WHERE HAS_PHONE_NUMBER = 1 ORDER BY DISPLAY_NAME */

        mProjection = new String[]
                {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
                };

        mSelectionClause = ContactsContract.Contacts.HAS_PHONE_NUMBER + " = ? ";
        mSelectionArgs = new String[]{"1"};
        mOrderBy = ContactsContract.Contacts.DISPLAY_NAME;

        mCursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                mProjection,
                mSelectionClause,
                mSelectionArgs,
                mOrderBy);

        mListColumns = new String[]
                {
                ContactsContract.Contacts.DISPLAY_NAME
                };

        mListItems = new int[]
                {
                R.id.contact_name
                };

        mCursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.query1,
                mCursor,
                mListColumns,
                mListItems,
                CursorAdapter.NO_SELECTION);

        setListAdapter(mCursorAdapter);
    }
}