package com.dev.socrates.swing.uiAdmin.inicio;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.dev.socrates.swing.Adapters.InicioAdapter;
import com.dev.socrates.swing.Adapters.StructRest;
import com.dev.socrates.swing.ApiRes.RestauranteApi;
import com.dev.socrates.swing.ApiRes.onLoadData;
import com.dev.socrates.swing.LoginActivity;
import com.dev.socrates.swing.R;
import com.dev.socrates.swing.RegisterUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class InicioFragment extends Fragment implements onLoadData {
    private InicioFragment root = this;
    private InicioViewModel inicioViewModel;
    private ListView list;

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
        list = this.getActivity().findViewById(R.id.list_restaurant);
        ArrayList<StructRest> datos = new ArrayList<>();
        RestauranteApi rest = new RestauranteApi(this);
        /*for (int i=0; i < 100; i++){
            StructRest item = new StructRest();
            item.setNombre ("Nombre" + i);
            item.setCalle("Calle numero 245" + i);
            //item.setLogo("");
            datos.add(item);
        }*/
        rest.loadRestaurant();


        Button createRest = this.getActivity().findViewById(R.id.createRest);
        createRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_crearRestFragment);
            }
        });
    }

    @Override
    public void onJsonLoad(JSONObject data) {

    }

    @Override
    public void onJsonArrayLoad(JSONArray data) {
        ArrayList<StructRest> datos = new ArrayList<>();
        for (int i = 0; i<data.length(); i++){
            StructRest item = new StructRest();
            try {
                /*if (data.getJSONObject(i).has("Nombre")) {
                    item.setNombre(data.getJSONObject(i).getString("Nombre"));
                } else {
                    item.setNombre("");
                }
                if (data.getJSONObject(i).has("Calle")) {
                    item.setCalle(data.getJSONObject(i).getString("Calle"));
                } else {
                    item.setCalle("");
                }
                if (data.getJSONObject(i).has("Logo")){
                    item.setLogo(data.getJSONObject(i).getString("Logo"));
                } else {
                    item.setLogo("");
                }*/
                item.setNombre(data.getJSONObject(i).getString("Nombre"));
                item.setCalle(data.getJSONObject(i).getString("Calle"));
                item.setLogo(data.getJSONObject(i).getString("Logo"));
                datos.add(item);

                /*Button VerMenu = this.getActivity().findViewById(R.id.menuRest);
                VerMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_menuVista);
                    }
                });*/
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        InicioAdapter adapter = new InicioAdapter(datos, this.getContext());
        list.setAdapter(adapter);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

    }
}