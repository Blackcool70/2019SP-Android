package com.example.wtstudentsay1005064.loancalculator;

import android.content.Intent;
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

public class LoanTableActivity extends AppCompatActivity {

    double loan,apr,monthlyPayment,cumulativeInterest=0;
    int    months;

    double howMuchGoesTowardInterest,leftOver,RemainingBalance;

    TextView inLoan,inMonths;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        Bundle extras = getIntent().getExtras();

        loan              = extras.getDouble("send-loan", 0);
        months            = extras.getInt("send-payments",0);
        apr               = extras.getDouble("send-apr",0);
        monthlyPayment    = extras.getDouble("send-payment",0);



        /***************Wiring***************************/
        inLoan = findViewById(R.id.loan_amt);
        inMonths = findViewById(R.id.payments);
        /************************************************/

        inLoan.setText("$" + (String.format("%,.2f", loan)));
        inMonths.setText((String.format("%d", months)));


        TextView tv1;
        TableLayout table = findViewById(R.id.TableLayout);
        TableRow row;

        RemainingBalance = loan;
        for(int i = 0; i < months; i++){
            row = new TableRow(this);

            tv1 = new TextView(this);

            howMuchGoesTowardInterest = RemainingBalance*(apr/100)/12;
            leftOver = monthlyPayment - howMuchGoesTowardInterest;
            RemainingBalance = RemainingBalance - (monthlyPayment - howMuchGoesTowardInterest);

            String printout = (i+1 + "  $"+(String.format("%,.2f", monthlyPayment)+"\t")
                    + "   $"+(String.format("%,.2f", howMuchGoesTowardInterest))
                    + "   $"+(String.format("%,.2f", leftOver))
                    + "   $"+(String.format("%,.2f\n", RemainingBalance)));


            if(RemainingBalance <= 0.00){
                 printout = (i+1 + "  $"+(String.format("%,.2f", monthlyPayment))
                        + "   $"+(String.format("%,.2f", howMuchGoesTowardInterest))
                        + "   $"+(String.format("%,.2f", leftOver))
                        + "   $0.00");
            }

            tv1.setText(printout);
            tv1.setTypeface(Typeface.DEFAULT_BOLD);


            tv1.setPadding(20,0,30,0);
            row.addView(tv1);
            table.addView(row);

            cumulativeInterest += howMuchGoesTowardInterest;
        }


    }

    @Override
    public void finish() {

        Intent i = new Intent();
        i.putExtra("interest", cumulativeInterest);
        setResult(RESULT_OK, i);
        super.finish();
    }

}
