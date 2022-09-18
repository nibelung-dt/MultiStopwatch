package com.example.androidlessons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Handler;

import java.util.Locale;
//import java.util.Timer;

public class One_timer extends AppCompatActivity {

    private int seconds7 = 60;
    private Boolean running7 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        runTimerDown();
    }

    public void start_timer(View view) {
        running7 = true;
       // EditText editTextTime = (EditText) findViewById(R.id.editTextTime);
        //String time =  editTextTime.getText().toString();

      //  00:02:35
    }

    // реализация таймера
    public void runTimerDown () {
        final TextView textView7 = (TextView) findViewById(R.id.textView7);
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {

                int hours = seconds7/3600;
                int minutes = (seconds7%3600)/60;
                int secs = seconds7%60;
                String time = String.format(Locale.getDefault(), "%d:%02d: %02d", hours, minutes, secs);
               // String time = Integer.toString(seconds7);
                textView7.setText(time);

                if (running7 && seconds7 != 0) {
                    seconds7--;
                } else {
                    running7 = false;
                    seconds7 = 60;
                }
                handler.postDelayed(this, 1000);
            }
        });

    }

}