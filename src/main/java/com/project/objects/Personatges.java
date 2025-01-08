package com.project.objects;

public class Personatges {
    
    private String nom, imatge, color, nom_del_videojoc;

    public Personatges(){

    }

    public Personatges(String nom, String imatge, String color, String nom_del_videojoc){
        this.nom = nom;
        this.imatge = imatge;
        this.color = color;
        this.nom_del_videojoc = nom_del_videojoc;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImatge() {
        return imatge;
    }

    public void setImatge(String imatge) {
        this.imatge = imatge;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNom_del_videojoc() {
        return nom_del_videojoc;
    }

    public void setNom_del_videojoc(String nom_del_videojoc) {
        this.nom_del_videojoc = nom_del_videojoc;
    }

    
}
