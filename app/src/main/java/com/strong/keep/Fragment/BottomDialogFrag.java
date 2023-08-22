package com.strong.keep.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.strong.keep.databinding.DialogUpdateFragmentBinding;


public class BottomDialogFrag extends Fragment {
    DialogUpdateFragmentBinding BottomDiaBind;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        BottomDiaBind = DialogUpdateFragmentBinding.inflate(inflater, container, false);
        return BottomDiaBind.getRoot();
    }
}