package com.example.wtstudentsay1005064.djia;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/*
* Author: Atef Yassine
* Date  : 03/04/2019
*
* Description:  This program takes in stock symbols, adds up the previous day close value
 *              then dividing it by a specific constant.
 *
 * What I've learnt: I have learnt how to create a progress bar and manipulate it.
 *                   I also learned how to start a timer and end it to compute how long
 *                   a computation took to complete.
 *
 *                   */
public class MainActivity extends AppCompatActivity {

    TextView price,firstText,secondText,thirdText,fourthText, number,number2,percent,progress_num;
    ProgressBar progress;
    Double total = 0.0;
    Double AVG    = 0.0;
    int   counter = 0;
    long startTime;
    Double price_p;
    Double Percent=0.0;


    String arr[] = {"MMM","AXP","AAPL","BA","CAT","CVX","CSCO","KO", "DIS", "DWDP", "XOM","GS", "HD", "IBM",
            "INTC", "JNJ", "JPM", "MCD", "MRK", "MSFT", "NKE", "PFE", "PG", "TRV","UTX", "UNH", "VZ", "V",
            "WMT", "WBA"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        price   = findViewById(R.id.textView);

        percent = findViewById(R.id.percent);

        firstText   = findViewById(R.id.firstText);
        secondText   = findViewById(R.id.secondText);
        thirdText   = findViewById(R.id.thirdText);
        fourthText   = findViewById(R.id.fourthText);

        number     = findViewById(R.id.number);
        number2    = findViewById(R.id.number2);
        progress = findViewById(R.id.progress_horizontal);
        progress_num = findViewById(R.id.progress);



        progress.setMax(30);
        progress.setVisibility(View.INVISIBLE);
        thirdText.setVisibility(View.INVISIBLE);
    }

    /*An alternative to setting a click listener on the button using
      the setOnClickListener(View.OnClickerListener) is to
      add an onClick element to the button in the xml file and
      implement a protected void method to tell what action to be taken when the button is clicked.*/
    protected void compute(View view){
        if(counter != 30) {
            String st;
            startTime = System.currentTimeMillis();

            for (int i = 0; i < arr.length; i++) {
                st = "https://query1.finance.yahoo.com/v8/finance/chart/" + arr[i] + "?interval=1m";
                new FindPrice().execute(st);
            }
        }
    }

    private class FindPrice extends AsyncTask<String, Void, String> {
       // String ticker;
        @Override
        protected String doInBackground(String... params) {
            String priceString = "";
            try{
                URL url = new URL(params[0]);
                BufferedReader bufferedReader=
                        new BufferedReader( new InputStreamReader(url.openStream()));
                String line = bufferedReader.readLine();
                String[] ar = line.split("\"previousClose\":");
                String[] sr = ar[1].split(",");
                priceString = sr[0];
            }
            catch(MalformedURLException e){
                Toast.makeText(getApplicationContext(),"URL failed",Toast.LENGTH_SHORT).show();

            }
            catch(IOException e){
                Toast.makeText(getApplicationContext(),"ERROR Reading" +
                        " Data",Toast.LENGTH_SHORT).show();
            }
            return priceString;

        }

        protected void onPreExecute(){
            super.onPreExecute();
        }

        protected void onPostExecute(String s){
            super.onPostExecute(s);

            progress.setVisibility(View.VISIBLE);
            firstText.setVisibility(View.INVISIBLE);
            secondText.setVisibility(View.VISIBLE);
            percent.setVisibility(View.VISIBLE);
            progress_num.setVisibility(View.VISIBLE);

            progress.incrementProgressBy(1);
            price_p = Double.parseDouble(s);

            total = total + price_p;

            counter++;

            Percent = (counter / 30.00) * 100;
            percent.setText(String.format("%,.1f",Percent) + "%.");

            progress_num.setText(String.format("%d",counter) + "/30.");

            if(counter == 30) {
                AVG = total / 0.14748071991788;
                number.setText(String.format("%,.2f",AVG));
                number.setVisibility(View.VISIBLE);
                secondText.setVisibility(View.INVISIBLE);
                thirdText.setVisibility(View.VISIBLE);
                progress.setVisibility(View.INVISIBLE);
                fourthText.setVisibility(View.VISIBLE);
                percent.setVisibility(View.INVISIBLE);
                progress_num.setVisibility(View.INVISIBLE);


                long stopTime       = System.currentTimeMillis();
                long elapsedTime    = stopTime - startTime;
                double Seconds      = elapsedTime / 1000.00;

                number2.setText(String.format("%,.2f",Seconds) + " seconds.");
                number2.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"Computation Complete!",Toast.LENGTH_SHORT).show();


            }

        }

        protected void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }
    }
}
