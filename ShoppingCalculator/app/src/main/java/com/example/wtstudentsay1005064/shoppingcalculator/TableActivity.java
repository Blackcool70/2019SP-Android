package com.example.wtstudentsay1005064.shoppingcalculator;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// Atef Yassine's part

public class TableActivity extends AppCompatActivity {

        ArrayList<String> items = new ArrayList<String>();
        ArrayList<Double> price = new ArrayList<Double>();
        ArrayList<Integer> number_of_items = new ArrayList<Integer>();
        ArrayList<Double> total = new ArrayList<Double>();

    EditText t1;
    double realTotal=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        ArrayList<String> items =
                (ArrayList<String>) getIntent().getSerializableExtra("send-name");
        ArrayList<Double> price =
                (ArrayList<Double>) getIntent().getSerializableExtra("send-price");
        ArrayList<Integer> number_of_items =
                (ArrayList<Integer>) getIntent().getSerializableExtra("send-quant");
        ArrayList<Double> total =
                (ArrayList<Double>) getIntent().getSerializableExtra("send-total");


        TextView tv1,tv2,tv3;
        TableLayout table = findViewById(R.id.TableLayout);

        t1 = findViewById(R.id.total);

        TableRow row;

        for(int i = 0; i < total.size(); i++){
            realTotal += total.get(i);
        }

        String afterTaxString = String.format("$%,.2f",realTotal);


        t1.setText(afterTaxString);

        for(int i = 0; i < items.size(); i++){
            row = new TableRow(this);

            tv1 = new TextView(this);
            tv2 = new TextView(this);
            tv3 = new TextView(this);

            tv1.setText(items.get(i));
            tv1.setTypeface(Typeface.DEFAULT_BOLD);

            // integer between 10 and 80
            tv2.setText(String.format("$%.2f",price.get(i)));
            tv2.setTypeface(Typeface.DEFAULT_BOLD);

            tv3.setText(String.format("%d",number_of_items.get(i)));
            tv3.setTypeface(Typeface.DEFAULT_BOLD);

            tv1.setPadding(20,0,30,0);
            tv2.setPadding(0,0,20,0);
            tv3.setPadding(0,0,20,0);


            row.addView(tv1);
            row.addView(tv2);
            row.addView(tv3);

            table.addView(row);
        }

    }

}
