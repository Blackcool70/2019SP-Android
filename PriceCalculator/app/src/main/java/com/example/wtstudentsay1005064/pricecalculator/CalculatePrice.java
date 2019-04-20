package com.example.wtstudentsay1005064.pricecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/*
    This program takes in input from the user and then it adds tax to it and displays the subtotal and total after tax
    Author: Atef Yassine
    Date  : 02/05/2019
 */
public class CalculatePrice extends AppCompatActivity {

    EditText inPrice, inQuantity,outPrice,outTax,outTotal;
    Button findPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calculate_price);

        inPrice = findViewById(R.id.price);
        inQuantity = findViewById(R.id.quantity);
        outPrice = findViewById(R.id.subtotal);
        outTax = findViewById(R.id.tax);
        outTotal = findViewById(R.id.total);
        findPrice = findViewById(R.id.compute);



        findPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double amount = Double.parseDouble(inPrice.getText().toString());
                int quantity = Integer.parseInt(inQuantity.getText().toString());
                double priceNew = amount * quantity;
                double afterTax = amount * quantity + amount*quantity*7.5/100;
                double tax_amt =  amount * 0.075 *quantity;

                String afterTaxString = String.format("%,.2f",afterTax);
                String priceNewString = String.format("%,.2f", priceNew);
                String tax_amtString  = String.format("%,.2f", tax_amt);

                priceNewString = "$" + priceNewString;

                inPrice.setText("$" + (String.format("%,.2f", amount)));

                inQuantity.setText(String.format("%d", quantity));

                outTax.setText("$"+tax_amtString) ;
                outTax.setVisibility(View.VISIBLE);

                outPrice.setText(priceNewString);
                outPrice.setVisibility(View.VISIBLE);

                outTotal.setText(afterTaxString);
                outTotal.setVisibility(View.VISIBLE);
            }
        });

    }
}
