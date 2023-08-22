package com.strong.keep.Fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.strong.keep.Adopter.TaskAdopter;
import com.strong.keep.GetSet.TaskGetter;
import com.strong.keep.SqlHelper;
import com.strong.keep.databinding.FragmentRecyclerBinding;

import java.util.ArrayList;

public class TaskFrag extends Fragment {

    static ArrayList<TaskGetter> taskList;
    FragmentRecyclerBinding RecyclerBind;
    static SqlHelper sqlHelper;
    @SuppressLint("StaticFieldLeak")
    static TaskAdopter taskAdopter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerBind = FragmentRecyclerBinding.inflate(inflater, container, false);

        taskList = new ArrayList<>();
        sqlHelper = new SqlHelper(getContext());

        taskAdopter = new TaskAdopter(taskList, getContext());
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        RecyclerBind.RecyclerView.setLayoutManager(layoutManager);
        RecyclerBind.RecyclerView.setAdapter(taskAdopter);

        RecyclerBind.swipeRefresh.setOnRefreshListener(() -> {
            refreshData();
            RecyclerBind.swipeRefresh.setRefreshing(false);
        });

        return RecyclerBind.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    @SuppressLint("NotifyDataSetChanged")
    public static void refreshData() {
        taskList.clear();
        Cursor res = sqlHelper.SHOW("Task");
        if (res.getCount() > 0) {
            while (res.moveToNext()) {
                taskList.add(new TaskGetter(res.getString(0), res.getString(1)));
            }
        }
        taskAdopter.notifyDataSetChanged();
    }
}
