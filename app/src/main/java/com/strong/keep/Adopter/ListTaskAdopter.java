package com.strong.keep.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.strong.keep.GetSet.listTaskGetter;
import com.strong.keep.R;

import java.util.ArrayList;

public class ListTask extends RecyclerView.Adapter<ListTask.RecyclerViewHolder> {

    ArrayList<listTaskGetter> taskGetter;
    Context context;
    BottomSheetDialog bottomSheetDialog;

    public ListTask(ArrayList<listTaskGetter> taskGetter, Context context) {
        this.taskGetter = taskGetter;
        this.context = context;
    }

    @NonNull
    @Override
    public ListTask.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list, parent, false);
        return new ListTask.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        listTaskGetter taskGetter = this.taskGetter.get(position);
        holder.checkBox.setChecked(taskGetter.getCheck());
        holder.TaskValue.setText(taskGetter.getTaskSummery());

        holder.itemView.setOnClickListener(e -> {
            bottomSheetDialog = new BottomSheetDialog(context);
            bottomSheetDialog.setContentView(R.layout.fragment_bottom_dialog);
            bottomSheetDialog.show();

            bottomSheetDialog.setOnDismissListener(dialog -> bottomSheetDialog.dismiss());
        });
    }

    @Override
    public int getItemCount() {
        return taskGetter.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView TaskValue;
        CheckBox checkBox;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            TaskValue = itemView.findViewById(R.id.listValue);
            checkBox = itemView.findViewById(R.id.checkboxDone);
        }
    }
}
