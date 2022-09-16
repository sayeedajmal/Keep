package com.strong.keep.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.strong.keep.GetSet.DoneTaskGetter;
import com.strong.keep.R;

import java.util.ArrayList;

public class DoneTaskAdopter extends RecyclerView.Adapter<DoneTaskAdopter.RecyclerViewHolder> {

    ArrayList<DoneTaskGetter> taskGetter;
    Context context;

    public DoneTaskAdopter(ArrayList<DoneTaskGetter> taskGetter, Context context) {
        this.taskGetter = taskGetter;
        this.context = context;
    }

    @NonNull
    @Override
    public DoneTaskAdopter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_don_task, parent, false);
        return new DoneTaskAdopter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoneTaskAdopter.RecyclerViewHolder holder, int position) {
        DoneTaskGetter taskGetter = this.taskGetter.get(position);
        holder.TaskName.setText(taskGetter.getTaskSummery());
    }

    @Override
    public int getItemCount() {
        return taskGetter.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView TaskName;
        CheckBox checkBox;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            TaskName = itemView.findViewById(R.id.TaskNameDone);
            checkBox = itemView.findViewById(R.id.checkboxDone);

        }
    }
}
