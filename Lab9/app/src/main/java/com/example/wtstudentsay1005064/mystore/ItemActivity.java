package com.example.wtstudentsay1005064.mystore;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class ItemActivity extends AppCompatActivity {

    ArrayList<String> items = new ArrayList<String>();
    ArrayList<Double> price = new ArrayList<Double>();
    ArrayList<Integer> number_of_items = new ArrayList<Integer>();
    ArrayList<Double> total = new ArrayList<Double>();

    TextView item_name;
    EditText quant;
    int quantity;
    String itemName;
    Double itemPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Bundle extras = getIntent().getExtras();

        itemName  = extras.getString("send-itemName", "");
        itemPrice = extras.getDouble("send-itemPrice",0);

        item_name = findViewById(R.id.itemname);
        quant     = findViewById(R.id.quantity);

        item_name.setText(itemName);

        ArrayList<String> items =
                (ArrayList<String>) getIntent().getSerializableExtra("send-name");
        ArrayList<Double> price =
                (ArrayList<Double>) getIntent().getSerializableExtra("send-price");
        ArrayList<Integer> number_of_items =
                (ArrayList<Integer>) getIntent().getSerializableExtra("send-quant");
        ArrayList<Double> total =
                (ArrayList<Double>) getIntent().getSerializableExtra("send-total");


        TextView tv1,tv3;
        TableLayout table = findViewById(R.id.TableLayout);

        TableRow row;

        for(int i = 0; i < items.size(); i++){
            row = new TableRow(this);

            tv1 = new TextView(this);
            tv3 = new TextView(this);

            tv1.setText(items.get(i));
            tv1.setTypeface(Typeface.DEFAULT_BOLD);

            tv3.setText(String.format("%d",number_of_items.get(i)));
            tv3.setTypeface(Typeface.DEFAULT_BOLD);

            tv1.setPadding(20,0,30,0);
            tv3.setPadding(0,0,20,0);


            row.addView(tv1);
            row.addView(tv3);

            table.addView(row);
        }
    }
    @Override
    public void finish() {

        try {
            quantity = Integer.parseInt(quant.getText().toString());
        }
        catch (Exception e){
            quantity = 0;
        }

        Intent i = new Intent();
        i.putExtra("back-name", itemName);
        i.putExtra("back-quant", quantity);
        i.putExtra("back-price", itemPrice);

        setResult(RESULT_OK, i);
        super.finish();
    }
}
