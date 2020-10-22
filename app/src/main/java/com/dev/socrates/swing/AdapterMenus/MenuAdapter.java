package com.dev.socrates.swing.AdapterMenus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.bumptech.glide.Glide;
import com.dev.socrates.swing.R;

import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter {
    private  ArrayList<StructMenus> LISTDATA;
    private Context context;
    public MenuAdapter(ArrayList<StructMenus> data, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_menu, null);
            TextView nombrePlato = convertView.findViewById(R.id.nombrePlato);
            nombrePlato.setText(LISTDATA.get(position).getNombre());
            TextView descripcion = convertView.findViewById(R.id.descripcionPlato);
            descripcion.setText(LISTDATA.get(position).getDescripción());
            TextView precio = convertView.findViewById(R.id.precioPlato);
            precio.setText(LISTDATA.get(position).getPrecio());
            ImageView imagen = convertView.findViewById(R.id.imagenMenu);
            Glide.with(context)
                    .load(LISTDATA.get(position).getFotoproducto())
                    .into(imagen);
        }
        return convertView;
    }
}
