package com.example.androidlessons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.os.Handler;
import java.util.Locale;
import android.view.Menu;


public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private Boolean running = false;
    private String interval_str;
    private String intervals = "Круг" + " " + "Время круга" + " " + "Общее время" + "\n";
    private int numberOfInterval = 0;
    private int secondsInterval;
    private int timeOfInterval = 0;
    private int prevTotalTime = 0;
    private String textOfStart1 = "Старт";
    private String textOfIntervalReset1 = "Интервал";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            intervals = savedInstanceState.getString("intervals");
            numberOfInterval = savedInstanceState.getInt("numberOfInterval");
            secondsInterval = savedInstanceState.getInt("secondsInterval");
            timeOfInterval = savedInstanceState.getInt("timeOfInterval");
            prevTotalTime = savedInstanceState.getInt("prevTotalTime");
            TextView for_interval = (TextView) findViewById(R.id.for_interval);
            for_interval.setText(intervals);
            for_interval.setMovementMethod(new ScrollingMovementMethod());

            textOfStart1 = savedInstanceState.getString("textOfStart1");
            Button start1 = (Button) findViewById(R.id.start1);
            start1.setText(textOfStart1);

            textOfIntervalReset1 = savedInstanceState.getString("textOfIntervalReset1");
            Button interval_reset = (Button) findViewById(R.id.interval_reset);
            interval_reset.setText(textOfIntervalReset1);
        }

       runTimer();
/*
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        OnCheckedChangeListener toogleListen = new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    running = true;
                } else {
                    running = false;
                }
            }
        };
        toggleButton.setOnCheckedChangeListener(toogleListen);
*/
//передаем параметры в внешний метод и запускаем его
        /*
        RunTimer xyz = new RunTimer();
        xyz.seconds = seconds;
        xyz.running = running;
        xyz.interval_str = interval_str;
        xyz.secondsInterval=secondsInterval;
        xyz.x = R.id.textView;
        xyz.runTimerMethod(); // запуск метода   */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // получим идентификатор выбранного пункта меню
        switch(item.getItemId()) {
            case R.id.two_stopwatch: {
                Intent intent = new Intent(MainActivity.this, Two_chrono.class);
                startActivity(intent);
                return true;
            } case R.id.three_stopwatch: {
                Intent intent = new Intent(MainActivity.this, Three_chrono.class);
                startActivity(intent);
                return true;
            } case R.id.one_timer: {
                Intent intent = new Intent(MainActivity.this, One_timer.class);
                startActivity(intent);
                return true;
            } default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    public void start1 (View view) {
        Button start1 = (Button) findViewById(R.id.start1);
        Button interval_reset = (Button) findViewById(R.id.interval_reset);
        if (running==false) {
            running = true;
            start1.setText("Пауза");
            textOfStart1 = "Пауза";
            interval_reset.setText("Интервал");
            textOfIntervalReset1 = "Интервал";

            interval_reset.setClickable(true);
        } else {
            running = false;
            start1.setText("Старт");
            textOfStart1 = "Старт";
            interval_reset.setText("Сброс");
            textOfIntervalReset1 = "Сброс";
        }
    }

    public void makeIntervalOrReset1 (View view) {
        Button interval_reset = (Button) findViewById(R.id.interval_reset);
        if (running==true) {
            interval_reset.setText("Интервал");
            textOfIntervalReset1 = "Интервал";

            numberOfInterval++;
            TextView for_interval = (TextView) findViewById(R.id.for_interval);
            timeOfInterval =  secondsInterval - prevTotalTime;
            prevTotalTime = secondsInterval;

            int hours2 = timeOfInterval/3600;
            int minutes2 = (timeOfInterval%3600)/60;
            int secs2 = timeOfInterval%60;
            String time2 = String.format(Locale.getDefault(), "%d:%02d: %02d", hours2, minutes2, secs2);

            intervals =  intervals + numberOfInterval + "         " + time2 + "       " + interval_str + "\n";
            for_interval.setText(intervals);
            for_interval.setMovementMethod(new ScrollingMovementMethod());

        } else {
            interval_reset.setText("Сброс");
            interval_reset.setClickable(false);
            textOfIntervalReset1 = "Сброс";

            running = false;
            seconds = 0;
            intervals = "Круг" + " " + "Время круга" + " " + "Общее время" + "\n";
            numberOfInterval = 0;
            timeOfInterval = 0;
            prevTotalTime = 0;
            TextView for_interval = (TextView) findViewById(R.id.for_interval);
            for_interval.setText(null);
        }
    }

    // реализация секундомера
    public void runTimer () {
        final TextView textView = (TextView) findViewById(R.id.textView);
       final Handler handler = new Handler();
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

    // метод для создания интервалов
    /*
    public void make_interval (View view) {
        numberOfInterval++;
        TextView for_interval = (TextView) findViewById(R.id.for_interval);
        timeOfInterval =  secondsInterval - prevTotalTime;
        prevTotalTime = secondsInterval;

        int hours2 = timeOfInterval/3600;
        int minutes2 = (timeOfInterval%3600)/60;
        int secs2 = timeOfInterval%60;
        String time2 = String.format(Locale.getDefault(), "%d:%02d: %02d", hours2, minutes2, secs2);

        intervals =  intervals + numberOfInterval + "         " + time2 + "       " + interval_str + "\n";
        for_interval.setText(intervals);
        for_interval.setMovementMethod(new ScrollingMovementMethod());
    }


    public void resetChrono (View view) {
        running = false;
        seconds = 0;
        intervals = "Круг" + " " + "Время круга" + " " + "Общее время" + "\n";
        numberOfInterval = 0;
        timeOfInterval = 0;
        prevTotalTime = 0;
        TextView for_interval = (TextView) findViewById(R.id.for_interval);
        for_interval.setText(null);
    }

*/



    //@Override
    public void onSaveInstanceState (Bundle SaveInstanceState) {
        super.onSaveInstanceState(SaveInstanceState);
        SaveInstanceState.putInt("seconds", seconds);
        SaveInstanceState.putBoolean("running", running);
        SaveInstanceState.putString("intervals", intervals);
        SaveInstanceState.putInt("numberOfInterval", numberOfInterval);
        SaveInstanceState.putInt("secondsInterval", secondsInterval);
        SaveInstanceState.putInt("timeOfInterval", timeOfInterval);
        SaveInstanceState.putInt("prevTotalTime", prevTotalTime);
        SaveInstanceState.putString("textOfStart1", textOfStart1);
        SaveInstanceState.putString("textOfIntervalReset1", textOfIntervalReset1);
    }

}