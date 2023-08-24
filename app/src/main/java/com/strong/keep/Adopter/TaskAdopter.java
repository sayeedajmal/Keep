package com.strong.keep.Adopter;

import static com.strong.keep.Fragment.TaskFrag.refreshData;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
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
            showMenu(v, taskGetter.getTaskSummery(), holder.TaskValue.getText().toString());
            return true;
        });

        holder.itemView.setOnClickListener(v -> updateTask(taskGetter));

    }

    @SuppressLint("NonConstantResourceId")
    private void showMenu(View view, String TaskValue, String TaskName) {
        PopupMenu menu = new PopupMenu(context, view);
        MenuInflater inflater = menu.getMenuInflater();

        inflater.inflate(R.menu.options, menu.getMenu());
        menu.show();

        menu.setOnMenuItemClickListener(menuItem1 -> {
            switch (menuItem1.getItemId()) {
                case R.id.copyText:
                    copyTask(TaskValue);
                    Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.deleteTask:
                    DeleteTask(TaskName);
                    return true;
                default:
                    return false;
            }
        });
    }

    private void copyTask(String task) {
        ClipboardManager manager = context.getSystemService(ClipboardManager.class);
        ClipData data = ClipData.newPlainText("Keep", task);
        manager.setPrimaryClip(data);

    }

    private void DeleteTask(String TaskValue) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Delete Task?");
        dialog.setCancelable(true);
        dialog.setMessage("Do You Want to Delete Task?");
        dialog.setPositiveButton("Delete", (dialog1, which) -> {
            Boolean CheckDelete = sqlHelper.DeleteTask("Task", TaskValue);
            if (CheckDelete) Toast.makeText(context, "Task Deleted", Toast.LENGTH_SHORT).show();
            refreshData();
            dialog1.dismiss();
        });
        dialog.setNegativeButton("No", (dialog2, which) -> dialog2.cancel());
        AlertDialog alert = dialog.create();
        alert.show();
    }

    @Override
    public int getItemCount() {
        return taskGetter.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView TaskName;
        TextView TaskValue;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            TaskName = itemView.findViewById(R.id.TaskName);
            TaskValue = itemView.findViewById(R.id.TaskValue);
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
