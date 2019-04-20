package com.example.wtstudentsay1005064.mystore;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/*
* Name: Atef Yassine
* Date: 04/12/2019
* Description: This program will initially show a main menu where the user can click on the pictures
*               type in a quantity, then process the order.*/
public class StoreActivity extends AppCompatActivity {

    ArrayList<String>  items = new ArrayList<>();
    ArrayList<Double>  price = new ArrayList<>();
    ArrayList<Integer> quant = new ArrayList<>();
    ArrayList<Double>  total = new ArrayList<>();

    String itemName = "";
    Double itemPrice = 0.00;
    final int MY_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
    }

    /*To be continued!!*/
    public void dasani(View view){

        Intent intent = new Intent(StoreActivity.this,ItemActivity.class);
        itemName = "Dasani Water";
        itemPrice = 2.00;
        intent.putExtra("send-itemName",itemName);
        intent.putExtra("send-itemPrice",itemPrice);
        intent.putExtra("send-name", items);
        intent.putExtra("send-price", price);
        intent.putExtra("send-quant", quant);
        intent.putExtra("send-total",total);

        startActivityForResult(intent,MY_REQUEST_CODE);
        /* *Start Activity Two !!!!* */}
    public void oatmeal(View view){
        Intent intent = new Intent(StoreActivity.this,ItemActivity.class);
        itemName = "Fruit Maple Oatmeal";
        itemPrice = 2.00;
        intent.putExtra("send-itemName",itemName);
        intent.putExtra("send-itemPrice",itemPrice);
        intent.putExtra("send-name", items);
        intent.putExtra("send-price", price);
        intent.putExtra("send-quant", quant);
        intent.putExtra("send-total",total);

        startActivityForResult(intent,MY_REQUEST_CODE);
        /* *Start Activity Two !!!!* */}
    public void baconeggcheese(View view){
        Intent intent = new Intent(StoreActivity.this,ItemActivity.class);
        itemName = "Bacon Egg Biscuit";
        itemPrice = 2.00;
        intent.putExtra("send-itemName",itemName);
        intent.putExtra("send-itemPrice",itemPrice);
        intent.putExtra("send-name", items);
        intent.putExtra("send-price", price);
        intent.putExtra("send-quant", quant);
        intent.putExtra("send-total",total);

        startActivityForResult(intent,MY_REQUEST_CODE);
        /* *Start Activity Two !!!!* */
    }
    public void hotcakes(View view){
        Intent intent = new Intent(StoreActivity.this,ItemActivity.class);
        itemName = "Hotcakes";
        itemPrice = 2.00;

        intent.putExtra("send-itemName",itemName);
        intent.putExtra("send-itemPrice",itemPrice);
        intent.putExtra("send-name", items);
        intent.putExtra("send-price", price);
        intent.putExtra("send-quant", quant);
        intent.putExtra("send-total",total);

        startActivityForResult(intent,MY_REQUEST_CODE);

    }
    public void eggSasuage(View view){
        Intent intent = new Intent(StoreActivity.this,ItemActivity.class);
        itemName = "Egg Sausage Biscuit";
        itemPrice = 2.00;

        intent.putExtra("send-itemName",itemName);
        intent.putExtra("send-itemPrice",itemPrice);
        intent.putExtra("send-name", items);
        intent.putExtra("send-price", price);
        intent.putExtra("send-quant", quant);
        intent.putExtra("send-total",total);

        startActivityForResult(intent,MY_REQUEST_CODE);

    }
    public void sasuageBiscuit(View view){
        Intent intent = new Intent(StoreActivity.this,ItemActivity.class);
        itemName = "Sausage Biscuit";
        itemPrice = 1.99;

        intent.putExtra("send-itemName",itemName);
        intent.putExtra("send-itemPrice",itemPrice);
        intent.putExtra("send-name", items);
        intent.putExtra("send-price", price);
        intent.putExtra("send-quant", quant);
        intent.putExtra("send-total",total);

        startActivityForResult(intent,MY_REQUEST_CODE);

    }
    public void sausageburrito(View view){
        Intent intent = new Intent(StoreActivity.this,ItemActivity.class);
        itemName = "Sausage Burrito";
        itemPrice =1.75;
        intent.putExtra("send-itemName",itemName);
        intent.putExtra("send-itemPrice",itemPrice);
        intent.putExtra("send-name", items);
        intent.putExtra("send-price", price);
        intent.putExtra("send-quant", quant);
        intent.putExtra("send-total",total);

        startActivityForResult(intent,MY_REQUEST_CODE);

    }
    public void process(View view){
        Intent intent = new Intent(StoreActivity.this,TransactionActivity.class);
        intent.putExtra("send-name", items);
        intent.putExtra("send-price", price);
        intent.putExtra("send-quant", quant);
        intent.putExtra("send-total",total);
        Toast.makeText(this,"Checking out.",Toast.LENGTH_SHORT).show();

        startActivityForResult(intent,MY_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                String names;
                int    quantity;
                Double prices;
                names = data.getExtras().getString("back-name");
                quantity = data.getExtras().getInt("back-quant");
                prices   = data.getExtras().getDouble("back-price");

                if (quantity > 0) {
                    items.add(names);
                    quant.add(quantity);
                    price.add(prices);
                    Toast.makeText(this,names + " Added",Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(this,names + " was not added",Toast.LENGTH_SHORT).show();




            }
        }
    }
}
