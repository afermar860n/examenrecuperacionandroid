package com.example.examenrecuperacionandroid.Fragmentos

import com.example.examenrecuperacionandroid.BasedeDatos.DatabaseHelper
import com.example.examenrecuperacionandroid.R

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment

class ListarFragment : Fragment() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_listar, container, false)

        databaseHelper = DatabaseHelper(requireContext())
        val listView = view.findViewById<ListView>(R.id.listView)

        // Lista de alumnos
        val alumnosList = ArrayList<HashMap<String, Any>>()

        // Obtener datos de la base de datos
        val cursor: Cursor = databaseHelper.obtenerAlumnos()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID))
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOMBRE))
                val mates = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_MATES))
                val lengua = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LENGUA))
                val informatica = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_INFORMATICA))
                val ingles = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_INGLES))
                val media = (mates + lengua + informatica + ingles) / 4

                // Crear un mapa para cada alumno
                val alumno = HashMap<String, Any>()
                alumno["id"] = "ID: $id"
                alumno["nombre"] = "Nombre: $nombre"
                alumno["media"] = "Media: $media"
                alumno["imagen"] = if (media >= 5) R.drawable.ic_aprobado else R.drawable.ic_suspenso

                alumnosList.add(alumno)
            } while (cursor.moveToNext())
        }
        cursor.close()

        // Adaptador personalizado
        val adapter = object : SimpleAdapter(
            requireContext(),
            alumnosList,
            R.layout.list_item_alumno,
            arrayOf("id", "nombre", "media", "imagen"),
            intArrayOf(R.id.tvId, R.id.tvNombre, R.id.tvMedia, R.id.ivResultado)
        ) {
            override fun setViewImage(v: ImageView, value: String?) {
                // Convertir el valor de la imagen a entero y establecer la imagen
                v.setImageResource(value?.toIntOrNull() ?: R.drawable.ic_aprobado)
            }
        }

        listView.adapter = adapter
        return view
    }
}
