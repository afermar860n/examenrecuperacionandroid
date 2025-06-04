package com.example.examenrecuperacionandroid.Fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.examenrecuperacionandroid.BasedeDatos.DatabaseHelper;
import com.example.examenrecuperacionandroid.R;

public class BorrarFragment extends Fragment {

    private DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_borrar, container, false);

        databaseHelper = new DatabaseHelper(requireContext());

        EditText etId = view.findViewById(R.id.etId);
        Button btnBorrar = view.findViewById(R.id.btnBorrar);
        Button btnCancelar = view.findViewById(R.id.btnCancelar);

        btnBorrar.setOnClickListener(v -> {
            String idStr = etId.getText().toString();
            if (idStr.isEmpty()) {
                Toast.makeText(getContext(), "Por favor, ingresa utu ID", Toast.LENGTH_SHORT).show();
            } else {
                int id = Integer.parseInt(idStr);
                boolean success = databaseHelper.borrarAlumnoPorId(id);
                if (success) {
                    Toast.makeText(getContext(), "Alumno borrado", Toast.LENGTH_SHORT).show();
                    etId.setText(""); // Limpiar el campo después de borrar
                } else {
                    Toast.makeText(getContext(), "No se encontró un alumno con esta ID", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Navegar al InicioFragment al pulsar cancelar
        btnCancelar.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.nav_inicio); // Navega al fragmento Inicio
        });

        return view;
    }
}