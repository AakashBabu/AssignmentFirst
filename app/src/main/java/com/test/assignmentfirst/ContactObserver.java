package com.test.assignmentfirst;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.provider.ContactsContract;

public class ContactObserver extends ContentObserver {

    Context context;

    public ContactObserver(Context context) {
        super(null);
        this.context = context;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);

        // Since onChange do not sent which user have been changed, you
        // have to figure out how to match it with your data.
        // Note: Contact is  one of my classes.
//        for (Contact contact : getContacts()) {
//            if (!contact.isLinked())
//                continue;
//
//            String selection = ContactsContract.Data._ID + " = ?";
//            String[] selectionArgs = new String[] { contact.getSystemId() };
//            String[] projection = new String[] { ContactsContract.Data.DISPLAY_NAME };
//            Cursor cursor = context.getContentResolver().query(
//                    ContactsContract.Contacts.CONTENT_URI, projection,
//                    selection, selectionArgs, null);
//
//            if (!cursor.moveToFirst())
//                return;
//
//            String name = cursor.getString(0);
//
//            if (contact.getUsername().equalsIgnoreCase(name))
//                continue;
//
//            contact.setUserName(name);
//
//        }
    }
}