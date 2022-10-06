package com.example.androidlessons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Handler;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;

//import com.ikovac.timepickerwithseconds.MyTimePickerDialog;
//import com.ikovac.timepickerwithseconds.TimePicker;

//import java.util.Calendar;
import java.util.Locale;
//import java.util.Timer;

// добавить в Gradle com.kovachcode:timePickerWithSeconds:1.0.1;



public class One_timer extends AppCompatActivity {

    //public EditText editTextTime = (EditText) findViewById(R.id.editTextTime);
    //public EditText editTextTime2 = (EditText) findViewById(R.id.editTextTime2);
    //public EditText editTextTime3 = (EditText) findViewById(R.id.editTextTime3);
    //private TextView textView7 = (TextView) findViewById(R.id.textView7);

    private int seconds7;
    private int seconds7_temp = 0;
    private String time_str = "";
    private Boolean running7 = false;

    private Boolean clickedReset = false;
    private Boolean wasStart = false;
    private Boolean clickedPause = false;

    private String hours = "0";
    private String minutes = "0";
    private String seconds = "0";

    private int times = 1; //нужно для уведомлений

    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {
            seconds7 = savedInstanceState.getInt("seconds7");
            seconds7_temp = savedInstanceState.getInt("seconds7_temp");
            running7 = savedInstanceState.getBoolean("running7");
            time_str = savedInstanceState.getString("time_str");


            TextView textView7 = (TextView) findViewById(R.id.textView7);
            Button enter_time = (Button) findViewById(R.id.enter_time);
            EditText editTextTime = (EditText) findViewById(R.id.editTextTime);
            EditText editTextTime2 = (EditText) findViewById(R.id.editTextTime2);
            EditText editTextTime3 = (EditText) findViewById(R.id.editTextTime3);
            textView7.setText(time_str);
            textView7.setVisibility(savedInstanceState.getInt("view7_status"));
            editTextTime.setVisibility(savedInstanceState.getInt("edit_status"));
            editTextTime2.setVisibility(savedInstanceState.getInt("edit_status2"));
            editTextTime3.setVisibility(savedInstanceState.getInt("edit_status3"));
            enter_time.setText(savedInstanceState.getCharSequence("enter_status"));

        }
        runTimerDown();

        this.notificationManagerCompat = NotificationManagerCompat.from(this);
    }


    // показываем уведомление в течение 5 секунд
    public void handler2() {
        final Handler handler = new Handler();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (times<=5) {
                        times++;
                        sendOnChannel();
                        handler.postDelayed(this, 1000);
                    }
                }
            });
    }


    // создаем уведомление и его отправка
    private void sendOnChannel()  {
        //интент для уведомления
        Intent timer1Intent = new Intent(this, One_timer.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(timer1Intent);
        //PendingIntent timer1PendingIntent = PendingIntent.getActivity(this, 0, timer1Intent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent timer1PendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT
              | PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new NotificationCompat.Builder(this, NotificationApp.CHANNEL_ID_1)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Таймер 1")
                        .setContentText("Время вышло!")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .setContentIntent(timer1PendingIntent)
                        .build();

                int notificationId = 1;
                this.notificationManagerCompat.notify(notificationId, notification);
    }



    public void start_timer(View view) {
        //running7 = true;
        Button enter_time = (Button) findViewById(R.id.enter_time);
        EditText editTextTime = (EditText) findViewById(R.id.editTextTime);
        EditText editTextTime2 = (EditText) findViewById(R.id.editTextTime2);
        EditText editTextTime3 = (EditText) findViewById(R.id.editTextTime3);
        if (running7==false) {
            running7 = true;
            enter_time.setText("Пауза");
            //seconds7 = seconds7_temp;
            if (seconds7_temp == 0) {
                hours = editTextTime.getText().toString();
                minutes = editTextTime2.getText().toString();
                seconds = editTextTime3.getText().toString();

                //  TextView textView8 = (TextView) findViewById(R.id.textView8);
                // textView8.setText(hours + ":" + minutes + ":" + seconds);

                int hours2 = Integer.parseInt(hours);
                int seconds11 = hours2 * 3600;

                int minutes2 = Integer.parseInt(minutes);
                int seconds22 = minutes2 * 60;

                int seconds2 = Integer.parseInt(seconds);
                //int seconds33 = seconds2 * 60;
                seconds7 = seconds11 + seconds22 + seconds2;

                editTextTime.setVisibility(View.INVISIBLE);
                editTextTime2.setVisibility(View.INVISIBLE);
                editTextTime3.setVisibility(View.INVISIBLE);
            } else {
                seconds7 = seconds7_temp;
            }

            //wasStart = true;
        } else {
            running7 = false;
            clickedPause = true;
            enter_time.setText("Старт");


        }
    }

    public void reset_timer1 (View view) {
        running7 = false;
        seconds7 = 0;
        seconds7_temp = 0;
       // wasStart=true;
        clickedReset=true;

        EditText editTextTime = (EditText) findViewById(R.id.editTextTime);
        EditText editTextTime2 = (EditText) findViewById(R.id.editTextTime2);
        EditText editTextTime3 = (EditText) findViewById(R.id.editTextTime3);
        editTextTime.setVisibility(View.VISIBLE);
        editTextTime2.setVisibility(View.VISIBLE);
        editTextTime3.setVisibility(View.VISIBLE);
        editTextTime.setText("00");
        editTextTime2.setText("00");
        editTextTime3.setText("00");

        Button enter_time = (Button) findViewById(R.id.enter_time);
        enter_time.setText("Старт");

    }

    // реализация таймера
    public void runTimerDown () {
        Button enter_time = (Button) findViewById(R.id.enter_time);
        EditText editTextTime = (EditText) findViewById(R.id.editTextTime);
        EditText editTextTime2 = (EditText) findViewById(R.id.editTextTime2);
        EditText editTextTime3 = (EditText) findViewById(R.id.editTextTime3);
        final TextView textView7 = (TextView) findViewById(R.id.textView7);
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {

                int hours = seconds7/3600;
                int minutes = (seconds7%3600)/60;
                int secs = seconds7%60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
               // String time = Integer.toString(seconds7);

                if (running7 && seconds7 != 0) {
                    seconds7--;
                    seconds7_temp = seconds7;
                    time_str = time;
                    textView7.setText(time);
                    textView7.setVisibility(View.VISIBLE);
                }
                //время истекло
                if (running7==true && seconds7 == 0) {
                    running7 = false;
                    seconds7 = 0;
                    times = 1;
                    seconds7_temp = 0;
                    handler2();

                    textView7.setVisibility(View.INVISIBLE);
                    editTextTime.setVisibility(View.VISIBLE);
                    editTextTime2.setVisibility(View.VISIBLE);
                    editTextTime3.setVisibility(View.VISIBLE);
                    editTextTime.setText("00");
                    editTextTime2.setText("00");
                    editTextTime3.setText("00");
                    enter_time.setText("Старт");
                }

                //пользователь нажал кнопку "отмена"
                if (running7 == false && clickedReset == true) {
                        //sendOnChannel();
                        clickedReset = false;
                        times = 1;
                        //handler2();
                        wasStart=false;
                        textView7.setVisibility(View.INVISIBLE);
                        editTextTime.setVisibility(View.VISIBLE);
                        editTextTime2.setVisibility(View.VISIBLE);
                        editTextTime3.setVisibility(View.VISIBLE);
                        editTextTime.setText("00");
                        editTextTime2.setText("00");
                        editTextTime3.setText("00");
                        enter_time.setText("Старт");


                    }


                handler.postDelayed(this, 1000);
            }
        });

    }

    public void onSaveInstanceState (Bundle SaveInstanceState) {
        super.onSaveInstanceState(SaveInstanceState);
        SaveInstanceState.putInt("seconds7", seconds7);
        SaveInstanceState.putInt("seconds7_temp", seconds7_temp);
        SaveInstanceState.putBoolean("running7", running7);
        SaveInstanceState.putString("time_str", time_str);

        Button enter_time = (Button) findViewById(R.id.enter_time);
        TextView textView7 = (TextView) findViewById(R.id.textView7);
        EditText editTextTime = (EditText) findViewById(R.id.editTextTime);
        EditText editTextTime2 = (EditText) findViewById(R.id.editTextTime2);
        EditText editTextTime3 = (EditText) findViewById(R.id.editTextTime3);

        int view7_status = textView7.getVisibility();
        int edit_status = editTextTime.getVisibility();
        int edit_status2 = editTextTime2.getVisibility();
        int edit_status3 = editTextTime3.getVisibility();
        CharSequence enter_status = enter_time.getText();
        SaveInstanceState.putInt("view7_status", view7_status);
        SaveInstanceState.putInt("edit_status", edit_status);
        SaveInstanceState.putInt("edit_status2", edit_status2);
        SaveInstanceState.putInt("edit_status3", edit_status3);
        SaveInstanceState.putCharSequence("enter_status", enter_status);

    }
}