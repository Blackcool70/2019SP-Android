package com.example.wtstudentsay1005064.shoppingcalculator;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
* This program simulates a shopping calculator that
* calculates items based on a inserted rate.
*
* This program uses Activity life-cycle concept to switch
* between activities
*
* 1. TableActivity
* 2. Shopping2Activity
*
* Authors: Atef Yassine & Ibsa Ahmed
* Version: 03/04/2019
*
* ***********************************
* Completion percentage: Atef: 50%  *
*                        Ibsa: 50%  *
*                       Total: 100% *
* ***********************************
*
*	Atef's & Ibsa's part -- group effort.
*
* */
public class ShoppingActivity extends AppCompatActivity {

    ArrayList<String> items = new ArrayList<>();
    ArrayList<Double> price = new ArrayList<>();
    ArrayList<Integer> quant = new ArrayList<>();
    ArrayList<Double> total = new ArrayList<>();


    EditText inPrice, inQuantity,inTax,outTotal,inName;
    Button findPrice,add_item,show_list;

    final int MY_REQUEST_CODE = 1;

    String name;
    Double amount,tax,afterTax;
    int quantity;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        //fields
        inName = findViewById(R.id.item_name);
        inPrice = findViewById(R.id.price);
        inQuantity = findViewById(R.id.quantity);
        inTax = findViewById(R.id.tax);
        outTotal = findViewById(R.id.total);

        //buttons
        findPrice = findViewById(R.id.compute);
        add_item  = findViewById(R.id.add_item);
        show_list = findViewById(R.id.show_list);



        findPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 name   = inName.getText().toString();
                 amount = Double.parseDouble(inPrice.getText().toString());
                 quantity = Integer.parseInt(inQuantity.getText().toString());
                 tax = Double.parseDouble(inTax.getText().toString());

                 Calculate();

                 //disables & enables buttons after invoking the following statements
                 findPrice.setEnabled(false);
                 add_item.setEnabled(true);
                 show_list.setEnabled(true);

                 //adding items to arrays
                 AddItems();
            }
        });

        show_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingActivity.this,TableActivity.class);
                intent.putExtra("send-name", items);
                intent.putExtra("send-price", price);
                intent.putExtra("send-quant", quant);
                intent.putExtra("send-total",total);

                startActivityForResult(intent,MY_REQUEST_CODE);
                /* *Start Activity Two !!!!* */

            }
        });

        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingActivity.this,Shopping2Activity.class);
                intent.putExtra("send-tax",tax);
                intent.putExtra("send-count",count);
                startActivityForResult(intent,MY_REQUEST_CODE);
                /* *Start Activity Three !!!!* */

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_REQUEST_CODE){
            if (resultCode == RESULT_OK){

                name = data.getExtras().getString("name");
                amount  = data.getExtras().getDouble("amount");
                quantity = data.getExtras().getInt("quantity");

                Calculate();

                if(name != "" && amount !=0.00 && quantity != 0) {
                    AddItems();
                }
            }
        }
    }

    public void Calculate(){

        afterTax = amount * quantity + amount*quantity*tax/100;

        String afterTaxString = String.format("$%,.2f",afterTax);

        inName.setText(name);
        inName.setEnabled(false);

        inPrice.setText("$" + (String.format("%,.2f", amount)));
        inPrice.setEnabled(false);

        inQuantity.setText(String.format("%d", quantity));
        inQuantity.setEnabled(false);

        inTax.setText(String.format("%.2f",tax));
        inTax.setEnabled(false);

        outTotal.setText(afterTaxString);
        outTotal.setEnabled(false);

    }

    public void AddItems(){
        items.add(name);
        price.add(amount);
        quant.add(quantity);
        total.add(afterTax);
        count++;
    }
}
