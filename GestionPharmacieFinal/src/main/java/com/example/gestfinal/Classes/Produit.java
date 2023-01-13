package com.example.gestfinal.Classes;
public class Produit {
    private String nom;
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}

    private int prix;
    public int getPrix() {return prix;}
    public void setPrix(int prix) {this.prix = prix;}

    private int qte;
    public int getQte() {return qte;}
    public void setQte(int qte) {this.qte = qte;}

    private String description;
    public String getDescription() {return description;}
    public void setDescription(String nom) {this.description = description;}

    public Produit() {
    }

    public Produit(String nom, int prix, int qte,String description) {
        this.nom = nom;
        this.prix = prix;
        this.qte = qte;
        this.description=description;
    }
}
