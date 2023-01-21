package com.strong.keep.Fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.strong.keep.Adopter.ListAdopter;
import com.strong.keep.GetSet.listTaskGetter;
import com.strong.keep.SqlHelper;
import com.strong.keep.databinding.FragmentRecyclerBinding;

import java.util.ArrayList;

public class ListTaskFrag extends Fragment {

    ArrayList<listTaskGetter> taskList;
    SqlHelper sqlHelper;
    FragmentRecyclerBinding RecyclerBind;

    @SuppressLint("NotifyDataSetChanged")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerBind = FragmentRecyclerBinding.inflate(inflater, container, false);

        taskList = new ArrayList<>();
        sqlHelper = new SqlHelper(getContext());

        /*taskList.add(new listTaskGetter("Web Development"));*/

        Cursor res = sqlHelper.SHOW("List");
        if (res.getCount() > 0) {
            taskList.clear();
            while (res.moveToNext()) {
                taskList.add(new listTaskGetter(res.getString(0)));
            }
        }
        ListAdopter taskAdopter = new ListAdopter(taskList, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerBind.RecyclerView.setLayoutManager(layoutManager);
        RecyclerBind.RecyclerView.setAdapter(taskAdopter);

        RecyclerBind.swipeRefresh.setOnRefreshListener(() -> {
            taskList.clear();
            Cursor res1 = sqlHelper.SHOW("List");
            if (res1.getCount() > 0) {
                while (res1.moveToNext()) {
                    taskList.add(new listTaskGetter(res1.getString(0)));
                }
                taskAdopter.notifyDataSetChanged();
                RecyclerBind.RecyclerView.refreshDrawableState();
            }
            taskAdopter.notifyDataSetChanged();
            RecyclerBind.RecyclerView.refreshDrawableState();
            RecyclerBind.swipeRefresh.setRefreshing(false);
        });
        return RecyclerBind.getRoot();
    }
}
