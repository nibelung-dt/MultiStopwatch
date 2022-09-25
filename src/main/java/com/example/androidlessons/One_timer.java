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

    //private EditText editTextTime = (EditText) findViewById(R.id.editTextTime);
    //private EditText editTextTime2 = (EditText) findViewById(R.id.editTextTime2);
    //private EditText editTextTime3 = (EditText) findViewById(R.id.editTextTime3);

    private int seconds7;
    private Boolean running7 = false;
    private Boolean wasStart = false;

    private String hours = "0";
    private String minutes = "0";
    private String seconds = "0";

    private int times = 1;

    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        running7 = true;
        EditText editTextTime = (EditText) findViewById(R.id.editTextTime);
        EditText editTextTime2 = (EditText) findViewById(R.id.editTextTime2);
        EditText editTextTime3 = (EditText) findViewById(R.id.editTextTime3);

            hours =  editTextTime.getText().toString();
            minutes =  editTextTime2.getText().toString();
            seconds =  editTextTime3.getText().toString();


      //  TextView textView8 = (TextView) findViewById(R.id.textView8);
       // textView8.setText(hours + ":" + minutes + ":" + seconds);

        int hours2 = Integer.parseInt(hours);
        int seconds11 = hours2 * 3600;

        int minutes2 = Integer.parseInt(minutes);
        int seconds22 = minutes2 * 60;

        int seconds2 = Integer.parseInt(seconds);
        //int seconds33 = seconds2 * 60;
        seconds7 = seconds11+seconds22+seconds2;

        editTextTime.setVisibility(View.INVISIBLE);
        editTextTime2.setVisibility(View.INVISIBLE);
        editTextTime3.setVisibility(View.INVISIBLE);

        wasStart = true;

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
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
               // String time = Integer.toString(seconds7);
                textView7.setText(time);

                if (running7 && seconds7 != 0) {
                    seconds7--;
                    textView7.setVisibility(View.VISIBLE);
                } else {
                    running7 = false;
                    seconds7 = 0;
                    if (wasStart==true) {
                        //sendOnChannel();
                        times = 1;
                        handler2();
                        wasStart=false;
                        //textView7.setVisibility(View.INVISIBLE);
                        //editTextTime.setVisibility(View.VISIBLE);
                        //editTextTime2.setVisibility(View.VISIBLE);
                        //editTextTime3.setVisibility(View.VISIBLE);
                    }

                }
                handler.postDelayed(this, 1000);
            }
        });

    }


}