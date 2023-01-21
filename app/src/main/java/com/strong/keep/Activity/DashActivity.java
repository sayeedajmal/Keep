package com.strong.keep.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.strong.keep.Fragment.ListTaskFrag;
import com.strong.keep.Fragment.TaskFrag;
import com.strong.keep.R;
import com.strong.keep.SqlHelper;
import com.strong.keep.databinding.ActivityDashboardBinding;

import java.util.Objects;

public class DashActivity extends AppCompatActivity {
    ViewPagerSection viewPagerAdaptor;
    SqlHelper sqlHelper;
    ActivityDashboardBinding BindRecent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindRecent = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(BindRecent.getRoot());

        TaskFrag taskFragFragment = new TaskFrag();
        ListTaskFrag listFragment = new ListTaskFrag();

        viewPagerAdaptor = new ViewPagerSection(getSupportFragmentManager(), 0);

        viewPagerAdaptor.addFragment(taskFragFragment);
        viewPagerAdaptor.addFragment(listFragment);

        BindRecent.dashboardPager.setAdapter(viewPagerAdaptor);
        BindRecent.tabLayoutDashboard.setupWithViewPager(BindRecent.dashboardPager);
        Objects.requireNonNull(BindRecent.tabLayoutDashboard.getTabAt(0)).setIcon(R.drawable.task);
        Objects.requireNonNull(BindRecent.tabLayoutDashboard.getTabAt(0)).setId(0);
        Objects.requireNonNull(BindRecent.tabLayoutDashboard.getTabAt(1)).setIcon(R.drawable.checked);
        Objects.requireNonNull(BindRecent.tabLayoutDashboard.getTabAt(0)).setId(1);
        sqlHelper = new SqlHelper(this);

        BindRecent.newTaskButton.setOnClickListener(v -> {
            if (Objects.requireNonNull(BindRecent.tabLayoutDashboard.getTabAt(0)).isSelected()) {
                Intent intent = new Intent(this, newTaskActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, newListActivity.class);
                startActivity(intent);
            }
        });

        BindRecent.goLoginButton.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));

        initSearch();
    }

    private void initSearch() {
        BindRecent.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println("<<<<<<<<<<<<<" + newText);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
