package com.strong.keep.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.strong.keep.databinding.ActivitySignupBinding;

public class signupActivity extends AppCompatActivity {
    ActivitySignupBinding BindSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindSignup=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(BindSignup.getRoot());

        BindSignup.goLogin.setOnClickListener(e->{
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        BindSignup.CreateNew.setOnClickListener(v->{
            startActivity(new Intent(this, DashActivity.class));
        });
    }
}