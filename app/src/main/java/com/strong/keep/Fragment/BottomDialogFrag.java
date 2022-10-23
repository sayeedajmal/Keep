package com.strong.keep.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.strong.keep.databinding.FragmentBottomDialogBinding;


public class BottomDialogFrag extends Fragment {
    FragmentBottomDialogBinding BottomDiaBind;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        BottomDiaBind = FragmentBottomDialogBinding.inflate(inflater, container, false);

        BottomDiaBind.setReminder.setOnClickListener(v -> BottomDiaBind.datePicker.setVisibility(View.VISIBLE));
        return BottomDiaBind.getRoot();
    }
}