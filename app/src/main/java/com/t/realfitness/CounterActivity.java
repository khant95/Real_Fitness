package com.t.realfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ikovac.timepickerwithseconds.MyTimePickerDialog;
import com.ikovac.timepickerwithseconds.TimePicker;
import com.t.realfitness.SQliteRoom.Exercise;
import com.t.realfitness.SQliteRoom.MyDatabaseClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CounterActivity extends AppCompatActivity {

    TextView tvTime;
    Button btnStart, btnSelectTime;
    Toolbar toolbar;
    long totalSelectedTimeInMilli;
    long remainingTimeInMilli;
    MediaPlayer player;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);


        //variable init
        tvTime = findViewById(R.id.tvTimer);
        btnSelectTime = findViewById(R.id.selectTIme);
        btnStart = findViewById(R.id.btnStart);
        toolbar = findViewById(R.id.appBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Start Timer");

        //alarm init
        player = MediaPlayer.create(CounterActivity.this, R.raw.alaram);


        //whcih excercise selected
        key = getIntent().getStringExtra("key");


        //set click listner on select date button
        btnSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime();
            }
        });



        //set CLick listner on Start timer button
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (remainingTimeInMilli != 0) {
                    btnStart.setEnabled(false);
                    btnSelectTime.setEnabled(false);

                }


                //Start count down timer
                CountDownTimer countDownTimer = new CountDownTimer(remainingTimeInMilli, 1000) {
                    @Override
                    public void onTick(long l) {
                        remainingTimeInMilli = l;
                        int minutes = (int) (remainingTimeInMilli / 1000) / 60;
                        int second = (int) (remainingTimeInMilli / 1000) % 60;
                        String timeleftFOrmat = String.format(Locale.getDefault(), "%02d:%02d", minutes, second);
                        tvTime.setText(timeleftFOrmat);
                    }

                    @Override
                    public void onFinish() {
                        tvTime.setText("00:00");
                        try {
                            player.start();
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        }
                        saveRecord();
                    }
                };
                countDownTimer.start();
            }

        });

        //start player after completion of timer
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Intent intent = new Intent(CounterActivity.this, HistoryActivity.class);
                startActivity(intent);
                player.release();

            }
        });


    }

    private void saveRecord() {


        Date date = new Date();
        //Current date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String myDate = dateFormat.format(date);

        //current time
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String myTime = timeFormat.format(date);


        int minutes = (int) (totalSelectedTimeInMilli / 1000) / 60;
        int second = (int) (totalSelectedTimeInMilli / 1000) % 60;
        String timeWorkout = String.format(Locale.getDefault(), "%02d:%02d", minutes, second);

        //store excercise data in database
        Exercise exercise = new Exercise(key, myTime, myDate, timeWorkout);
        MyDatabaseClass myDatabaseClass = Room.databaseBuilder(CounterActivity.this, MyDatabaseClass.class, "exDatabase").allowMainThreadQueries().build();
        myDatabaseClass.myDao().insertData(exercise);


    }


    //selct timer from timepicker
    private void setTime() {
        MyTimePickerDialog mTimePicker = new MyTimePickerDialog(this, new MyTimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute, int seconds) {
                totalSelectedTimeInMilli = (hourOfDay * 3600000) + (minute * 60000) + seconds * 1000;
                remainingTimeInMilli = (hourOfDay * 3600000) + (minute * 60000) + seconds * 1000;
                String s = (seconds < 10 ? "0" : "") + seconds;
                String m = ((hourOfDay * 60 + minute) < 10 ? "0" : "") + (hourOfDay * 60 + minute);
                tvTime.setText(m + ":" + s);
            }
        }, 00, 00, 00, true);
        mTimePicker.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.release();
    }


//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        player.stop();
//        Toast.makeText(this, "onBac", Toast.LENGTH_SHORT).show();
//        player.release();
//    }
}