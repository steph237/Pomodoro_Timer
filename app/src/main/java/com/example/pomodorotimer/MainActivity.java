package com.example.pomodorotimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final long START_TIME= 600000;
    TextView countDownText;
    Button startButton;
    Button resetButton;
    CountDownTimer countDownTimer;
    Boolean timerRunning = false;
    private long timeLeft = START_TIME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countDownText= findViewById(R.id.counterText);
        startButton = findViewById(R.id.button_start);
        resetButton = findViewById(R.id.button_reset);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timerRunning){
                    pauseTimer();
                }

                else{
                    startTimer();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();

            }
        });


        updateCountDownText();
    }

    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeft,1000) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                updateCountDownText();

            }

            @Override
            public void onFinish() {

                timerRunning= false;
                startButton.setText("START");
                startButton.setVisibility(View.INVISIBLE);


            }
        }.start();

        timerRunning = true;
        startButton.setText("Pause");


    }

    public void pauseTimer(){

        countDownTimer.cancel();
        timerRunning = false;
        startButton.setText("Start");



    }

    public void resetTimer(){
        timeLeft = START_TIME;
        updateCountDownText();
        startButton.setVisibility(View.VISIBLE);


    }

    public void updateCountDownText(){
        int minutes = (int)( timeLeft/1000)/60;
        int seconds = (int)( timeLeft/1000)% 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        countDownText.setText(timeLeftFormatted);
    }
}