package com.dev.socrates.swing.uiAdmin.inicio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dev.socrates.swing.AdapterMenus.MenuAdapter;
import com.dev.socrates.swing.AdapterMenus.StructMenus;
import com.dev.socrates.swing.ApiResMenu.MenuApi;
import com.dev.socrates.swing.ApiResMenu.onLoad;
import com.dev.socrates.swing.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuVista#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuVista extends Fragment implements onLoad {
    private ListView list;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuVista() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuVista.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuVista newInstance(String param1, String param2) {
        MenuVista fragment = new MenuVista();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_vista, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        list = this.getActivity().findViewById(R.id.list_menu);
        ArrayList<StructMenus> datos = new ArrayList<>();
        MenuApi menu = new MenuApi(this);
        menu.loadMenu();
    }

    @Override
    public void onJsonLoad(JSONObject data) {

    }

    @Override
    public void onJsonArrayLoad(JSONArray data) {
        ArrayList<StructMenus> datos = new ArrayList<>();
        for (int i = 0; i<data.length(); i++){
            StructMenus item = new StructMenus();
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
                item.setDescripción(data.getJSONObject(i).getString("Descripcion"));
                item.setPrecio(data.getJSONObject(i).getString("Precio"));
                item.setFotoproducto(data.getJSONObject(i).getString("Fotoproducto"));
                datos.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        MenuAdapter adapter = new MenuAdapter(datos, getContext());
        list.setAdapter(adapter);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

    }
}