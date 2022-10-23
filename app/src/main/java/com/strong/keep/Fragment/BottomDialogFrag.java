package com.strong.keep.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.strong.keep.GetSet.listTaskGetter;
import com.strong.keep.databinding.FragmentBottomDialogBinding;

import java.util.ArrayList;

public class BottomDialog extends Fragment {
    FragmentBottomDialogBinding BottomDiaBind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        BottomDiaBind = FragmentBottomDialogBinding.inflate(inflater, container, false);

        return BottomDiaBind.getRoot();
    }
}