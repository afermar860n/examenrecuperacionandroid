package com.example.examenrecuperacionandroid.Miscelanea;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.examenrecuperacionandroid.R;

import java.util.ArrayList;
import java.util.HashMap;

public class PanelAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<HashMap<String, Object>> alumnosList;

    public PanelAdapter(Context context, ArrayList<HashMap<String, Object>> alumnosList) {
        this.context = context;
        this.alumnosList = alumnosList;
    }

    @Override
    public int getCount() {
        return alumnosList.size();
    }

    @Override
    public Object getItem(int position) {
        return alumnosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item_alumno, parent, false);
        }

        HashMap<String, Object> alumno = alumnosList.get(position);

        TextView tvId = convertView.findViewById(R.id.tvId);
        TextView tvNombre = convertView.findViewById(R.id.tvNombre);
        TextView tvMedia = convertView.findViewById(R.id.tvMedia);
        ImageView ivResultado = convertView.findViewById(R.id.ivResultado);

        tvId.setText((String) alumno.get("id"));
        tvNombre.setText((String) alumno.get("nombre"));
        tvMedia.setText((String) alumno.get("media"));
        ivResultado.setImageResource((int) alumno.get("imagen"));

        return convertView;
    }
}
