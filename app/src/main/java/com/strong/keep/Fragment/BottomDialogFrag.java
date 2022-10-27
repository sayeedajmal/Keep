package com.strong.keep.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.strong.keep.R;
import com.strong.keep.databinding.FragmentBottomDialogBinding;

import java.util.Objects;


public class BottomDialogFrag extends Fragment {
    FragmentBottomDialogBinding BottomDiaBind;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        BottomDiaBind = FragmentBottomDialogBinding.inflate(inflater, container, false);
        return BottomDiaBind.getRoot();
    }
}