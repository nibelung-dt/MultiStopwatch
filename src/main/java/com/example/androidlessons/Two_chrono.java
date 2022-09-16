package com.example.androidlessons;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.content.Intent;
import android.widget.CompoundButton.OnCheckedChangeListener;
import java.util.Locale;

public class Two_chrono extends AppCompatActivity {

    private int seconds2 = 0;
    private Boolean running2 = false;
    private String interval_str2;
    private String intervals2 = "Круг" + " " + "Время круга" + " " + "Общ время" + "\n";
    private int numberOfInterval2 = 0;
    private int secondsInterval2;
    private int timeOfInterval2 = 0;
    private int prevTotalTime2 = 0;

    private int seconds3 = 0;
    private Boolean running3 = false;
    private String interval_str3;
    private String intervals3 = "Круг" + " " + "Время круга" + " " + "Общ время" + "\n";
    private int numberOfInterval3 = 0;
    private int secondsInterval3;
    private int timeOfInterval3 = 0;
    private int prevTotalTime3 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_chrono);

        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState != null) {
            seconds2 = savedInstanceState.getInt("seconds2");
            running2 = savedInstanceState.getBoolean("running2");
            intervals2 = savedInstanceState.getString("intervals2");
            numberOfInterval2 = savedInstanceState.getInt("numberOfInterval2");
            secondsInterval2 = savedInstanceState.getInt("secondsInterval2");
            timeOfInterval2 = savedInstanceState.getInt("timeOfInterval2");
            prevTotalTime2 = savedInstanceState.getInt("prevTotalTime2");
            TextView for_interval2 = (TextView) findViewById(R.id.for_interval2);
            for_interval2.setText(intervals2);
            for_interval2.setMovementMethod(new ScrollingMovementMethod());

            seconds3 = savedInstanceState.getInt("seconds3");
            running3 = savedInstanceState.getBoolean("running3");
            intervals3 = savedInstanceState.getString("intervals3");
            numberOfInterval3 = savedInstanceState.getInt("numberOfInterval3");
            secondsInterval3 = savedInstanceState.getInt("secondsInterval3");
            timeOfInterval3 = savedInstanceState.getInt("timeOfInterval3");
            prevTotalTime3 = savedInstanceState.getInt("prevTotalTime3");
            TextView for_interval3 = (TextView) findViewById(R.id.for_interval3);
            for_interval3.setText(intervals3);
            for_interval3.setMovementMethod(new ScrollingMovementMethod());
        }

        runTimer2();
        runTimer3();

        //слушатель для кнопки №2
        ToggleButton toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
        OnCheckedChangeListener toogleListen2 = new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    running2 = true;
                } else {
                    running2 = false;
                }
            }
        };
        toggleButton2.setOnCheckedChangeListener(toogleListen2);

        //слушатель для кнопки №3
        ToggleButton toggleButton3 = (ToggleButton) findViewById(R.id.toggleButton3);
        OnCheckedChangeListener toogleListen3 = new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    //simpleChronometer.setBase(SystemClock.elapsedRealtime());
                    running3 = true;
                } else {
                    running3 = false;
                }
            }
        };
        toggleButton3.setOnCheckedChangeListener(toogleListen3);
    }


    // реализация секундомера №2
    public void runTimer2 () {

        final TextView textView2 = (TextView) findViewById(R.id.textView2);
        final Handler handler2 = new Handler();

        handler2.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds2/3600;
                int minutes = (seconds2%3600)/60;
                int secs = seconds2%60;
                String time2 = String.format(Locale.getDefault(), "%d:%02d: %02d", hours, minutes, secs);
                textView2.setText(time2);
                interval_str2 = time2;
                secondsInterval2 = seconds2;
                if (running2) {
                    seconds2++;
                }
                handler2.postDelayed(this, 1000);
            }
        });
    }

    // реализация секундомера №3
    public void runTimer3 () {

        final TextView textView3 = (TextView) findViewById(R.id.textView3);
        final Handler handler3 = new Handler();

        handler3.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds3/3600;
                int minutes = (seconds3%3600)/60;
                int secs = seconds3%60;
                String time3 = String.format(Locale.getDefault(), "%d:%02d: %02d", hours, minutes, secs);
                textView3.setText(time3);
                interval_str3 = time3;
                secondsInterval3 = seconds3;
                if (running3) {
                    seconds3++;
                }
                //String seconds_str = toString(seconds);
                handler3.postDelayed(this, 1000);
            }
        });
    }

    // отсчет круга
    public void make_interval2 (View view) {
        numberOfInterval2++;
        TextView for_interval2 = (TextView) findViewById(R.id.for_interval2);
        timeOfInterval2 =  secondsInterval2 - prevTotalTime2;
        prevTotalTime2 = secondsInterval2;

        int hours2 = timeOfInterval2/3600;
        int minutes2 = (timeOfInterval2%3600)/60;
        int secs2 = timeOfInterval2%60;
        String time2 = String.format(Locale.getDefault(), "%d:%02d: %02d", hours2, minutes2, secs2);

        intervals2 =  intervals2 + numberOfInterval2 + "    " + time2 + "  " + interval_str2 + "\n";
        for_interval2.setText(intervals2);
        for_interval2.setMovementMethod(new ScrollingMovementMethod());
    }

    public void make_interval3 (View view) {
        numberOfInterval3++;
        TextView for_interval3 = (TextView) findViewById(R.id.for_interval3);
        timeOfInterval3 =  secondsInterval3 - prevTotalTime3;
        prevTotalTime3 = secondsInterval3;

        int hours3 = timeOfInterval3/3600;
        int minutes3 = (timeOfInterval3%3600)/60;
        int secs3 = timeOfInterval3%60;
        String time3 = String.format(Locale.getDefault(), "%d:%02d: %02d", hours3, minutes3, secs3);

        intervals3 =  intervals3 + numberOfInterval3 + "    " + time3 + "   " + interval_str3 + "\n";
        for_interval3.setText(intervals3);
        for_interval3.setMovementMethod(new ScrollingMovementMethod());
    }

    public void resetChrono2 (View view) {
        running2 = false;
        seconds2 = 0;
        intervals2 = "Круг" + " " + "Время круга" + " " + "Общее время" + "\n";
        numberOfInterval2 = 0;
        timeOfInterval2 = 0;
        prevTotalTime2 = 0;
        TextView for_interval2 = (TextView) findViewById(R.id.for_interval2);
        for_interval2.setText(null);
    }

    public void resetChrono3 (View view) {
        running3 = false;
        seconds3 = 0;
        intervals3 = "Круг" + " " + "Время круга" + " " + "Общее время" + "\n";
        numberOfInterval3 = 0;
        timeOfInterval3 = 0;
        prevTotalTime3 = 0;
        TextView for_interval3 = (TextView) findViewById(R.id.for_interval3);
        for_interval3.setText(null);
    }

    public void onSaveInstanceState (Bundle SaveInstanceState) {
        super.onSaveInstanceState(SaveInstanceState);
        SaveInstanceState.putInt("seconds2", seconds2);
        SaveInstanceState.putBoolean("running2", running2);
        SaveInstanceState.putString("intervals2", intervals2);
        SaveInstanceState.putInt("numberOfInterval2", numberOfInterval2);
        SaveInstanceState.putInt("secondsInterval2", secondsInterval2);
        SaveInstanceState.putInt("timeOfInterval2", timeOfInterval2);
        SaveInstanceState.putInt("prevTotalTime2", prevTotalTime2);

        SaveInstanceState.putInt("seconds3", seconds3);
        SaveInstanceState.putBoolean("running3", running3);
        SaveInstanceState.putString("intervals3", intervals3);
        SaveInstanceState.putInt("numberOfInterval3", numberOfInterval3);
        SaveInstanceState.putInt("secondsInterval3", secondsInterval3);
        SaveInstanceState.putInt("timeOfInterval3", timeOfInterval3);
        SaveInstanceState.putInt("prevTotalTime3", prevTotalTime3);
    }

}