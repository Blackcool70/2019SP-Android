package com.example.wtstudentsay1005064.mystore;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
/*
 * Name: Atef Yassine
 * Date: 04/12/2019
 * Description: This program will initially show a main menu where the user can click on the pictures
 *               type in a quantity, then process the order.*/
public class TransactionActivity extends AppCompatActivity {

    ArrayList<String> items = new ArrayList<String>();
    ArrayList<Double> price = new ArrayList<Double>();
    ArrayList<Integer> number_of_items = new ArrayList<Integer>();
    ArrayList<Double> total = new ArrayList<Double>();

    Double subTotal=0.00;
    TextView tv1,tv2,tv3;
    TextView tv4,tv5,tv6;


    TableRow row,row2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        Bundle extras = getIntent().getExtras();

        ArrayList<String> items =
                (ArrayList<String>) getIntent().getSerializableExtra("send-name");
        ArrayList<Double> price =
                (ArrayList<Double>) getIntent().getSerializableExtra("send-price");
        ArrayList<Integer> number_of_items =
                (ArrayList<Integer>) getIntent().getSerializableExtra("send-quant");
        ArrayList<Double> total =
                (ArrayList<Double>) getIntent().getSerializableExtra("send-total");

        TableLayout table = findViewById(R.id.TableLayout2);


        for(int index = 0; index < items.size();index++){

            total.add(number_of_items.get(index) * price.get(index));
        }

        for(int j = 0;j< items.size();j++){
            subTotal += total.get(j);
        }
        for(int i = 0; i < items.size(); i++){

            row = new TableRow(this);

            tv1 = new TextView(this);
            tv2 = new TextView(this);
            tv3 = new TextView(this);

            tv1.setText(items.get(i));
            tv1.setTypeface(Typeface.DEFAULT_BOLD);

            tv2.setText(String.format("%d",number_of_items.get(i)));
            tv2.setTypeface(Typeface.DEFAULT_BOLD);

            tv3.setText(String.format("$%,.2f",total.get(i)));
            tv3.setTypeface(Typeface.DEFAULT_BOLD);


            tv1.setPadding(20,0,30,0);
            tv2.setPadding(0,0,20,0);
            tv3.setPadding(0,0,20,0);


            row.addView(tv1);
            row.addView(tv2);
            row.addView(tv3);

            table.addView(row);
        }
        row = new TableRow(this);
        tv4 = new TextView(this);
        tv6 = new TextView(this);
        tv5 = new TextView(this);

        tv4.setText("Sub Total");
        tv5.setText(String.format("$%,.2f",subTotal));

        tv4.setTypeface(Typeface.DEFAULT_BOLD);
        tv5.setTypeface(Typeface.DEFAULT_BOLD);

        tv4.setPadding(20,0,30,0);
        tv6.setPadding(0,0,20,0);
        tv5.setPadding(0,0,20,0);

        row.addView(tv4);
        row.addView(tv6);
        row.addView(tv5);

        table.addView(row);


        row = new TableRow(this);
        tv4 = new TextView(this);
        tv6 = new TextView(this);
        tv5 = new TextView(this);

        tv4.setText("Tax (8.25%)");
        Double tax = subTotal * 0.0825;
        tv5.setText(String.format("$%,.2f",tax));

        tv4.setTypeface(Typeface.DEFAULT_BOLD);
        tv5.setTypeface(Typeface.DEFAULT_BOLD);

        tv4.setPadding(20,0,30,0);
        tv6.setPadding(0,0,20,0);
        tv5.setPadding(0,0,20,0);

        row.addView(tv4);
        row.addView(tv6);
        row.addView(tv5);

        table.addView(row);

        row = new TableRow(this);
        tv4 = new TextView(this);
        tv6 = new TextView(this);
        tv5 = new TextView(this);

        tv4.setText("Total");
        subTotal = subTotal + tax;
        tv5.setText(String.format("$%,.2f",subTotal));

        tv4.setTypeface(Typeface.DEFAULT_BOLD);
        tv5.setTypeface(Typeface.DEFAULT_BOLD);

        tv4.setPadding(20,0,30,0);
        tv6.setPadding(0,0,20,0);
        tv5.setPadding(0,0,20,0);

        row.addView(tv4);
        row.addView(tv6);
        row.addView(tv5);

        table.addView(row);



    }
    }

