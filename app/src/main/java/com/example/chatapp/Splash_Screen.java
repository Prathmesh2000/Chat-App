package com.example.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash_Screen extends AppCompatActivity {
    TextView text;
    Animation bounce_anim;
    Handler h;
    Runnable r;
    Long delaytime;
    Long t=3000L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        text = findViewById(R.id.txt);
        bounce_anim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
        text.startAnimation(bounce_anim);
        h = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash_Screen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        };

    }
    @Override
    protected void onResume() {
        super.onResume();
        h.postDelayed(r,t);
        t=System.currentTimeMillis();
    }
    @Override
    protected void onStop() {
        super.onStop();
        h.removeCallbacks(r);
    }
}