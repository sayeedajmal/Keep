package com.strong.keep.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.strong.keep.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {
    ActivityLoginBinding BindLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindLogin = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(BindLogin.getRoot());

        BindLogin.goSignUp.setOnClickListener(e -> {
            startActivity(new Intent(this, signup.class));
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            finish();
        });

        BindLogin.Login.setOnClickListener(e -> {
            startActivity(new Intent(this, Dashboard.class));
            finish();
        });
    }
}