package com.strong.keep.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.strong.keep.SqlHelper;
import com.strong.keep.databinding.ActivityNewTaskBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class newTaskActivity extends AppCompatActivity {
    ActivityNewTaskBinding newTaskBind;
    SqlHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newTaskBind = ActivityNewTaskBinding.inflate(getLayoutInflater());
        setContentView(newTaskBind.getRoot());

        /*Current Time */
        newTaskBind.currentTime.setText(getTime());
        sqlHelper = new SqlHelper(this);


        newTaskBind.taskValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newTaskBind.currentTime.setText(getTime() + " | " + s.length() + " Characters");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        newTaskBind.saveButton.setOnClickListener(v -> {
            String Title = Objects.requireNonNull(newTaskBind.Title.getText()).toString();
            String TaskValue = Objects.requireNonNull(newTaskBind.taskValue.getText()).toString();
            long time = System.currentTimeMillis();
            if (Title.isEmpty()) {
                newTaskBind.Title.setError("Enter A Title Name");
                newTaskBind.Title.requestFocus();
            } else if (TaskValue.isEmpty()) {
                newTaskBind.taskValue.setError("Enter  Some Task");
                newTaskBind.taskValue.requestFocus();
            } else {
                Boolean checkInsertData = sqlHelper.createTask(Title, TaskValue, (int) time);
                if (checkInsertData) {
                    showToast("Task Added");
                    finish();
                } else showToast("Something Error");
            }
        });
        newTaskBind.backbutton.setOnClickListener(v -> onBackPressed());
    }

    private void showToast(String Message) {
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
    }

    private String getTime() {
        return new SimpleDateFormat("dd/MM/yyyy - hh:mm a", Locale.getDefault()).format(new Date().getTime());
    }
}