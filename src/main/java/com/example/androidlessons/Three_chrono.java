package com.example.androidlessons;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.CompoundButton;
//import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Locale;

public class Three_chrono extends AppCompatActivity {

    private int seconds4 = 0;
    private Boolean running4 = false;
    private String interval_str4;
    private String intervals4 = "Круг" + " " + "Время круга" + " " + "Общ время" + "\n";
    private int numberOfInterval4 = 0;
    private int secondsInterval4;
    private int timeOfInterval4 = 0;
    private int prevTotalTime4 = 0;

    private int seconds5 = 0;
    private Boolean running5 = false;
    private String interval_str5;
    private String intervals5 = "Круг" + " " + "Время круга" + " " + "Общ время" + "\n";
    private int numberOfInterval5 = 0;
    private int secondsInterval5;
    private int timeOfInterval5 = 0;
    private int prevTotalTime5 = 0;

    private int seconds6 = 0;
    private Boolean running6 = false;
    private String interval_str6;
    private String intervals6 = "Круг" + " " + "Время круга" + " " + "Общ время" + "\n";
    private int numberOfInterval6 = 0;
    private int secondsInterval6;
    private int timeOfInterval6 = 0;
    private int prevTotalTime6 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_chrono);

        Toolbar toolbar3 = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar3);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState != null) {
            seconds4 = savedInstanceState.getInt("seconds4");
            running4 = savedInstanceState.getBoolean("running4");
            intervals4 = savedInstanceState.getString("intervals4");
            numberOfInterval4 = savedInstanceState.getInt("numberOfInterval4");
            secondsInterval4 = savedInstanceState.getInt("secondsInterval4");
            timeOfInterval4 = savedInstanceState.getInt("timeOfInterval4");
            prevTotalTime4 = savedInstanceState.getInt("prevTotalTime4");
            TextView for_interval4 = (TextView) findViewById(R.id.for_interval4);
            for_interval4.setText(intervals4);
            for_interval4.setMovementMethod(new ScrollingMovementMethod());

            seconds5 = savedInstanceState.getInt("seconds5");
            running5 = savedInstanceState.getBoolean("running5");
            intervals5 = savedInstanceState.getString("intervals5");
            numberOfInterval5 = savedInstanceState.getInt("numberOfInterval5");
            secondsInterval5 = savedInstanceState.getInt("secondsInterval5");
            timeOfInterval5 = savedInstanceState.getInt("timeOfInterval5");
            prevTotalTime5 = savedInstanceState.getInt("prevTotalTime5");
            TextView for_interval5 = (TextView) findViewById(R.id.for_interval5);
            for_interval5.setText(intervals5);
            for_interval5.setMovementMethod(new ScrollingMovementMethod());

            seconds6 = savedInstanceState.getInt("seconds6");
            running6 = savedInstanceState.getBoolean("running6");
            intervals6 = savedInstanceState.getString("intervals6");
            numberOfInterval6 = savedInstanceState.getInt("numberOfInterval6");
            secondsInterval6 = savedInstanceState.getInt("secondsInterval6");
            timeOfInterval6 = savedInstanceState.getInt("timeOfInterval6");
            prevTotalTime6 = savedInstanceState.getInt("prevTotalTime6");
            TextView for_interval6 = (TextView) findViewById(R.id.for_interval6);
            for_interval6.setText(intervals6);
            for_interval6.setMovementMethod(new ScrollingMovementMethod());
        }

        runTimer4();
        runTimer5();
        runTimer6();

        //слушатель для кнопки №4
        ToggleButton toggleButton4 = (ToggleButton) findViewById(R.id.toggleButton4);
        CompoundButton.OnCheckedChangeListener toogleListen4 = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    running4 = true;
                } else {
                    running4 = false;
                }
            }
        };
        toggleButton4.setOnCheckedChangeListener(toogleListen4);

        //слушатель для кнопки №5
        ToggleButton toggleButton5 = (ToggleButton) findViewById(R.id.toggleButton5);
        CompoundButton.OnCheckedChangeListener toogleListen5 = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    running5 = true;
                } else {
                    running5 = false;
                }
            }
        };
        toggleButton5.setOnCheckedChangeListener(toogleListen5);

        //слушатель для кнопки №6
        ToggleButton toggleButton6 = (ToggleButton) findViewById(R.id.toggleButton6);
        CompoundButton.OnCheckedChangeListener toogleListen6 = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    running6 = true;
                } else {
                    running6 = false;
                }
            }
        };
        toggleButton6.setOnCheckedChangeListener(toogleListen6);
    }


    // реализация секундомера №4
    public void runTimer4 () {

        final TextView textView4 = (TextView) findViewById(R.id.textView4);
        final Handler handler4 = new Handler();

        handler4.post(new Runnable() {
            @Override
            public void run() {
                int hours4 = seconds4/3600;
                int minutes4 = (seconds4%3600)/60;
                int secs4 = seconds4%60;
                String time4 = String.format(Locale.getDefault(), "%d:%02d: %02d", hours4, minutes4, secs4);
                textView4.setText(time4);
                interval_str4 = time4;
                secondsInterval4 = seconds4;
                if (running4) {
                    seconds4++;
                }
                handler4.postDelayed(this, 1000);
            }
        });
    }

    // реализация секундомера №5
    public void runTimer5 () {

        final TextView textView5 = (TextView) findViewById(R.id.textView5);
        final Handler handler5 = new Handler();

        handler5.post(new Runnable() {
            @Override
            public void run() {
                int hours5 = seconds5/3600;
                int minutes5 = (seconds5%3600)/60;
                int secs5 = seconds5%60;
                String time5 = String.format(Locale.getDefault(), "%d:%02d: %02d", hours5, minutes5, secs5);
                textView5.setText(time5);
                interval_str5 = time5;
                secondsInterval5 = seconds5;
                if (running5) {
                    seconds5++;
                }
                handler5.postDelayed(this, 1000);
            }
        });
    }

    // реализация секундомера №6
    public void runTimer6 () {

        final TextView textView6 = (TextView) findViewById(R.id.textView6);
        final Handler handler6 = new Handler();

        handler6.post(new Runnable() {
            @Override
            public void run() {
                int hours6 = seconds6/3600;
                int minutes6 = (seconds6%3600)/60;
                int secs6 = seconds6%60;
                String time6 = String.format(Locale.getDefault(), "%d:%02d: %02d", hours6, minutes6, secs6);
                textView6.setText(time6);
                interval_str6 = time6;
                secondsInterval6 = seconds6;
                if (running6) {
                    seconds6++;
                }
                //String seconds_str = toString(seconds);
                handler6.postDelayed(this, 1000);
            }
        });
    }

    // отсчет круга для секундомера №4
    public void make_interval4 (View view) {
        numberOfInterval4++;
        TextView for_interval4 = (TextView) findViewById(R.id.for_interval4);
        timeOfInterval4 =  secondsInterval4 - prevTotalTime4;
        prevTotalTime4 = secondsInterval4;

        int hours4 = timeOfInterval4/3600;
        int minutes4 = (timeOfInterval4%3600)/60;
        int secs4 = timeOfInterval4%60;
        String time4 = String.format(Locale.getDefault(), "%d:%02d: %02d", hours4, minutes4, secs4);

        intervals4 =  intervals4 + numberOfInterval4 + "    " + time4 + "  " + interval_str4 + "\n";
        for_interval4.setText(intervals4);
        for_interval4.setMovementMethod(new ScrollingMovementMethod());
    }

    // отсчет круга для секундомера №5
    public void make_interval5 (View view) {
        numberOfInterval5++;
        TextView for_interval5 = (TextView) findViewById(R.id.for_interval5);
        timeOfInterval5 =  secondsInterval5 - prevTotalTime5;
        prevTotalTime5 = secondsInterval5;

        int hours5 = timeOfInterval5/3600;
        int minutes5 = (timeOfInterval5%3600)/60;
        int secs5 = timeOfInterval5%60;
        String time5 = String.format(Locale.getDefault(), "%d:%02d: %02d", hours5, minutes5, secs5);

        intervals5 =  intervals5 + numberOfInterval5 + "    " + time5 + "   " + interval_str5 + "\n";
        for_interval5.setText(intervals5);
        for_interval5.setMovementMethod(new ScrollingMovementMethod());
    }

    // отсчет круга для секундомера №6
    public void make_interval6 (View view) {
        numberOfInterval6++;
        TextView for_interval6 = (TextView) findViewById(R.id.for_interval6);
        timeOfInterval6 =  secondsInterval6 - prevTotalTime6;
        prevTotalTime6 = secondsInterval6;

        int hours6 = timeOfInterval6/3600;
        int minutes6 = (timeOfInterval6%3600)/60;
        int secs6 = timeOfInterval6%60;
        String time6 = String.format(Locale.getDefault(), "%d:%02d: %02d", hours6, minutes6, secs6);

        intervals6 =  intervals6 + numberOfInterval6 + "    " + time6 + "   " + interval_str6 + "\n";
        for_interval6.setText(intervals6);
        for_interval6.setMovementMethod(new ScrollingMovementMethod());
    }

    // сброс секундомера №4
    public void resetChrono4 (View view) {
        running4 = false;
        seconds4 = 0;
        intervals4 = "Круг" + " " + "Время круга" + " " + "Общее время" + "\n";
        numberOfInterval4 = 0;
        timeOfInterval4 = 0;
        prevTotalTime4 = 0;
        TextView for_interval4 = (TextView) findViewById(R.id.for_interval4);
        for_interval4.setText(null);
    }

    // сброс секундомера №5
    public void resetChrono5 (View view) {
        running5 = false;
        seconds5 = 0;
        intervals5 = "Круг" + " " + "Время круга" + " " + "Общее время" + "\n";
        numberOfInterval5 = 0;
        timeOfInterval5 = 0;
        prevTotalTime5 = 0;
        TextView for_interval5 = (TextView) findViewById(R.id.for_interval5);
        for_interval5.setText(null);
    }

    // сброс секундомера №6
    public void resetChrono6 (View view) {
        running6 = false;
        seconds6 = 0;
        intervals6 = "Круг" + " " + "Время круга" + " " + "Общее время" + "\n";
        numberOfInterval6 = 0;
        timeOfInterval6 = 0;
        prevTotalTime6 = 0;
        TextView for_interval6 = (TextView) findViewById(R.id.for_interval6);
        for_interval6.setText(null);
    }

    //сохраняем состояние активности
    public void onSaveInstanceState (Bundle SaveInstanceState) {
        super.onSaveInstanceState(SaveInstanceState);
        SaveInstanceState.putInt("seconds4", seconds4);
        SaveInstanceState.putBoolean("running4", running4);
        SaveInstanceState.putString("intervals4", intervals4);
        SaveInstanceState.putInt("numberOfInterval4", numberOfInterval4);
        SaveInstanceState.putInt("secondsInterval4", secondsInterval4);
        SaveInstanceState.putInt("timeOfInterval4", timeOfInterval4);
        SaveInstanceState.putInt("prevTotalTime4", prevTotalTime4);

        SaveInstanceState.putInt("seconds5", seconds5);
        SaveInstanceState.putBoolean("running5", running5);
        SaveInstanceState.putString("intervals5", intervals5);
        SaveInstanceState.putInt("numberOfInterval5", numberOfInterval5);
        SaveInstanceState.putInt("secondsInterval5", secondsInterval5);
        SaveInstanceState.putInt("timeOfInterval5", timeOfInterval5);
        SaveInstanceState.putInt("prevTotalTime5", prevTotalTime5);

        SaveInstanceState.putInt("seconds6", seconds6);
        SaveInstanceState.putBoolean("running6", running6);
        SaveInstanceState.putString("intervals6", intervals6);
        SaveInstanceState.putInt("numberOfInterval6", numberOfInterval6);
        SaveInstanceState.putInt("secondsInterval6", secondsInterval6);
        SaveInstanceState.putInt("timeOfInterval6", timeOfInterval6);
        SaveInstanceState.putInt("prevTotalTime6", prevTotalTime6);
    }

}