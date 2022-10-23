package com.strong.keep.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.strong.keep.databinding.ActivitySplashBinding;

public class splash extends AppCompatActivity {
    ActivitySplashBinding BindSplash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        BindSplash = ActivitySplashBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(BindSplash.getRoot());

        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, Login.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finishAffinity();
        }, 100);
    }
}
