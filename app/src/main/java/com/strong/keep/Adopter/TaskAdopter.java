package com.strong.keep.Adopter;

import static com.strong.keep.Fragment.TaskFrag.refreshData;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.strong.keep.GetSet.TaskGetter;
import com.strong.keep.R;
import com.strong.keep.SqlHelper;

import java.util.ArrayList;
import java.util.Objects;

public class TaskAdopter extends RecyclerView.Adapter<TaskAdopter.RecyclerViewHolder> {

    ArrayList<TaskGetter> taskGetter;
    Context context;
    SqlHelper sqlHelper;
    BottomSheetDialog bottomSheetDialog;

    public TaskAdopter(ArrayList<TaskGetter> taskGetter, Context context) {
        this.taskGetter = taskGetter;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_task, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        sqlHelper = new SqlHelper(context.getApplicationContext());
        TaskGetter taskGetter = this.taskGetter.get(position);
        holder.TaskName.setText(taskGetter.getTaskName());
        holder.TaskValue.setText(taskGetter.getTaskSummery());

        holder.itemView.setOnLongClickListener(v -> {
            holder.deleteTask.setVisibility(View.VISIBLE);
            return true;
        });

        holder.itemView.setOnClickListener(v -> updateTask(taskGetter));

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);

        holder.deleteTask.setOnClickListener(v -> {
            dialog.setTitle("Delete Task?");
            dialog.setCancelable(true);
            dialog.setMessage("Do You Want to Delete Task?");
            dialog.setPositiveButton("Delete", (dialog1, which) -> {
                String TaskValue = holder.TaskValue.getText().toString();
                Boolean CheckDelete = sqlHelper.DeleteTask("Task", TaskValue);
                if (CheckDelete) {
                    refreshData();
                    Toast.makeText(context, "Task Deleted", Toast.LENGTH_SHORT).show();
                }
                dialog1.dismiss();
            });
            dialog.setNegativeButton("No", (dialog2, which) -> dialog2.cancel());
            AlertDialog alert = dialog.create();
            holder.deleteTask.setVisibility(View.INVISIBLE);
            alert.show();
        });

    }

    @Override
    public int getItemCount() {
        return taskGetter.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView TaskName;
        TextView TaskValue;
        ImageButton deleteTask;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            TaskName = itemView.findViewById(R.id.TaskName);
            TaskValue = itemView.findViewById(R.id.TaskValue);
            deleteTask = itemView.findViewById(R.id.deleteTask);
        }
    }

    private void updateTask(TaskGetter taskGetter) {
        bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.dialog_update_fragment);
        String TaskSummery = taskGetter.getTaskSummery();
        TextInputEditText newValue = bottomSheetDialog.findViewById(R.id.TypeList);
        assert newValue != null;
        newValue.setText(TaskSummery);

        Button update = bottomSheetDialog.findViewById(R.id.update);
        sqlHelper = new SqlHelper(context.getApplicationContext());

        assert update != null;
        update.setOnClickListener(v -> {
            if (Objects.requireNonNull(newValue.getText()).length() == 0) {
                Toast.makeText(context.getApplicationContext(), "Can't Update Empty List", Toast.LENGTH_SHORT).show();
            } else {
                boolean checkUpdate = sqlHelper.UpdateTask("Task", TaskSummery, newValue.getText().toString());
                if (checkUpdate) {
                    refreshData();
                    Toast.makeText(context.getApplicationContext(), "Task Updated", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                } else
                    Toast.makeText(context.getApplicationContext(), "Task Not Updated", Toast.LENGTH_SHORT).show();
            }
        });
        bottomSheetDialog.show();
        bottomSheetDialog.setOnDismissListener(dialog -> bottomSheetDialog.dismiss());
    }
}
