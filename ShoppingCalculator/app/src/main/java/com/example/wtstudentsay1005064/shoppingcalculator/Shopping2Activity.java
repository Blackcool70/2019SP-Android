package com.example.wtstudentsay1005064.shoppingcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

//Ibsa Ahmed's part

public class Shopping2Activity extends AppCompatActivity {

    EditText inPrice, inQuantity, inName, count_1;


    int count;
    String name="";
    Double amount=0.00;
    int quantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping2);

        //fields
        inName = findViewById(R.id.name);
        inName.setText("");
        inPrice = findViewById(R.id.price);
        inPrice.setText("");
        inQuantity = findViewById(R.id.quantity);
        inQuantity.setText("");
        count_1 = findViewById(R.id.number_of_items);

        Bundle extras = getIntent().getExtras();
        count = extras.getInt("send-count", 0);

        count_1.setText(String.format("%d", count));
    }

    @Override
    public void finish() {

        try {
            name = inName.getText().toString();
            amount = Double.parseDouble(inPrice.getText().toString());
            quantity = Integer.parseInt(inQuantity.getText().toString());
        }
        catch(Exception e){
            name = "";
            amount = 0.00;
            quantity =0;
       }
            Intent i = new Intent();
            i.putExtra("name", name);
            i.putExtra("amount", amount);
            i.putExtra("quantity", quantity);
            setResult(RESULT_OK, i);
            super.finish();
    }
}
