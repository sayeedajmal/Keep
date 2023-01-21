package com.strong.keep.Activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.strong.keep.R;
import com.strong.keep.SqlHelper;
import com.strong.keep.databinding.ActivityNewListBinding;

import java.util.Objects;

public class newListActivity extends AppCompatActivity {
    ActivityNewListBinding BindList;
    SqlHelper sqlHelper;
    static int hour, minute, year, monthOfYear, DayOfMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindList = ActivityNewListBinding.inflate(getLayoutInflater());

        BindList.backButton.setOnClickListener(e -> onBackPressed());

        sqlHelper = new SqlHelper(this);

        BindList.setReminder.setOnClickListener(v -> {
            BindList.setReminder.setVisibility(View.GONE);
            BindList.listLayout.setVisibility(View.GONE);
            BindList.Save.setVisibility(View.GONE);

            BindList.timePicker.setVisibility(View.VISIBLE);
            BindList.datePicker.setVisibility(View.VISIBLE);
            BindList.CancelTimer.setVisibility(View.VISIBLE);
            BindList.DoneTimer.setVisibility(View.VISIBLE);
        });

        BindList.timePicker.setOnTimeChangedListener((view, hourOfDay, minute) -> {
            hour = hourOfDay;
            Snackbar.make(findViewById(R.id.listLayout), hourOfDay + ":" + minute, Snackbar.LENGTH_SHORT).show();
        });

        /*Instance value of Time and Date*/
        hour = BindList.timePicker.getHour();
        minute = BindList.timePicker.getMinute();
        year = BindList.datePicker.getYear();
        monthOfYear = BindList.datePicker.getMonth();
        DayOfMonth = BindList.datePicker.getDayOfMonth();

        BindList.datePicker.setOnDateChangedListener((view, year, monthOfYear, dayOfMonth) -> {
            year = year;
            monthOfYear = monthOfYear;
            DayOfMonth = dayOfMonth;
            Snackbar.make(findViewById(R.id.listLayout), dayOfMonth + "/" + monthOfYear + "/" + year, Snackbar.LENGTH_SHORT).show();
        });

        BindList.DoneTimer.setOnClickListener(v -> Toast.makeText(this, hour, Toast.LENGTH_SHORT));

        BindList.CancelTimer.setOnClickListener(v -> {
            BindList.setReminder.setVisibility(View.VISIBLE);
            BindList.listLayout.setVisibility(View.VISIBLE);
            BindList.Save.setVisibility(View.VISIBLE);

            BindList.timePicker.setVisibility(View.GONE);
            BindList.datePicker.setVisibility(View.GONE);
            BindList.CancelTimer.setVisibility(View.GONE);
            BindList.DoneTimer.setVisibility(View.GONE);

        });

        BindList.Save.setOnClickListener(v -> {
            String ListValue = Objects.requireNonNull(BindList.ListValue.getText()).toString();
            if (!ListValue.isEmpty()) {
                Boolean checkInsertData = sqlHelper.createList(ListValue);
                if (checkInsertData) {
                    showToast("ListAdded");
                    finish();
                } else showToast("Something Error");
            } else {
                showToast("Start Typing for Add");
            }

        });
        setContentView(BindList.getRoot());
    }

    private void showToast(String Message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_layout));
        Toast toast = new Toast(getApplicationContext());
        TextView text = (TextView) layout.findViewById(R.id.message);
        text.setText(Message);
        toast.setGravity(Gravity.BOTTOM, 0, 10);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}