package com.strong.keep.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.strong.keep.R;
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
            if (!Title.isEmpty() && !TaskValue.isEmpty()) {
                Boolean checkInsertData = sqlHelper.createTask(Title, TaskValue);
                if (checkInsertData) {
                    showToast("Task Added");
                    finish();
                } else
                    showToast("Something Error");
            } else {
                showToast("Start Typing for Save");
            }

        });
        newTaskBind.backbutton.setOnClickListener(v -> onBackPressed());
    }

    private void showToast(String Message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_layout));
        Toast toast = new Toast(getApplicationContext());
        TextView text = layout.findViewById(R.id.message);
        text.setText(Message);
        toast.setGravity(Gravity.BOTTOM, 0, 10);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    private String getTime() {
        return new SimpleDateFormat("dd/MM/yyyy - hh:mm a", Locale.getDefault()).format(
                new Date().getTime());
    }
}