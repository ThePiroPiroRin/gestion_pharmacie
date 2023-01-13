package com.example.gestfinal.Classes;

import java.util.Date;

public class Vente {
    private int id;
    private String nomMedicament;
    private int qtMedicament ;
    private int prixUnitaire;
    private int prixVente;
    private Date dateVente;

    public Date getDateVente() {
        return dateVente;
    }

    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomMedicament() {
        return nomMedicament;
    }

    public void setNomMedicament(String nomMedicament) {
        this.nomMedicament = nomMedicament;
    }

    public int getQtMedicament() {
        return qtMedicament;
    }

    public void setQtMedicament(int qtMedicament) {
        this.qtMedicament = qtMedicament;
    }

    public int getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(int prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public int getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }
}

