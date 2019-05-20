package com.test.assignmentfirst;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Contacts contacts = (Contacts)getIntent().getSerializableExtra("data");

        getContactName(contacts.getId());

    }

    private void getContactName(String id) {
        Cursor cursor = null;
        String result = "";

        try {
            cursor = getContentResolver().query(ContactsContract
                            .Data.CONTENT_URI,
                    null,
                    ContactsContract.Data.CONTACT_ID + "=?",
                    new String[] { id },
                    null);

            if (cursor.moveToFirst()) {
                result = cursor.getString(cursor.getColumnIndex
                        (ContactsContract.Data.DISPLAY_NAME));

                // Get all columns
                String columns[] = cursor.getColumnNames();
                for (String column : columns) {
                    Log.v("responce","columns "+column);
                    String val = cursor.getString(cursor.getColumnIndex
                            (column));
                    Log.v("responce","Value "+val);
                }

                cursor.close();
            }
        } catch (Exception e) {
            Log.v("responce","E --" +e);
        }
    }

}
