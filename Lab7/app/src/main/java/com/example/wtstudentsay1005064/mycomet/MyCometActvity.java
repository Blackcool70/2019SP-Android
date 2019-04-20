package com.example.wtstudentsay1005064.mycomet;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
/*
    Author: Atef Yassine
    Version: 03/27/2019

 */
public class MyCometActvity extends AppCompatActivity {


    /*
     * This app will draw a solid circle on an instance of Canvas. We will use the callback
     * onDraw() to draw the figure, to redraw, we will use the callback invalidate() after a
     * small interval of time.
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comet_actvity);

        //to find the width and length of the UI screen use an instance
        // DisplayMetrics and communicate
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager =
                (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        float width = displayMetrics.widthPixels;
        float height = displayMetrics.heightPixels;
        setContentView(new CometAnimation(this, width, height));

    }
}