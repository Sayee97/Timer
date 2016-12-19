package com.example.android.timer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar sb;
boolean counter=false;
    Button b;
    CountDownTimer say;
MediaPlayer mp;
     TextView tv;
    public void updateText(int num){
        int min=(int)num/60;
        int seconds=num-min*60;
        String sec = Integer.toString(seconds);
        if(seconds<=9){
            sec="0"+sec;
        }
        String min1=Integer.toString(min);
        if(min<=9){
            min1="0"+min1;
        }
        tv.setText(min1+" : "+ sec);

    }

    public void control(View v){
        //Log.i("IS:","pressde");
        if(counter==false) {
            counter = true;
            sb.setEnabled(false);
        say=    new CountDownTimer(sb.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    b.setText("STOP!!!");
                    updateText((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                  //  b.setText("STOP!!!");
                    // MediaPlayer mp = MediaPlayer.create(this,R.raw.alert);
                    reset();
                    tv.setText("00 : 00");
                    // Log.i("hey","DONE!!!");
                     mp = MediaPlayer.create(getApplicationContext(), R.raw.alert);
                    mp.start();

                }
            }.start();

    }
    else {
            reset();
        }


    }
    public void reset(){
        sb.setEnabled(true);
       // mp.stop();
        counter=false;
        sb.setProgress(30);
        b.setText("GO!!!");
        say.cancel();
        tv.setText("00 : 30");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         sb = (SeekBar)findViewById(R.id.timerSeekBar);
        b=(Button)findViewById(R.id.controllerButton);
        tv = (TextView)findViewById(R.id.timerTextView);
        sb.setMax(600);
        sb.setProgress(30);//seconds me hota hai
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
 updateText(progress);





            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
