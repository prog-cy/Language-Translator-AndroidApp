package com.example.languagetranslator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class Splash_Screen extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //For adding animation before going to main screen
        lottieAnimationView = findViewById(R.id.lottie_layer_name);
        lottieAnimationView.setAnimation(R.raw.lottie);
        lottieAnimationView.playAnimation();
        lottieAnimationView.loop(true);

        //This is for handling the thread to make delay loading the main screen
        new Handler().postDelayed(() -> {
            Intent intent;
            intent = new Intent(Splash_Screen.this,MainActivity.class);
            startActivity(intent);
            finish();
        },1000);

    }
}