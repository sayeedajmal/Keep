package com.strong.keep.Adopter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.strong.keep.GetSet.TaskGetter;
import com.strong.keep.R;
import com.strong.keep.SqlHelper;

import java.util.ArrayList;

public class TaskAdopter extends RecyclerView.Adapter<TaskAdopter.RecyclerViewHolder> {

    ArrayList<TaskGetter> taskGetter;
    Context context;
    SqlHelper sqlHelper;

    public TaskAdopter(ArrayList<TaskGetter> taskGetter, Context context) {
        this.taskGetter = taskGetter;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task, parent, false);
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
            return false;
        });

        holder.itemView.setOnClickListener(v -> {
            updateTask(taskGetter);
        });
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        holder.deleteTask.setOnClickListener(v -> {
            dialog.setTitle("Delete Task?");
            dialog.setCancelable(true);
            dialog.setMessage("Do You Want to Delete Task?");
            dialog.setPositiveButton("Delete", (dialog1, which) -> {
                String TaskValue = holder.TaskValue.getText().toString();
                Boolean CheckDelete = sqlHelper.DeleteTask("Task", TaskValue);
                TextView text = holder.layout.findViewById(R.id.message);
                if (CheckDelete) {
                    Toast toast = new Toast(context);
                    text.setText("Deleted..");
                    toast.setGravity(Gravity.BOTTOM, 0, 10);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(holder.layout);
                    toast.show();
                }
                dialog1.dismiss();
            });
            dialog.setNegativeButton("No", (dialog2, which) -> dialog2.cancel());
            AlertDialog alert = dialog.create();
            alert.show();
        });

    }

    @Override
    public int getItemCount() {
        return taskGetter.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        LayoutInflater inflater = LayoutInflater.from(itemView.getContext());
        View layout = inflater.inflate(R.layout.custom_toast, itemView.findViewById(R.id.custom_toast_layout));
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
      /*  TextInputEditText value=bottomSheetDialog.findViewById(R.id.TaskValue);
        assert value != null;
        String TaskName=taskGetter.getTaskName();

        value.setText(taskGetter.getTaskSummery());

        Button update=bottomSheetDialog.findViewById(R.id.update);
        sqlHelper=new SqlHelper(context.getApplicationContext());
        assert update != null;
        update.setOnClickListener(v->{
            if (Objects.requireNonNull(value.getText()).length()==0){
                Toast.makeText(context.getApplicationContext(), "Can't Update Empty List", Toast.LENGTH_SHORT).show();
            }else{
                boolean checkUpdate=sqlHelper.UpdateTask("Task",TaskName, value.getText().toString());
                if (checkUpdate){
                    Toast.makeText(context.getApplicationContext(), "Task Updated", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                }else
                    Toast.makeText(context.getApplicationContext(), "Task Not Updated", Toast.LENGTH_SHORT).show();
            }
        });
        */
        Toast.makeText(context.getApplicationContext(), "Update Feature is Coming", Toast.LENGTH_SHORT).show();
    }
}
