package com.dev.socrates.swing.uiAdmin.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.dev.socrates.swing.R;

public class InicioFragment extends Fragment {

    private InicioViewModel inicioViewModel;

        public View onCreateView(@NonNull LayoutInflater inflater,
                ViewGroup container, Bundle savedInstanceState) {
            inicioViewModel =
                    ViewModelProviders.of(this).get(InicioViewModel.class);
            View root = inflater.inflate(R.layout.fragment_inicio, container, false);
        /*final TextView textView = root.findViewById(R.id.text_home);
        inicioViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
            return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        Button createRest = this.getActivity().findViewById(R.id.createRest);
        createRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_crearRestFragment);
            }
        });
    }
}