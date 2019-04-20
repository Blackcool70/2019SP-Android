package com.example.wtstudentsay1005064.cscourses;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class CourseDetails extends Fragment {

    public static CourseDetails newInstance(int index){

        CourseDetails courseDetails = new CourseDetails();

        Bundle args = new Bundle();
        args.putInt("index",index);

        courseDetails.setArguments(args);
        return courseDetails;


    }

    public int getShownIndex(){
        return getArguments().getInt("index",0);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        ScrollView scrollView = new ScrollView(getActivity());
        TextView textView = new TextView(getActivity());
        textView.setTextSize(15);

        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getActivity().getResources().getDisplayMetrics());
        textView.setPadding(padding,padding,padding,padding);

        scrollView.addView(textView);

       // Spannable str = new SpannableStringBuilder(Courses.COURSE_INFO[getShownIndex()]);

        //str.setSpan(new BackgroundColorSpan(getResources().getColor(android.R.color.holo_blue_dark)),0,str.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(Courses.COURSE_INFO[getShownIndex()]);
        textView.setBackgroundColor(Color.rgb(150, 150, 150));
        textView.setPadding(0,0,0,0);
        //textView.setHighlightColor(Color.rgb(0, 0, 255));
       // textView.color(Color.rgb(0,0,255));
        textView.setTextColor(Color.rgb(255, 255, 255));
        //textView.setPadding(5,5,5,5);
        return scrollView;
    }

}
