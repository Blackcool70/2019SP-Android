package com.example.wtstudentsay1005064.loancalculator;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoanCalculatorActivity extends AppCompatActivity {

    /*
    * id:
    *       text:
    *       loan
    *       apr
    *       term
    *       payment
    *       interest
    *
    *       buttons:
    *       compute
    *       reset
    *       show_list
    *
    *
    *       This program takes in the loan amount, the APR, and the term. It calculates the monthly
    *       payment, and makes use of activity life cycle to create a AMORTIZATION TABLE.
    *       Author: Atef Yassine
    *       Version: 02/27/2019
    *
    *
    *
    *       */

    EditText inLoan, inAPR, inTerm, outPayment, outInterest;
    TextView outText,outText_2;

    Button   compute,list,reset;

    double loan, apr, payment, rate,interest;
    int    term,months;

    final int MY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /***************Wiring***************************/
        inLoan = findViewById(R.id.loan);
        inAPR  = findViewById(R.id.apr);
        inTerm = findViewById(R.id.term);

        outPayment = findViewById(R.id.payment);
        outInterest = findViewById(R.id.interest);
        outText     = findViewById(R.id.View);
        outText_2   = findViewById(R.id.View_2);

        compute = findViewById(R.id.compute);
        list    = findViewById(R.id.show_list);
        reset   = findViewById(R.id.reset);
        /************************************************/


        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    loan = Double.parseDouble(inLoan.getText().toString());
                    apr = Double.parseDouble(inAPR.getText().toString());
                    term = Integer.parseInt(inTerm.getText().toString());

                    rate = apr/100/12;
                    months = term * 12;

                    payment = rate / ((Math.pow(1+rate,months)) - 1);

                    payment = payment + rate;

                    payment = payment * loan;

                    String payment_out = String.format("$%,.2f",payment);

                    /* Loan Payment Field */
                    outPayment.setText(payment_out);
                    outPayment.setEnabled(true);
                    outText.setEnabled(true);

                    /* All other fields */
                    inLoan.setText("$" + (String.format("%,.2f", loan)));
                    inAPR.setText((String.format("%,.2f", apr)) + "%");
                    inTerm.setText((String.format("%d",term)));


                    /*Enabling buttons*/
                    list.setEnabled(true);
                    reset.setEnabled(true);
                }
                catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Please enter a valid input",Toast.LENGTH_SHORT).show();
                }

            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoanCalculatorActivity.this,LoanTableActivity.class);
                intent.putExtra("send-loan", loan);
                intent.putExtra("send-payments", months);
                intent.putExtra("send-apr",apr);
                intent.putExtra("send-payment", payment);

                startActivityForResult(intent,MY_REQUEST_CODE);
                /* *Start Activity Two !!!!* */

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inLoan.getText().clear();
                inAPR.getText().clear();
                inTerm.getText().clear();
                outPayment.setText("");
                outText.setEnabled(false);
                outPayment.setEnabled(false);
                list.setEnabled(false);
                reset.setEnabled(false);
                outText_2.setEnabled(false);
                outInterest.setEnabled(false);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                interest  = data.getExtras().getDouble("interest");
                outInterest.setText((String.format("$"+"%,.2f",interest)));
                outText_2.setEnabled(true);
                outInterest.setEnabled(true);
            }
        }
    }
}
