package com.strong.keep.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.strong.keep.GetSet.TaskGetter;
import com.strong.keep.R;

import java.util.ArrayList;

public class TaskAdopter extends RecyclerView.Adapter<TaskAdopter.RecyclerViewHolder> {

    ArrayList<TaskGetter> taskGetter;
    Context context;

    public TaskAdopter(ArrayList<TaskGetter> taskGetter, Context context) {
        this.taskGetter = taskGetter;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_task_list, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        TaskGetter taskGetter = this.taskGetter.get(position);
        holder.TaskName.setText(taskGetter.getTaskName());
        holder.TaskSummery.setText(taskGetter.getTaskSummery());
        holder.itemView.setOnLongClickListener(v -> {
            Toast.makeText(context.getApplicationContext(), taskGetter.getTaskName(), Toast.LENGTH_SHORT).show();
            return false;
        });


    }

    @Override
    public int getItemCount() {
        return taskGetter.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView TaskName;
        TextView TaskSummery;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            TaskName = itemView.findViewById(R.id.TaskName);
            TaskSummery = itemView.findViewById(R.id.TaskSummery);

        }
    }
}
