package com.example.examenrecuperacionandroid.BasedeDatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "alumnos.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_ALUMNOS = "alumnos";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_MATES = "mates";
    public static final String COLUMN_LENGUA = "lengua";
    public static final String COLUMN_INFORMATICA = "informatica";
    public static final String COLUMN_INGLES = "ingles";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_ALUMNOS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOMBRE + " TEXT NOT NULL, " +
                COLUMN_MATES + " INTEGER, " +
                COLUMN_LENGUA + " INTEGER, " +
                COLUMN_INFORMATICA + " INTEGER, " +
                COLUMN_INGLES + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALUMNOS);
        onCreate(db);
    }

    // Método para insertar datos
    public boolean insertarAlumno(String nombre, int mates, int lengua, int informatica, int ingles) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.execSQL("INSERT INTO " + TABLE_ALUMNOS + " (" +
                            COLUMN_NOMBRE + ", " +
                            COLUMN_MATES + ", " +
                            COLUMN_LENGUA + ", " +
                            COLUMN_INFORMATICA + ", " +
                            COLUMN_INGLES + ") VALUES (?, ?, ?, ?, ?);",
                    new Object[]{nombre, mates, lengua, informatica, ingles});
            return true; // Inserción exitosa
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Fallo en la inserción
        } finally {
            db.close();
        }
    }

    // Método para insertar datos con un ID específico
    public boolean insertarAlumnoConId(int id, String nombre, int mates, int lengua, int informatica, int ingles) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.execSQL("INSERT INTO " + TABLE_ALUMNOS + " (" +
                            COLUMN_ID + ", " +
                            COLUMN_NOMBRE + ", " +
                            COLUMN_MATES + ", " +
                            COLUMN_LENGUA + ", " +
                            COLUMN_INFORMATICA + ", " +
                            COLUMN_INGLES + ") VALUES (?, ?, ?, ?, ?, ?);",
                    new Object[]{id, nombre, mates, lengua, informatica, ingles});
            return true; // Inserción exitosa
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Fallo en la inserción (por ejemplo, ID duplicado)
        } finally {
            db.close();
        }
    }


    // Método para borrar alumnos por ID
    public boolean borrarAlumnoPorId(int id) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            int rowsAffected = db.delete(TABLE_ALUMNOS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
            return rowsAffected > 0; // Retorna true si se eliminó al menos una fila
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }

    // Método para obtener la lista de alumnos
    public Cursor obtenerAlumnos() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_ALUMNOS, null);
    }
}
