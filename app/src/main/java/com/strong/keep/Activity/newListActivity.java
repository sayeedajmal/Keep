package com.strong.keep.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.strong.keep.R;
import com.strong.keep.SqlHelper;
import com.strong.keep.databinding.ActivityNewListBinding;

import java.util.Objects;

public class newListActivity extends AppCompatActivity {
    ActivityNewListBinding BindList;
    SqlHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindList = ActivityNewListBinding.inflate(getLayoutInflater());

        BindList.backButton.setOnClickListener(e -> onBackPressed());

        sqlHelper = new SqlHelper(this);
        BindList.Save.setOnClickListener(v -> {
            String ListValue = Objects.requireNonNull(BindList.ListValue.getText()).toString();
            if (!ListValue.isEmpty()) {
                Boolean checkInsertData = sqlHelper.createList(ListValue);
                if (checkInsertData) {
                    showToast("ListAdded");
                    finish();
                } else
                    showToast("Something Error");
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