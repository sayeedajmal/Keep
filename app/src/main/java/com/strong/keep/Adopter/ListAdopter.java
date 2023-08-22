package com.strong.keep.Adopter;

import static com.strong.keep.Fragment.ListTaskFrag.refreshData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.strong.keep.GetSet.listTaskGetter;
import com.strong.keep.R;
import com.strong.keep.SqlHelper;

import java.util.ArrayList;
import java.util.Objects;

public class ListAdopter extends RecyclerView.Adapter<ListAdopter.RecyclerViewHolder> {

    ArrayList<listTaskGetter> taskGetter;
    Context context;
    SqlHelper sqlHelper;
    BottomSheetDialog bottomSheetDialog;

    public ListAdopter(ArrayList<listTaskGetter> taskGetter, Context context) {
        this.taskGetter = taskGetter;
        this.context = context;
    }

    @NonNull
    @Override
    public ListAdopter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_list, parent, false);
        return new ListAdopter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        listTaskGetter listGetter = this.taskGetter.get(position);
        holder.ListValue.setText(listGetter.getListValue());
        holder.itemView.setOnClickListener(e -> updateList(listGetter));
        sqlHelper = new SqlHelper(context.getApplicationContext());

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (holder.checkBox.isChecked()) {
                String ListValue = holder.ListValue.getText().toString();
                Boolean CheckDelete = sqlHelper.DeleteList("List", ListValue);
                if (CheckDelete) {
                    refreshData();
                    holder.checkBox.setChecked(false);
                    Toast.makeText(context, "List Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskGetter.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView ListValue;
        CheckBox checkBox;


        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ListValue = itemView.findViewById(R.id.listValue);
            checkBox = itemView.findViewById(R.id.checkboxDone);
        }
    }

    private void updateList(listTaskGetter listGetter) {
        bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.dialog_update_fragment);
        //Binding all Buttons
        TextInputEditText text = bottomSheetDialog.findViewById(R.id.TypeList);
        assert text != null;
        text.setText(listGetter.getListValue());
        String previousText = listGetter.getListValue();

        Button update = bottomSheetDialog.findViewById(R.id.update);
        sqlHelper = new SqlHelper(context.getApplicationContext());
        assert update != null;
        update.setOnClickListener(v -> {
            if (Objects.requireNonNull(text.getText()).length() == 0) {
                Toast.makeText(context.getApplicationContext(), "Can't Update Empty List", Toast.LENGTH_SHORT).show();
            } else {
                boolean checkUpdate = sqlHelper.UpdateList("List", previousText, text.getText().toString());
                if (checkUpdate) {
                    refreshData();
                    Toast.makeText(context.getApplicationContext(), "List Updated", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                } else
                    Toast.makeText(context.getApplicationContext(), "List Not Updated", Toast.LENGTH_SHORT).show();
            }
        });

        bottomSheetDialog.show();
        bottomSheetDialog.setOnDismissListener(dialog -> bottomSheetDialog.dismiss());
    }
}
