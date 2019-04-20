package com.example.wtstudentsay1005064.phonebook;

/*
    This is a helper class and its purpose is to display the table content stored in
    the content_provider class. It creates a TableLayout view dynamically.
    When adding this class remember to go New->Activity->Empty Activity-> and give
    an Activity name, and layout file name.
 */

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        String [] projection = {MyPhoneBook.COLUMN_ID,MyPhoneBook.COLUMN_FIRSTNAME,
                MyPhoneBook.COLUMN_LASTNAME,
                MyPhoneBook.COLUMN_PHONENUMBER };
        Cursor cursor = getContentResolver().query(MyPhoneBook.CONTENT_URI,projection ,
                null ,null );
        int count = cursor.getCount();
        cursor.moveToFirst();
        TableLayout tableLayout = new TableLayout(this);
        TableRow tableRow;
        TextView t1, t2, t3;
        tableLayout.setVerticalScrollBarEnabled(true);
        t1 = new TextView(this);
        t1.setText("First Name");
        t1.setTextColor(Color.RED);
        t1.setTypeface(null, Typeface.BOLD);
        t1.setPadding(20,20, 20,20  );
        t2 = new TextView(this);
        t2.setText("Last Name");
        t2.setTextColor(Color.RED);
        t2.setTypeface(null, Typeface.BOLD);
        t2.setPadding(20,20, 20,20  );
        t3 = new TextView(this);
        t3.setText("Phone Number");
        t3.setTextColor(Color.RED);
        t3.setTypeface(null, Typeface.BOLD);
        t3.setPadding(20,20, 20,20  );
        tableRow = new TableRow(this);
        tableRow.addView(t1);
        tableRow.addView(t2);
        tableRow.addView(t3);
        tableLayout.addView(tableRow);
        for(int i = 0; i < count; i++){
            t1 = new TextView(this);
            t1.setText(cursor.getString(cursor.getColumnIndex("FirstName")));
            t1.setTextColor(Color.BLACK);
            t1.setPadding(25,20, 20,20  );
            t2 = new TextView(this);
            t2.setText(cursor.getString(cursor.getColumnIndex("LastName")));
            t2.setTextColor(Color.BLACK);
            t2.setPadding(20,20, 20,20  );
            t3 = new TextView(this);
            t3.setText(cursor.getString(cursor.getColumnIndex("PhoneNum")));
            t3.setTextColor(Color.BLACK);
            t3.setPadding(20,20, 20,20  );
            tableRow = new TableRow(this);
            tableRow.addView(t1);
            tableRow.addView(t2);
            tableRow.addView(t3);
            tableLayout.addView(tableRow);
            cursor.moveToNext();
            Log.i("ContentProvider " , "I am here 0");
        }
        setContentView(tableLayout);

    }
}
