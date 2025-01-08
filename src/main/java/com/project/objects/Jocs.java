package com.project.objects;

public class Jocs {
    
    private String nom, tipus, descripcio, imatge;
    private int any;

    public Jocs(){

    }

    public Jocs (String nom, int any, String tipus, String descripcio, String imatge){
        this.nom = nom;
        this.any = any;
        this.tipus = tipus;
        this.descripcio = descripcio;
        this.imatge = imatge;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAny() {
        return any;
    }

    public void setAny(int any) {
        this.any = any;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getImatge() {
        return imatge;
    }

    public void setImatge(String imatge) {
        this.imatge = imatge;
    }

    

}
