package com.strong.keep.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.strong.keep.Adopter.DoneTaskAdopter;
import com.strong.keep.Adopter.TaskAdopter;
import com.strong.keep.GetSet.DoneTaskGetter;
import com.strong.keep.databinding.FragmentRecyclerBinding;

import java.util.ArrayList;

public class DoneTask extends Fragment {

    ArrayList<DoneTaskGetter> taskList;
    FragmentRecyclerBinding RecyclerBind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerBind = FragmentRecyclerBinding.inflate(inflater, container, false);

        taskList = new ArrayList<>();

        taskList.add(new DoneTaskGetter("DSA"));
        taskList.add(new DoneTaskGetter("JAVA"));


        DoneTaskAdopter taskAdopter = new DoneTaskAdopter(taskList, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        RecyclerBind.RecyclerView.setLayoutManager(layoutManager);
        RecyclerBind.RecyclerView.setAdapter(taskAdopter);
        return RecyclerBind.getRoot();
    }
}
