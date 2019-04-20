package com.example.wtstudentsay1005064.converttemperature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
/*
*
* This program converts C to F and reverse.
* Author: Atef Yassine
* Version: 02/27/2019
* */
public class MainActivity extends AppCompatActivity {

    EditText inC,inF;

    Button reset,clickC,clickF;

    double C,F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /***************Wiring***************************/
        inC = findViewById(R.id.cel);
        inF  = findViewById(R.id.fah);

        clickC   = findViewById(R.id.C);
        clickF   = findViewById(R.id.F);
        reset    = findViewById(R.id.reset);

        /************************************************/
        clickC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                F = Double.parseDouble(inF.getText().toString());
                C = (F-32.0) * 5.0/9.0;
                inC.setText((String.format("%,.2f", C)));

            }
        });

        clickF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                C = Double.parseDouble(inC.getText().toString());
                F = C * 9.0/5.0 + 32.0;
                inF.setText((String.format("%,.2f", F)));

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inC.setText("");
                inF.setText("");
            }
        });

    }

}