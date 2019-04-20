package com.example.wtstudentsay1005064.cardvalidator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//Author: atef yassine
//version: 02/20/2019
public class MainActivity extends AppCompatActivity {

    EditText msg, cardNo;
    Button Validate;
    final String TAG1 = "ActivityOne";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fields
        cardNo = findViewById(R.id.card);
        msg = findViewById(R.id.msg);

        //buttons
        Validate = findViewById(R.id.validate);

        //Logic
        Validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardNumber   = cardNo.getText().toString();

                String number  = reverse(cardNumber);


                String lastDigit = number.substring(1);

                int lastDig=0;

                try {

                     lastDig = Integer.parseInt(lastDigit);

                }
                catch (NumberFormatException nfe)
                {
                    nfe.printStackTrace();
                }


                int cardNumber_1=0;

                try {

                    cardNumber_1 = Integer.parseInt(number);

                }
                catch (NumberFormatException nfe)
                {
                    nfe.printStackTrace();
                }


                char[] c = ("" + cardNumber_1).toCharArray();
                int[] digits = new int[c.length];
                for (int i = 0 ; i < digits.length ; i++)
                    digits[i] = c[i] - '0';

                for(int j = 0 ; j < digits.length; j+=2) {

                    digits[j] *= 2;

                    if(digits[j] > 9){
                        digits[j] -= 9;
                    }



                }
                int Sum = 0;
                for(int k = 0; k < digits.length; k++) {
                    Sum += digits[k];
                }

                int total = Sum + lastDig;


                if(total % 10 == 0){
                    msg.setVisibility(View.VISIBLE);
                    msg.setText("Valid Number");
                }
                else {
                    msg.setVisibility(View.VISIBLE);
                    msg.setText("Invalid Number");
                }
            }
        });


    }

        public static String reverse(String a) {
            int j = a.length();
            char[] newWord = new char[j];
            for (int i = 0; i < a.length(); i++) {
                newWord[--j] = a.charAt(i);
            }
            return new String(newWord);
        }
}
