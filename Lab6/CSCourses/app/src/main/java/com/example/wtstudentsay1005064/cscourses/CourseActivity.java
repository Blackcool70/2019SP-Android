package com.example.wtstudentsay1005064.cscourses;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class CourseActivity extends FragmentActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }
        if(savedInstanceState == null){
            CourseDetails details = new CourseDetails();
            details.setArguments(getIntent().getExtras());

            /*
                getSupportFragmentManager() works with the super Class FragmentActivity.

             */
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
        }

    }

}
