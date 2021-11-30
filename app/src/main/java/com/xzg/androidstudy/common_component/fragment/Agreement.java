package com.xzg.androidstudy.common_component.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.xzg.androidstudy.R;

public class Agreement extends DialogFragment {

    public static void show(FragmentManager fragmentManager) {
        Agreement agreement = new Agreement();
        agreement.show(fragmentManager, "agreement");
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agreement, null);
        return view;
    }
}
