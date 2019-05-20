package com.test.assignmentfirst;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    List<Contacts> contact = new ArrayList();
    String TAG= "responce";
    RecyclerView rcv_list;
    ContactAdapter adapter;
    Button btn_permission;
    private int PERMISSION = 10101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_permission = findViewById(R.id.btn_permission);
        btn_permission.setOnClickListener(this);

        setAdapter();

        runtimePermission();
    }

    private void runtimePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(
                        new String[]{Manifest.permission
                                .READ_CONTACTS},
                        PERMISSION);
            } else {
                getContactList();
            }
        } else {
            getContactList();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Contact permission granted", Toast.LENGTH_LONG).show();
                getContactList();
            } else {
                Toast.makeText(this, "Contact permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void getContactList() {
        btn_permission.setVisibility(View.GONE);
        contact.clear();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                Contacts con = new Contacts();
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Log.v(TAG, "Name: " + name);
                        Log.v(TAG, "Phone Number: " + phoneNo);
                        if(id != null)
                            con.setId(id);
                        if(name != null)
                            con.setName(name);
                        if(phoneNo != null)
                            con.setNumber(phoneNo);
                    }
                    pCur.close();
                }
                contact.add(con);
            }
        }
        if(cur!=null){
            cur.close();
        }
        adapter.loadContacts(contact);
    }

    public void setAdapter(){
        rcv_list = (RecyclerView)findViewById(R.id.rcv_list);
        rcv_list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactAdapter(this);
        rcv_list.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        runtimePermission();
    }
}
