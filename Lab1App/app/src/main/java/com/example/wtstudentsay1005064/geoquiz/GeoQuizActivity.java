// Name: Atef Yassine
// Date: 01/28/2019
// Course: Android
// Description: A little program to simulate a true/false game based on questions.

package com.example.wtstudentsay1005064.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GeoQuizActivity extends AppCompatActivity {

    TextView questionLabel;
    Button yes,no,next,prev;
    Question [] questionBank;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_quiz);

        questionLabel = findViewById(R.id.q_label);
        no =   findViewById(R.id.False_Button);
        yes =  findViewById(R.id.True_Button);
        next = findViewById(R.id.Next_Button);
        prev = findViewById(R.id.Previous_Button);

        i = 0;
        questionBank = new Question[5];
        questionBank[0] = new Question();
        questionBank[0].setStatement("1. Mexico City is the capital of Mexico");
        questionBank[0].setAnswer(true);
        questionBank[1] = new Question();
        questionBank[1].setStatement("2. Toronto is the capital of Canada");
        questionBank[1].setAnswer(false);
        questionBank[2] = new Question();
        questionBank[2].setStatement("3. London is the capital of United Kingdom");
        questionBank[2].setAnswer(true);
        questionBank[3] = new Question();
        questionBank[3].setStatement("4. Milan is the capital of Italy");
        questionBank[3].setAnswer(false);
        questionBank[4] = new Question();
        questionBank[4].setStatement("5. Jerusalem is the capital of Palestine");
        questionBank[4].setAnswer(true);

        // set to display the first question
        questionLabel.setText(questionBank[i].getStatement());

        //place click listener method on the buttons
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify(false);
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify(true);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    i++;
                    questionLabel.setText(questionBank[i].getStatement());
                }
                catch (Exception e){
                    i = 0;
                    questionLabel.setText(questionBank[i].getStatement());
                }
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    i--;
                    questionLabel.setText(questionBank[i].getStatement());
                }
                catch (Exception e){
                    i = 4;
                    questionLabel.setText(questionBank[i].getStatement());
                }
            }
        });


    }

    // the method will verify if the boolean parameter is correct or not
    // for the given statement and accordingly display a toast message.
    private void verify (boolean b){
        if (questionBank[i].getAnswer() == b)
            Toast.makeText(GeoQuizActivity.this, "Correct answer", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(GeoQuizActivity.this, "Sorry your answer is incorrect", Toast.LENGTH_SHORT).show();
    }

}
