package com.example.androidlessons;

import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
//import android.widget.TextView;
//import android.view.View;

//import androidx.appcompat.app.AppCompatActivity;

public class RunTimer extends AppCompatActivity {

    public int seconds;
    public Boolean running;
    public String interval_str;
    public int secondsInterval;
    //public TextView textView;
   public int x; //  R.id.textView;


    public void runTimerMethod () {

        final Handler handler = new Handler();
        final TextView textView = (TextView) findViewById(x);

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(), "%d:%02d: %02d", hours, minutes, secs);
                textView.setText(time);
                interval_str = time;
                secondsInterval = seconds;
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });

    }


}
