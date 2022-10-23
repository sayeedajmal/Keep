package com.strong.keep.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.strong.keep.databinding.ActivityListactivityBinding;

public class ListActivity extends AppCompatActivity {

    ActivityListactivityBinding BindList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindList = ActivityListactivityBinding.inflate(getLayoutInflater());

        BindList.backButton.setOnClickListener(e -> onBackPressed());
        setContentView(BindList.getRoot());
    }
}