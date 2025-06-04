package com.example.examenrecuperacionandroid.Fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.examenrecuperacionandroid.BasedeDatos.DatabaseHelper
import com.example.examenrecuperacionandroid.R

class IntroducirFragment : Fragment() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_introducir, container, false)

        databaseHelper = DatabaseHelper(requireContext())

        val etId = view.findViewById<EditText>(R.id.etId)
        val etNombre = view.findViewById<EditText>(R.id.etNombre)
        val etMates = view.findViewById<EditText>(R.id.etMates)
        val etLengua = view.findViewById<EditText>(R.id.etLengua)
        val etInformatica = view.findViewById<EditText>(R.id.etInformatica)
        val etIngles = view.findViewById<EditText>(R.id.etIngles)

        val btnInsertar = view.findViewById<Button>(R.id.btnInsertar)
        val btnCancelar = view.findViewById<Button>(R.id.btnCancelar)

        btnInsertar.setOnClickListener {
            val id = etId.text.toString().toIntOrNull()
            val nombre = etNombre.text.toString()
            val mates = etMates.text.toString().toIntOrNull()
            val lengua = etLengua.text.toString().toIntOrNull()
            val informatica = etInformatica.text.toString().toIntOrNull()
            val ingles = etIngles.text.toString().toIntOrNull()

            // Validar que todos los campos estén completos
            if (id != null && nombre.isNotEmpty() && mates != null && lengua != null && informatica != null && ingles != null) {
                val success = databaseHelper.insertarAlumnoConId(id, nombre, mates, lengua, informatica, ingles)
                if (success) {
                    Toast.makeText(context, "Alumno insertado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Error al insertar el alumno.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Navegar al InicioFragment al pulsar cancelar
        btnCancelar.setOnClickListener {
            findNavController().navigate(R.id.nav_inicio) // Navega al fragmento Inicio
        }

        return view
    }
}