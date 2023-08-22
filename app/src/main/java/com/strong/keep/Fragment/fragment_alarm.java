package com.strong.keep.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.strong.keep.databinding.FragmentAlarmBinding;

public class fragment_alarm extends Fragment {
    FragmentAlarmBinding BindAlarm;
    int hour, minute, year, monthOfYear, DayOfMonth;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BindAlarm = FragmentAlarmBinding.inflate(inflater, container, false);

        BindAlarm.timePicker.setOnTimeChangedListener((view, hourOfDay, minute) -> {
            hour = hourOfDay;
            Snackbar.make(BindAlarm.CancelTimer, hourOfDay + ":" + minute, Snackbar.LENGTH_SHORT).show();
        });

        /*Instance value of Time and Date*/
        hour = BindAlarm.timePicker.getHour();
        minute = BindAlarm.timePicker.getMinute();
        year = BindAlarm.datePicker.getYear();
        monthOfYear = BindAlarm.datePicker.getMonth();
        DayOfMonth = BindAlarm.datePicker.getDayOfMonth();

        BindAlarm.datePicker.setOnDateChangedListener((view, year, monthOfYear, dayOfMonth) -> {
            this.year = year;
            this.monthOfYear = monthOfYear;
            DayOfMonth = dayOfMonth;
            Snackbar.make(BindAlarm.CancelTimer, dayOfMonth + "/" + monthOfYear + "/" + year, Snackbar.LENGTH_SHORT).show();
        });

        BindAlarm.DoneTimer.setOnClickListener(v -> Toast.makeText(getContext(), hour, Toast.LENGTH_SHORT));

        BindAlarm.CancelTimer.setOnClickListener(v -> {

        });
        return BindAlarm.getRoot();
    }
}