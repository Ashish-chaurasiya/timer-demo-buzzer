package com.example.timerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView timerTextView;
    CountDownTimer countDownTimer;
    String secondStrings;
    SeekBar timerSeekBar;
    Button goButton;
    Boolean counterActive = false;
    public void reset(){
        timerTextView.setText("0:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("Go!");
        counterActive = false;
    }
    public void button( View view ) {
        if(counterActive){
            reset();

        }else {
            counterActive = true;
            timerSeekBar.setEnabled(false);

            goButton.setText("Stop!");
            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick( long l ) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                    mPlayer.start();
                    reset();
                }
            }.start();
        }
    }

    public void updateTimer(int secondLeft){
        int i = secondLeft;
        int minutes = i / 60;
        int second = i-(minutes*60);
        secondStrings = Integer.toString(second);
        if(second<=9){
            secondStrings="0"+second;
        }
        timerTextView.setText(Integer.toString(minutes)+":"+secondStrings);
    }
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = findViewById(R.id.goButton);
        timerSeekBar = findViewById(R.id.timerSeekBar);
        timerTextView = findViewById(R.id.countDownTextView);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged( SeekBar seekBar, int i, boolean fromUser ) {
            updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch( SeekBar seekBar ) {

            }

            @Override
            public void onStopTrackingTouch( SeekBar seekBar ) {

            }
        });


    }


}