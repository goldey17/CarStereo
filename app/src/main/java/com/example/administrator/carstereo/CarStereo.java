package com.example.administrator.carstereo;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.IOException;
import java.util.ArrayList;


public class CarStereo extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener, View.OnLongClickListener{
    ToggleButton power;
    TextView radioDisplay;
    TextView cdSlot;
    TextView volume;
    SeekBar tuner;
    ToggleButton amFm;
    Button preset1, preset2, preset3, preset4, preset5, preset6;
    int[] amPresets = {550, 600, 650, 700, 750, 800};
    double[] fmPresets = {90.9,92.9,94.9,96.9,98.9,100.9};
    SeekBar volumebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_stereo);
        power = (ToggleButton) findViewById(R.id.power);
        radioDisplay = (TextView) findViewById(R.id.radioDisplay);
        cdSlot = (TextView) findViewById(R.id.cdSlot);
        volume = (TextView) findViewById(R.id.volume);
        tuner = (SeekBar) findViewById(R.id.tuner);
        tuner.setOnSeekBarChangeListener(this);
        amFm = (ToggleButton) findViewById(R.id.AMFM);
        tuner.setMax(117);
        preset1 = (Button) findViewById(R.id.preset1);
        preset2 = (Button) findViewById(R.id.preset2);
        preset3 = (Button) findViewById(R.id.preset3);
        preset4 = (Button) findViewById(R.id.preset4);
        preset5 = (Button) findViewById(R.id.preset5);
        preset6 = (Button) findViewById(R.id.preset6);
        preset1.setOnLongClickListener(this);
        preset2.setOnLongClickListener(this);
        preset3.setOnLongClickListener(this);
        preset4.setOnLongClickListener(this);
        preset5.setOnLongClickListener(this);
        preset6.setOnLongClickListener(this);
        volumebar = (SeekBar) findViewById(R.id.volumeDial);
        volumebar.setOnSeekBarChangeListener(this);
        volumebar.setMax(99);
        try {
            String url = "http://www.noiseaddicts.com/samples_1w72b820/2540.mp3"; // your URL here
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare(); // might take long! (for buffering, etc)
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void powerButtonClick(View v) {
       if (!power.isChecked()){
           radioDisplay.setBackgroundColor(Color.BLACK);
           cdSlot.setBackgroundColor(Color.BLACK);
           volume.setBackgroundColor(Color.BLACK);
           tuner.setBackgroundColor(Color.BLACK);
       }else{
           radioDisplay.setBackgroundColor(Color.MAGENTA);
           cdSlot.setBackgroundColor(Color.GRAY);
           volume.setBackgroundColor(0);
           tuner.setBackgroundColor(0);
       }
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (power.isChecked()) {
            if (seekBar == tuner){
                if (amFm.isChecked()) {
                    //FM
                    double value = (double) (88.1 + progress * 0.2);
                    String text = "" + value;
                    int period = text.indexOf(".");
                    text = text.substring(0,period + 2);

                    if (value > 107.9){
                        radioDisplay.setText("107.9 FM");
                    }else{
                        radioDisplay.setText("" + text + " FM");
                    }

                } else {
                    //AM
                    int value = (int) (530 + progress * 10);
                    radioDisplay.setText("" + value + " AM");
                }
            }else{
                volume.setText(""+ progress);
            }

        }
    }
    public void preset(View v){
        if (power.isChecked()){
            v.setBackgroundColor(0);
            if (amFm.isChecked()){
                //fm
                if (v == preset1){
                    radioDisplay.setText("" + fmPresets[0] + " FM" );
                }
                if (v == preset2){
                    radioDisplay.setText("" + fmPresets[1] + " FM" );
                }
                if (v == preset3){
                    radioDisplay.setText("" + fmPresets[2] + " FM" );
                }
                if (v == preset4){
                    radioDisplay.setText("" + fmPresets[3] + " FM" );
                }
                if (v == preset5){
                    radioDisplay.setText("" + fmPresets[4] + " FM" );
                }
                if (v == preset6){
                    radioDisplay.setText("" + fmPresets[5] + " FM" );
                }
            }else{
                if (v == preset1){
                    radioDisplay.setText("" + amPresets[0] + " AM" );
                }
                if (v == preset2){
                    radioDisplay.setText("" + amPresets[1] + " AM" );
                }
                if (v == preset3){
                    radioDisplay.setText("" + amPresets[2] + " AM" );
                }
                if (v == preset4){
                    radioDisplay.setText("" + amPresets[3] + " AM");
                }
                if (v == preset5){
                    radioDisplay.setText("" + amPresets[4] + " AM");
                }
                if (v == preset6){
                    radioDisplay.setText("" + amPresets[5] + " AM");
                }
            }
        }
    }

    public void amfmclicked(View v){
        if (power.isChecked()) {
            if (amFm.isChecked()) {
                //FM
                radioDisplay.setText("88.1 FM");
            } else {
                radioDisplay.setText("530 AM");
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public boolean onLongClick(View v) {
        String value = (String) radioDisplay.getText();
        int index = value. indexOf(" ");
        value = value.substring(0,index);
        if (power.isChecked()){
            if (amFm.isChecked()){
                //fm
                if (v == preset1){
                    fmPresets[0] = Double.parseDouble(value);
                }
                if (v == preset2){
                    fmPresets[1] = Double.parseDouble(value);
                }
                if (v == preset3){
                    fmPresets[2] = Double.parseDouble(value);
                }
                if (v == preset4){
                    fmPresets[3] = Double.parseDouble(value);
                }
                if (v == preset5){
                    fmPresets[4] = Double.parseDouble(value);
                }
                if (v == preset6){
                    fmPresets[5] = Double.parseDouble(value);
                }
            }else{
                if (v == preset1){
                    amPresets[0] = Integer.parseInt(value);
                }
                if (v == preset2){
                    amPresets[1] = Integer.parseInt(value);
                }
                if (v == preset3){
                    amPresets[2] = Integer.parseInt(value);
                }
                if (v == preset4){
                    amPresets[3] = Integer.parseInt(value);
                }
                if (v == preset5){
                    amPresets[4] = Integer.parseInt(value);
                }
                if (v == preset6){
                    amPresets[5] = Integer.parseInt(value);
                }
            }
            v.setBackgroundColor(Color.BLUE);
        }
        return true;
    }

}
