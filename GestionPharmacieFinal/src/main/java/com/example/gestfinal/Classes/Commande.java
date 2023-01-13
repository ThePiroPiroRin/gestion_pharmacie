package com.example.gestfinal.Classes;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class Commande {
        int id;
        String nom_pro;
        String date;
        int prix;
        int qtite;
        String nom_Fr;
        Boolean est_recu1;
        Boolean est_complet1;
        CheckBox est_recu;
        CheckBox est_complet;
        TextField qtite_recu;
        int qtite_recu1;

    public Commande(String nom_pro, int prix, int qtite, String nom_Fr) {
        this.nom_pro = nom_pro;
        this.prix = prix;
        this.qtite = qtite;
        this.nom_Fr = nom_Fr;
    }

    public Commande(Boolean est_recu1, Boolean est_complet1, TextField qtite_recu) {
        this.est_recu1 = est_recu1;
        this.est_complet1 = est_complet1;
        this.qtite_recu = qtite_recu;

    }


        public Commande(String nom_pro, int prix, int qtite, String nom_Fr, Boolean est_recu1, Boolean est_complet1, TextField qtite_recu, int id) {
            this.nom_pro = nom_pro;
            this.prix = prix;
            this.qtite = qtite;
            this.nom_Fr = nom_Fr;
            this.est_recu1 = est_recu1;
            this.est_complet1 = est_complet1;
            this.qtite_recu = qtite_recu;
            this.id = id;
        }

    public Commande(String nom_pro, int prix, int qtite, String nom_Fr, CheckBox est_recu, CheckBox est_complet, TextField qtite_recu, int id) {
        this.nom_pro = nom_pro;
        this.prix = prix;
        this.qtite = qtite;
        this.nom_Fr = nom_Fr;
        this.est_recu = est_recu;
        this.est_complet= est_complet;
        this.qtite_recu = qtite_recu;
        this.id = id;
    }

    public Commande(Boolean est_recu1, Boolean est_complet1, int qtite_recu1) {
        this.est_recu1 = est_recu1;
        this.est_complet1 = est_complet1;
        this.qtite_recu1 = qtite_recu1;
    }

    public Commande(String nomP, int prixP, int qtiteP, String nomFR, String date) {
        this.nom_pro=nomP;
        this.prix=prixP;
        this.qtite=qtiteP;
        this.nom_Fr=nomFR;
        this.date=date;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getNom_pro() {

            return nom_pro;
        }

        public void setNom(String nom_pro) {

            this.nom_pro = nom_pro;
        }

        public int getPrix() {

            return prix;
        }

        public void setPrix(int prix) {

            this.prix = prix;
        }

        public int getQtite() {

            return qtite;
        }

        public void setQtite(int qtite) {

            this.  qtite = qtite;
        }

        public String getNom_Fr() {

            return nom_Fr;
        }

        public void setNom_Fr(String nom_Fr) {

            this.nom_Fr = nom_Fr;
        }

    public Boolean getEst_recu1() {

        return est_recu1;
    }

    public void setEst_recu1(Boolean est_recu1) {

        this.est_recu1 = est_recu1;
    }

    public Boolean getEst_complet1() {

        return est_complet1;
    }

    public void setEst_complet(Boolean est_complet1) {

        this.est_complet1 = est_complet1;
    }

    public TextField getQtite_recu() {

        return qtite_recu;
    }

    public void setQtite_recu(TextField qtite_recu) {

        this.  qtite_recu = qtite_recu;
    }

    public int getQtite_recu1() {
        return qtite_recu1;
    }

    public void setQtite_recu1(int qtite_recu1) {
        this.qtite_recu1 = qtite_recu1;
    }

    public CheckBox getEst_recu() {

        return est_recu;
    }

    public void setEst_recu(CheckBox est_recu) {

        this.est_recu = est_recu;
    }

    public CheckBox getEst_complet() {

        return est_complet;
    }

    public void setEst_complet(CheckBox est_complet){
        this.est_complet = est_complet;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

