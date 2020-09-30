package com.dev.socrates.swing.uiAdmin.ordenes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.dev.socrates.swing.R;

public class OrdenesFragment extends Fragment {

    private OrdenesViewModel ordenesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ordenesViewModel =
                ViewModelProviders.of(this).get(OrdenesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ordenes, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        ordenesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}