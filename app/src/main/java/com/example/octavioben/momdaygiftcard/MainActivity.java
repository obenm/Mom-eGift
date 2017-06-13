package com.example.octavioben.momdaygiftcard;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.content.pm.ActivityInfo;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

    public FrameLayout btnBox, btnBoxTop, boxAnim, backgroundMessage, message, btnback;
    public boolean canOpenBox = true;
    public MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_main);


        btnBox = (FrameLayout) findViewById(R.id.regalo);
        btnBoxTop = (FrameLayout) findViewById(R.id.tapa);
        boxAnim = (FrameLayout) findViewById(R.id.cuadritoanimacion);
        backgroundMessage = (FrameLayout) findViewById(R.id.backgroundcajaabierta);
        message = (FrameLayout) findViewById(R.id.mensaje);
        btnback = (FrameLayout) findViewById(R.id.botonvolver);

        backgroundMessage.setVisibility(View.INVISIBLE);
        message.setVisibility(View.INVISIBLE);
        btnback.setVisibility(View.INVISIBLE);

        mp = MediaPlayer.create(this, R.raw.song);
        mp.setLooping(true);
        int MAX_VOLUME = 100;
        float volume = (float) (1 - (Math.log(MAX_VOLUME - 100) / Math.log(MAX_VOLUME)));
        mp.setVolume(volume, volume);

        btnBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canOpenBox == true)
                    openBox();
            }
        });

        btnBoxTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canOpenBox == true)
                    openBox();
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(canOpenBox == false)
                    closeMessage();
            }
        });
    }

    /*public void onDestroy() {
        mp.stop();
        super.onDestroy();
    }

    public void onPause() {
        if(canOpenBox == false)
            mp.pause();
        super.onPause();
    }

    public void onResume() {
        if(canOpenBox == false)
            mp.start();
        super.onResume();
    }*/

    private void closeMessage() {
        mp.stop();
        AlphaAnimation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setDuration(1000);
        fadeOut.setFillAfter(true);
        //message.startAnimation(fadeOut);
        backgroundMessage.startAnimation(fadeOut);
        btnback.startAnimation(fadeOut);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                message.setAlpha(0);
                message.setVisibility(View.INVISIBLE);
                backgroundMessage.setVisibility(View.INVISIBLE);
                btnback.setVisibility(View.INVISIBLE);
                boxAnim.setVisibility(View.VISIBLE);
                boxAnim.setAlpha(1);
                ScaleAnimation reducirTraslacion = new ScaleAnimation(4.8f, 0, 4.8f, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                reducirTraslacion.setDuration(1000);
                reducirTraslacion.setFillAfter(true);
                reducirTraslacion.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        boxAnim.setTranslationX(0);
                        boxAnim.setTranslationY(0);
                        RotateAnimation desrotate = new RotateAnimation(-280, 0, 0, 200);
                        desrotate.setDuration(2000);
                        desrotate.setFillAfter(true);
                        btnBoxTop.startAnimation(desrotate);

                        canOpenBox = true;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                boxAnim.startAnimation(reducirTraslacion);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void openBox() {
        mp.start();
        RotateAnimation rotate = new RotateAnimation(0, -280, 0, 200);
        rotate.setDuration(3000);
        rotate.setFillAfter(true);
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                boxAnim.setTranslationX(-7f);
                boxAnim.setTranslationY(-75f);
                ScaleAnimation aumentarTraslacion = new ScaleAnimation(0, 4.8f, 0, 4.8f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                aumentarTraslacion.setDuration(1000);
                aumentarTraslacion.setFillAfter(true);
                aumentarTraslacion.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        AlphaAnimation fadeIn = new AlphaAnimation(0, 1);
                        fadeIn.setDuration(500);
                        fadeIn.setFillAfter(true);
                        message.startAnimation(fadeIn);
                        backgroundMessage.startAnimation(fadeIn);
                        btnback.startAnimation(fadeIn);
                        message.setAlpha(1);
                        message.setVisibility(View.VISIBLE);
                        backgroundMessage.setVisibility(View.VISIBLE);
                        btnback.setVisibility(View.VISIBLE);
                        fadeIn.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                boxAnim.setVisibility(View.INVISIBLE);
                                boxAnim.setAlpha(0);

                                canOpenBox = false;
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                boxAnim.startAnimation(aumentarTraslacion);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        btnBoxTop.startAnimation(rotate);
    }
}
