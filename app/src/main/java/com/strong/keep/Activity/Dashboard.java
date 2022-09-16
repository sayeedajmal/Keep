package com.strong.keep.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.strong.keep.Fragment.DoneTask;
import com.strong.keep.Fragment.Task;
import com.strong.keep.R;
import com.strong.keep.databinding.ActivityDashboardBinding;

import java.util.Objects;

public class Dashboard extends AppCompatActivity {
    ViewPagerSection viewPagerAdaptor;
    ActivityDashboardBinding BindRecent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindRecent = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(BindRecent.getRoot());

        Task Fragment = new Task();
        DoneTask doneTask = new DoneTask();

        viewPagerAdaptor = new ViewPagerSection(getSupportFragmentManager(), 0);

        viewPagerAdaptor.addFragment(Fragment);
        viewPagerAdaptor.addFragment(doneTask);

        BindRecent.dashboardPager.setAdapter(viewPagerAdaptor);
        BindRecent.tabLayoutDashboard.setupWithViewPager(BindRecent.dashboardPager);
        Objects.requireNonNull(BindRecent.tabLayoutDashboard.getTabAt(0)).setIcon(R.drawable.task);
        Objects.requireNonNull(BindRecent.tabLayoutDashboard.getTabAt(1)).setIcon(R.drawable.checked);


        BindRecent.newTaskButton.setOnClickListener(e ->
                startActivity(new Intent(this, newTask.class)));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
