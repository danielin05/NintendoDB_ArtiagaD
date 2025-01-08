package com.project.objects;

public class Consoles {
    
    private String nom, data, procesador, color, imatge;
    private int venudes;

    public Consoles(){

    }

    public Consoles(String nom, String data, String procesador, String color, int venudes, String imatge) {
        this.nom = nom;
        this.data = data;
        this.procesador = procesador;
        this.color = color;
        this.venudes = venudes;
        this.imatge = imatge;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getVenudes() {
        return venudes;
    }

    public void setVenudes(int venudes) {
        this.venudes = venudes;
    }

    public String getImatge() {
        return imatge;
    }

    public void setImatge(String imatge) {
        this.imatge = imatge;
    }

    
}
