package com.strong.keep.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.strong.keep.databinding.ActivityNewTaskBinding;

public class newTask extends AppCompatActivity {
    ActivityNewTaskBinding newTaskBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newTaskBind = ActivityNewTaskBinding.inflate(getLayoutInflater());
        setContentView(newTaskBind.getRoot());

        newTaskBind.backbutton.setOnClickListener(v -> onBackPressed());
    }
}