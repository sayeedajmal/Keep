package com.strong.keep.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.strong.keep.R;
import com.strong.keep.SqlHelper;
import com.strong.keep.databinding.ActivityNewListBinding;

import java.util.Objects;

public class newListActivity extends AppCompatActivity {
    ActivityNewListBinding BindList;
    SqlHelper sqlHelper;
    BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindList = ActivityNewListBinding.inflate(getLayoutInflater());

        BindList.backButton.setOnClickListener(e -> onBackPressed());

        sqlHelper = new SqlHelper(this);

        BindList.listAlarm.setOnClickListener(v -> showBottom());


        BindList.Save.setOnClickListener(v -> {
            String ListValue = Objects.requireNonNull(BindList.ListValue.getText()).toString();
            if (ListValue.isEmpty()) {
                BindList.ListValue.setError("Enter Some Quotes");
                BindList.ListValue.requestFocus();
            } else {
                long Time = System.currentTimeMillis();
                Boolean checkInsertData = sqlHelper.createList(ListValue, (int) Time);
                if (checkInsertData) {
                    showToast("List Added");
                    finish();
                } else showToast("Something Error");
            }
        });
        setContentView(BindList.getRoot());
    }

    private void showToast(String Message) {
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
    }

    private void showBottom() {
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.fragment_alarm);
        TimePicker timePicker = bottomSheetDialog.findViewById(R.id.timePicker);
        DatePicker datePicker = bottomSheetDialog.findViewById(R.id.date_picker);
        Button cancel = bottomSheetDialog.findViewById(R.id.CancelTimer);
        Button Done = bottomSheetDialog.findViewById(R.id.DoneTimer);

        assert timePicker != null;
        timePicker.setOnTimeChangedListener((timePicker1, i, i1) -> Toast.makeText(this, timePicker1.getHour() + " : " + timePicker1.getMinute(), Toast.LENGTH_SHORT).show());

        assert datePicker != null;
        datePicker.setOnDateChangedListener((datePicker1, i, i1, i2) -> Toast.makeText(newListActivity.this, datePicker1.getDayOfMonth() + " : " + datePicker1.getMonth(), Toast.LENGTH_SHORT).show());

        assert cancel != null;
        cancel.setOnClickListener(v -> bottomSheetDialog.dismiss());

        assert Done != null;
        Done.setOnClickListener(v -> {

        });
        bottomSheetDialog.show();
        bottomSheetDialog.setOnDismissListener(dialog -> bottomSheetDialog.dismiss());
    }
}