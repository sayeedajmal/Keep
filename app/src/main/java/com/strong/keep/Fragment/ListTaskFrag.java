package com.strong.keep.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.strong.keep.GetSet.listTaskGetter;
import com.strong.keep.databinding.FragmentRecyclerBinding;

import java.util.ArrayList;

public class ListTaskFragment extends Fragment {

    ArrayList<listTaskGetter> taskList;
    FragmentRecyclerBinding RecyclerBind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerBind = FragmentRecyclerBinding.inflate(inflater, container, false);

        taskList = new ArrayList<>();

        taskList.add(new listTaskGetter(true, "Web Development"));
        taskList.add(new listTaskGetter(false, "Dart"));
        taskList.add(new listTaskGetter(true, "C++"));
        taskList.add(new listTaskGetter(false, "JavaFx"));
        taskList.add(new listTaskGetter(true, "Android Studio"));


        com.strong.keep.Adopter.ListTask taskAdopter = new com.strong.keep.Adopter.ListTask(taskList, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        RecyclerBind.RecyclerView.setLayoutManager(layoutManager);
        RecyclerBind.RecyclerView.setAdapter(taskAdopter);
        return RecyclerBind.getRoot();
    }
}
