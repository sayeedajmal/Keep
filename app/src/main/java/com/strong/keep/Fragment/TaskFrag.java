package com.strong.keep.Fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.strong.keep.Adopter.TaskAdopter;
import com.strong.keep.GetSet.TaskGetter;
import com.strong.keep.SqlHelper;
import com.strong.keep.databinding.FragmentRecyclerBinding;

import java.util.ArrayList;

public class TaskFrag extends Fragment {

    ArrayList<TaskGetter> taskList;
    FragmentRecyclerBinding RecyclerBind;
    SqlHelper sqlHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerBind = FragmentRecyclerBinding.inflate(inflater, container, false);

        /*taskList = new ArrayList<>();

        taskList.add(new TaskGetter("DSA", "This is the View of DSA."));
        taskList.add(new TaskGetter("JAVA", "Java is the Object-Oriented Programming Language. If You Can Do all Thing! Choose Java.Java is the Object-Oriented Programming Language. If You Can Do all Thing! Choose Java.Java is the Object-Oriented Programming Language. If You Can Do all Thing! Choose Java"));
        taskList.add(new TaskGetter("Android Studio", "We are Creating Application with the help of Android Studio."));
*/
        sqlHelper = new SqlHelper(getContext());
        taskList = new ArrayList<>();

        TaskAdopter taskAdopter = new TaskAdopter(taskList, getContext());
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        RecyclerBind.RecyclerView.setLayoutManager(layoutManager);
        RecyclerBind.RecyclerView.setAdapter(taskAdopter);


        RecyclerBind.swipeRefresh.setOnRefreshListener(() -> {
            taskList.clear();
            Cursor res = sqlHelper.SHOW("Task");
            if (res.getCount() > 0) {
                while (res.moveToNext()) {
                    taskList.add(new TaskGetter(res.getString(0), res.getString(1)));
                }
            }
            taskAdopter.notifyDataSetChanged();
            RecyclerBind.RecyclerView.refreshDrawableState();
            RecyclerBind.swipeRefresh.setRefreshing(false);
        });
        RecyclerBind.RecyclerView.refreshDrawableState();
        taskAdopter.notifyDataSetChanged();
        return RecyclerBind.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        addData();
    }

    public void addData() {
        Cursor res = sqlHelper.SHOW("Task");
        if (res.getCount() > 0) {
            taskList.clear();
            while (res.moveToNext()) {
                taskList.add(new TaskGetter(res.getString(0), res.getString(1)));
            }
        }
    }
}