package com.example.wtstudentsay1005064.makeacall;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/*
* Name: Atef Yassine
* Version: 04/11/2019
* Description: This code fetches a contact number given First name and last name and makes a call
*
* */
public class CallActivity extends AppCompatActivity {

    private static  final  String AUTHORITY =
            "com.example.wtstudentsay1005064.phonebook.MyPhoneBook";
    private static final String DATABASE_NAME = "phoneBookDB.db";
    private static final String TABLE_PRODUCT = "Info";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRSTNAME = "FirstName";
    public static final String COLUMN_LASTNAME = "LastName";
    public static final String COLUMN_PHONENUMBER = "PhoneNum";

    TextView firstN;
    TextView lastN;
    TextView number;
    Uri CONTENT_URI;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        firstN = findViewById(R.id.firstName);
        lastN  = findViewById(R.id.lastName);
        number = findViewById(R.id.number);
        CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/"+ TABLE_PRODUCT);
    }

    public void findContact(View view){

                String [] projection = {COLUMN_ID,COLUMN_FIRSTNAME,
                COLUMN_LASTNAME,
                COLUMN_PHONENUMBER};

        String [] selectionArgs = {firstN.getText().toString(),lastN.getText().toString()};
        Cursor cursor = getContentResolver().query(CONTENT_URI,projection ,"FirstName = ? AND LastName = ?" ,selectionArgs , null);
        Info product = new Info();
        if(cursor.moveToFirst()){
            product.setFirstName(cursor.getString(1));
            product.setLastName(cursor.getString(2));
            product.setPhoneNum(cursor.getString(3));
            cursor.close();
        }
        else
            product = null;
        if(product != null){

            number.setText(String.valueOf(product.getPhoneNum()));
            phone = String.valueOf(product.getPhoneNum());
            number.setTextColor(Color.BLACK);

        }
        else
            number.setText("Contact was not found");
    }
    public  void makeCall(View view){

        try {
                phone = phone.replaceAll("\\-", "");
                Uri uri = Uri.parse("tel:" + phone);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
            catch (ActivityNotFoundException e){
                Toast.makeText(this ,"Application failed" ,Toast.LENGTH_SHORT ).show();
            }
        }
    }


