package com.strong.keep.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.strong.keep.Fragment.ListTaskFragment;
import com.strong.keep.Fragment.Task;
import com.strong.keep.R;
import com.strong.keep.databinding.ActivityDashboardBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dashboard extends AppCompatActivity {
    ViewPagerSection viewPagerAdaptor;
    ActivityDashboardBinding BindRecent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindRecent = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(BindRecent.getRoot());

        Task TaskFragment = new Task();
        ListTaskFragment listFragment = new ListTaskFragment();

        viewPagerAdaptor = new ViewPagerSection(getSupportFragmentManager(), 0);

        viewPagerAdaptor.addFragment(TaskFragment);
        viewPagerAdaptor.addFragment(listFragment);

        BindRecent.dashboardPager.setAdapter(viewPagerAdaptor);
        BindRecent.tabLayoutDashboard.setupWithViewPager(BindRecent.dashboardPager);
        Objects.requireNonNull(BindRecent.tabLayoutDashboard.getTabAt(0)).setIcon(R.drawable.task);
        Objects.requireNonNull(BindRecent.tabLayoutDashboard.getTabAt(0)).setId(0);
        Objects.requireNonNull(BindRecent.tabLayoutDashboard.getTabAt(1)).setIcon(R.drawable.checked);
        Objects.requireNonNull(BindRecent.tabLayoutDashboard.getTabAt(0)).setId(1);


        BindRecent.newTaskButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                if (Objects.requireNonNull(BindRecent.tabLayoutDashboard.getTabAt(0)).isSelected()) {
                    startActivity(new Intent(Dashboard.this, newTask.class));
                } else {
                    startActivity(new Intent(Dashboard.this, ListActivity.class));
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
