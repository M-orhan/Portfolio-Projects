package com.example.relaxingsounds;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Arrays;
import java.util.List;


public class SecondPage extends AppCompatActivity {
    ImageButton imageButton;
    SeekBar seekbar_rain,seekbar_thunder,seekbar_wind,seekbar_fire,seekbar_crickets,seekbar_wave;

    MediaPlayer mp1,mp2,mp3,mp4,mp5,mp6;

    private AdView mAdView,mAdView_top;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_page);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.second_page), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        mAdView_top=findViewById(R.id.adView_top);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView_top.loadAd(adRequest);


        imageButton = (ImageButton) findViewById(R.id.imageButton);
        seekbar_rain = (SeekBar) findViewById(R.id.seekbar_rain);
        seekbar_thunder = (SeekBar) findViewById(R.id.seekbar_thunder);
        seekbar_wind = (SeekBar) findViewById(R.id.seekbar_wind);
        seekbar_fire = (SeekBar) findViewById(R.id.seekbar_fire);
        seekbar_crickets = (SeekBar) findViewById(R.id.seekbar_crickets);
        seekbar_wave = (SeekBar) findViewById(R.id.seekbar_wave);

        //PROGRESS SETTINGS
        seekbar_wave.setProgress(0);
        seekbar_fire.setProgress(0);
        seekbar_crickets.setProgress(0);
        seekbar_wind.setProgress(0);
        seekbar_thunder.setProgress(0);
        seekbar_rain.setProgress(0);

        mp1=MediaPlayer.create(SecondPage.this,R.raw.wave);
        mp2=MediaPlayer.create(SecondPage.this,R.raw.campfire);
        mp3=MediaPlayer.create(SecondPage.this,R.raw.crickets);
        mp4=MediaPlayer.create(SecondPage.this,R.raw.rain);
        mp5=MediaPlayer.create(SecondPage.this,R.raw.thunder);
        mp6=MediaPlayer.create(SecondPage.this,R.raw.wind);



        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 20, 0);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp1.isPlaying()||mp2.isPlaying()||mp3.isPlaying()||mp4.isPlaying()||mp5.isPlaying()||mp6.isPlaying())
                {
                    mp1.pause();
                    mp2.pause();
                    mp3.pause();
                    mp4.pause();
                    mp5.pause();
                    mp6.pause();
                }
                else
                {
                    mp1.start();
                    mp2.start();
                    mp3.start();
                    mp4.start();
                    mp5.start();
                    mp6.start();
                }

            }
        });

        seekbar_wave.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mp1.start();
                mp1.setLooping(true);
                return false;
            }
        });



        seekbar_wave.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mp1.setVolume(progress / 100f, progress / 100f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbar_fire.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mp2.setVolume(progress / 100f, progress / 100f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbar_fire.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mp2.start();
                mp2.setLooping(true);
                return false;
            }
        });

        seekbar_crickets.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mp3.setVolume(progress / 100f, progress / 100f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbar_crickets.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mp3.start();
                mp3.setLooping(true);
                return false;
            }
        });

        seekbar_rain.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mp4.setVolume(progress / 100f, progress / 100f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbar_rain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mp4.start();
                mp4.setLooping(true);
                return false;
            }
        });

        seekbar_thunder.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mp5.setVolume(progress / 100f, progress / 100f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbar_thunder.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mp5.start();
                mp5.setLooping(true);
                return false;
            }
        });

        seekbar_wind.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mp6.setVolume(progress / 100f, progress / 100f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbar_wind.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mp6.start();
                mp6.setLooping(true);
                return false;
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        mp1.stop();
        mp1.release();

    }

    private AdSize getAdSize() {
        // Determine the screen width (less decorations) to use for the ad width.
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = outMetrics.density;

        Display adContainerView = null;
        float adWidthPixels = adContainerView.getWidth();

        // If the ad hasn't been laid out, default to the full screen width.
        if (adWidthPixels == 0) {
            adWidthPixels = outMetrics.widthPixels;
        }

        int adWidth = (int) (adWidthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }

    private void loadBanner() {

        // Create a new ad view.
        AdView adView = new AdView(this);
        adView.setAdSize(getAdSize());
        adView.setAdUnitId("ca-app-pub-3940256099942544/9214589741");

    }

}









