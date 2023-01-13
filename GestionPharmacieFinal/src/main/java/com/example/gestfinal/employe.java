package com.example.gestfinal;

public class employe {

    private int id;

    private String nom;

    private String prenom;

    private  String tel;


    private String fonction;
    private String passwd;
    private String username;

    public int getId() {

        return id;
    }

    public String getNom() {
        return nom;

    }

    public String getPrenom() {

        return prenom;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getTel() {
        return tel;
    }


    void setId(int id) {
        this.id = id;

    }

    void setNom(String nom) {
        this.nom = nom;
    }

    void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    void setTel(String tel) {
        this.tel = tel;
    }

    void setPasswd(String pass) {
        passwd = pass;
    }

    void setFonction(String fonc) {
        fonction = fonc;
    }
    public String getFonction() {
       return fonction ;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public employe(int id, String nom, String prenom, String tel, String passwd, String fonction, String username) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.fonction = fonction;
        this.passwd = passwd;
        this.username=username;

    }

}