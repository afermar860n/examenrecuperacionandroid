package com.example.examenrecuperacionandroid.Fragmentos;


import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.examenrecuperacionandroid.BasedeDatos.DatabaseHelper;
import com.example.examenrecuperacionandroid.Miscelanea.PanelAdapter;
import com.example.examenrecuperacionandroid.R;

import java.util.ArrayList;
import java.util.HashMap;

public class PanelFragment extends Fragment {

    private DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_panel, container, false);

        databaseHelper = new DatabaseHelper(requireContext());
        GridView gridView = view.findViewById(R.id.gridView);

        ArrayList<HashMap<String, Object>> alumnosList = new ArrayList<>();
        Cursor cursor = databaseHelper.obtenerAlumnos();

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOMBRE));
                int mates = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_MATES));
                int lengua = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LENGUA));
                int informatica = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_INFORMATICA));
                int ingles = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_INGLES));
                int media = (mates + lengua + informatica + ingles) / 4;

                HashMap<String, Object> alumno = new HashMap<>();
                alumno.put("id", "ID: " + id);
                alumno.put("nombre", "Nombre: " + nombre);
                alumno.put("media", "Media: " + media);
                alumno.put("imagen", media >= 5 ? R.drawable.ic_aprobado: R.drawable.ic_suspenso);

                alumnosList.add(alumno);
            } while (cursor.moveToNext());
        }
        cursor.close();

        PanelAdapter adapter = new PanelAdapter(requireContext(), alumnosList);
        gridView.setAdapter(adapter);

        return view;
    }
}

