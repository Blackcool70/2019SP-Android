package com.example.wtstudentsay1005064.phonebook;

import android.content.ContentValues;
import android.content.Intent;
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
* Description: This program uses SQLite to emulate a phone book. It adds,deletes, clears
*               fields and shows the list
*               */
public class PhoneBookActivity extends AppCompatActivity {
    TextView idView;
    EditText firstName, lastName, phoneNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_book);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        phoneNum = findViewById(R.id.phonenumber);
    }
    public void show(View view){
        firstName.setText("");
        lastName.setText("" );
        phoneNum.setText("" );
        firstName.requestFocus();
        Intent intent = new Intent(this, DisplayActivity.class);
        startActivity(intent);

    }

    public void newContact(View view){
        Info product = new Info(firstName.getText().toString(), lastName.getText().toString(),
                                phoneNum.getText().toString());
        ContentValues values = new ContentValues();

        values.put(MyPhoneBook.COLUMN_FIRSTNAME,product.getFirstName());
        values.put(MyPhoneBook.COLUMN_LASTNAME,product.getLastName());
        values.put(MyPhoneBook.COLUMN_PHONENUMBER,product.getPhoneNum() );

        Uri uri = getContentResolver().insert(MyPhoneBook.CONTENT_URI,values );
        firstName.setText("" );
        lastName.setText("" );
        phoneNum.setText("" );
        firstName.requestFocus();
        Toast.makeText(this, "Contact was added", Toast.LENGTH_SHORT).show();




    }
    public void clear(View view){
        firstName.setText("");
        lastName.setText("" );
        phoneNum.setText("" );
        firstName.requestFocus();
    }

    public void removeContact(View view){
        String[] selectionArgs = new String [2];
        selectionArgs[0] = firstName.getText().toString();
        selectionArgs[1] = lastName.getText().toString();
        int result = getContentResolver().delete(MyPhoneBook.CONTENT_URI,"FirstName=? AND LastName=?" ,selectionArgs );
        if(result > 0){
            Toast.makeText(this, "Contact was deleted", Toast.LENGTH_SHORT).show();
            firstName.setText("" );
            lastName.setText("" );
            phoneNum.setText("" );
        }
        else
            Toast.makeText(this, "Contact was not found", Toast.LENGTH_SHORT).show();
    }
}
