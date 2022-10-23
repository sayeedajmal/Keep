package com.strong.keep.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.strong.keep.Adopter.TaskAdopter;
import com.strong.keep.GetSet.TaskGetter;
import com.strong.keep.databinding.FragmentRecyclerBinding;

import java.util.ArrayList;

public class Task extends Fragment {

    ArrayList<TaskGetter> taskList;
    FragmentRecyclerBinding RecyclerBind;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerBind = FragmentRecyclerBinding.inflate(inflater, container, false);

        taskList = new ArrayList<>();

        taskList.add(new TaskGetter("DSA", "This is the View of DSA."));
        taskList.add(new TaskGetter("JAVA", "Java is the Object-Oriented Programming Language. If You Can Do all Thing! Choose Java"));
        taskList.add(new TaskGetter("Android Studio", "We are Creating Application with the help of Android Studio."));


        TaskAdopter taskAdopter = new TaskAdopter(taskList, getContext());
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        RecyclerBind.RecyclerView.setLayoutManager(layoutManager);
        RecyclerBind.RecyclerView.setAdapter(taskAdopter);


        return RecyclerBind.getRoot();
    }
}