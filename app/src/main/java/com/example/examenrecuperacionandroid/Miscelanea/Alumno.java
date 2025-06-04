package com.example.examenrecuperacionandroid.Miscelanea;

public class Alumno {
    private int id;
    private String nombre;
    private float mates;
    private float lengua;
    private float informatica;
    private float ingles;

    // Constructor
    public Alumno(int id, String nombre, float mates, float lengua, float informatica, float ingles) {
        this.id = id;
        this.nombre = nombre;
        this.mates = mates;
        this.lengua = lengua;
        this.informatica = informatica;
        this.ingles = ingles;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getMates() {
        return mates;
    }

    public void setMates(float mates) {
        this.mates = mates;
    }

    public float getLengua() {
        return lengua;
    }

    public void setLengua(float lengua) {
        this.lengua = lengua;
    }

    public float getInformatica() {
        return informatica;
    }

    public void setInformatica(float informatica) {
        this.informatica = informatica;
    }

    public float getIngles() {
        return ingles;
    }

    public void setIngles(float ingles) {
        this.ingles = ingles;
    }

    public float calcularMedia() {
        return (mates + lengua + informatica + ingles) / 4;
    }
}
