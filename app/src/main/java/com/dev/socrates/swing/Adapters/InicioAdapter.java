package com.dev.socrates.swing.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.dev.socrates.swing.AdapterMenus.StructMenus;
import com.dev.socrates.swing.R;

import java.util.ArrayList;

public class InicioAdapter extends BaseAdapter {
    private ArrayList<StructRest> LISTDATA;
    private Context context;
    public InicioAdapter(ArrayList<StructRest> data, Context context) {
        LISTDATA = data;
        this.context = context;
    }
    @Override
    public int getCount() {
        return LISTDATA.size();
    }

    @Override
    public Object getItem(int position) {
        return LISTDATA.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_restaurante, null);
            TextView nombre = convertView.findViewById(R.id.nombreRest);
            nombre.setText(LISTDATA.get(position).getNombre());
            TextView direccion = convertView.findViewById(R.id.direccion);
            direccion.setText(LISTDATA.get(position).getCalle());
            ImageView imagen = convertView.findViewById(R.id.imagenRest);
            Glide.with(context)
                    .load(LISTDATA.get(position).getLogo())
                    .into(imagen);
            Button menu = convertView.findViewById(R.id.menuRest);
            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_menuVista);
                }
            });
        }
        return convertView;
    }
}
