package com.example.gestfinal.Controllers;

import com.example.gestfinal.Classes.Produit;
import com.example.gestfinal.Classes.Vente;

import com.example.gestfinal.ConnexionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class VenteController implements Initializable {
    Connection con = null; // Connection une classe permet de faire la connexion ou  le lien de connection
    PreparedStatement st = null;
    ResultSet rs = null;
    Connection con2=null;
    PreparedStatement st2 = null;
    ResultSet rs2 = null;
    PreparedStatement st3 = null;
    ResultSet rs3 = null;


    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btncheck;

    @FXML
    private Button btnUpdate;
    @FXML
    private TableColumn<Vente, Integer> colId;

    @FXML
    private TableColumn<Vente, String> colnMedicament;

    @FXML
    private TableColumn<Vente, Integer> colpUnitaire;

    @FXML
    private TableColumn<Vente, Integer> colpVente;

    @FXML
    private TableColumn<Vente, Integer> colqMedicament;

    @FXML
    private TableColumn<Vente,String> colDate;


    @FXML
    private TableView<Vente> table; // la table stock depuis la classe Vente
    int id=0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTabventes();
    }
    public ObservableList<Vente> getTabVentes(){ // methode pour extraire des données de base de donnée vente
        ObservableList<Vente> tabvente = FXCollections.observableArrayList();
        String query="select *from vente";
        con = ConnexionDB.getConnection(); // (puisque la methode getConn est static alors je peux l'appeler que par le nom de la classe DBConnection) LA CRÉATION DE LIEN PAR LA METHODE getConn()
        try {
            st = con.prepareStatement(query);// la methode prepareStatement(query) permet d'envoyer query  sql a database a travers le lien de connexion con
            rs= st.executeQuery();//rs stock l'execution du query dans ce cas la table tabvente
            while (rs.next()){ //parcourir la table de vente ENREGISTREMENT PAR ENREGISTREMENT
                Vente st = new Vente(); // un objet vente
                st.setId(rs.getInt("id"));// attribuer l'élément id d'un enregistrement de tabvente stcker dans rs  dans l'élément dans l'élément id de st
                st.setNomMedicament(rs.getString("NomMedicament"));//!!!!! atttenntion aux espacepour des nommmmmmmssssssssssssssssssss
                st.setQtMedicament(rs.getInt("QtMedicament"));
                st.setPrixUnitaire(rs.getInt("PrixUnitaire"));
                st.setPrixVente(rs.getInt("PrixVente"));
                st.setDateVente(rs.getDate("LaDate"));
                tabvente.add(st); // ajouter l'élément dans une liste tabvente


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tabvente;
    }
    public void showTabventes() { // permet d'afficher les donnes extraites de la base de donnee dans la table
        ObservableList<Vente> list = getTabVentes();
        table.setItems(list);
        colId.setCellValueFactory(new PropertyValueFactory<Vente, Integer>("id")); // id est l'attribue de la classe Vente
        colnMedicament.setCellValueFactory(new PropertyValueFactory<Vente, String>("nomMedicament"));
        colqMedicament.setCellValueFactory(new PropertyValueFactory<Vente,Integer>("qtMedicament"));
        colpUnitaire.setCellValueFactory(new PropertyValueFactory<Vente,Integer>("prixUnitaire"));
        colpVente.setCellValueFactory(new PropertyValueFactory<Vente,Integer>("prixVente"));
        colDate.setCellValueFactory(new PropertyValueFactory<Vente, String>("dateVente") );


    }
    @FXML
    private TextField tnommedicament;

    @FXML
    private TextField tprixunitaire;


    @FXML
    private TextField tquantite;
    @FXML
    private TextField tdate;




    int pqt=0;
    int prixvente=0;
    int Qt=0;// quantité qui va être stockée après vente
    int QtStock=0;// quantité disponible en stock

    @FXML
    void clearMedicament(ActionEvent event) {
        clear();


    }
    void clear(){
        tnommedicament.setText(null);
        tquantite.setText(null);
        tprixunitaire.setText(null);
        tdate.setText(null);
        btnSave.setDisable(false);
    }


    @FXML
    void deleteMedicament(ActionEvent event) {
        String delete="delete from vente where id=?";
        con = ConnexionDB.getConnection(); //le lien de connexion
        try {
            st =con.prepareStatement(delete);// envoyer la query sql a travers le lien de la connexion
            st.setInt(1,id);// pour completer la query on utilise setInt()
            st.executeUpdate();
            showTabventes();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void saveMedicament(ActionEvent event) {
        String insert = "insert into vente(NomMedicament,QtMedicament, PrixUnitaire, PrixVente,LaDate) values(?,?,?,?,?)";
        String sqlQtStock="SELECT qte FROM produits WHERE nom=?";
        String updateStock = "update produits set qte=? where nom=?";
        con=ConnexionDB.getConnection();
        con2=ConnexionDB.getConnection();
        try {
            st= con.prepareStatement(insert);
            st.setString(1,tnommedicament.getText());
            st.setInt(2, Integer.parseInt(tquantite.getText()));
            st.setInt(3, Integer.parseInt(tprixunitaire.getText()));
            pqt=(Integer.parseInt(tprixunitaire.getText()))*(Integer.parseInt(tquantite.getText()));
            prixvente = (int) (pqt+(pqt*0.4));
            st.setInt(4, Integer.parseInt(String.valueOf(prixvente)));
            st.setDate(5, Date.valueOf(tdate.getText()));// attention il faut respecter la notation de 0000-00-00 si non une erreur sera generer
            st.executeUpdate();
            showTabventes();
            // enregistrer la quantité disponible en stock dans un var QtStock
            st3=con2.prepareStatement(sqlQtStock);
            st3.setString(1,tnommedicament.getText());
            rs3= st3.executeQuery();//rs stock l'execution du query dans ce cas la table tabvente
            while (rs3.next()){
                QtStock =rs3.getInt("qte");
            }

            st2= con2.prepareStatement(updateStock);
            Qt= QtStock-(Integer.parseInt(tquantite.getText()));
            st2.setInt(1, Qt);
            st2.setString(2,tnommedicament.getText());
            st2.executeUpdate();

            clear();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void updateMedicament(ActionEvent event) {
        String update = "update vente set NomMedicament=?, QtMedicament=?, PrixUnitaire=?, PrixVente=?, LaDate=? where id =?";
        con = ConnexionDB.getConnection();
        try {
            st = con.prepareStatement(update);
            st.setString(1, tnommedicament.getText());
            st.setInt(2, Integer.parseInt(tquantite.getText()));
            st.setInt(3, Integer.parseInt(tprixunitaire.getText()));
            pqt=(Integer.parseInt(tprixunitaire.getText()))*(Integer.parseInt(tquantite.getText()));
            prixvente = (int) (pqt+(pqt*0.4));
            st.setInt(4, Integer.parseInt(String.valueOf(prixvente)));
            st.setInt(5, id); // il faut declarer id
            st.setDate(6, Date.valueOf(tdate.getText()));// attention il faut respecter la notation de 0000-00-00 si non une erreur sera generer
            st.executeUpdate();
            showTabventes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void getData(MouseEvent event) {
        Vente vente = table.getSelectionModel().getSelectedItem(); // affecter les éléments d'une vente a l'objet vente à partir de la table
        id=vente.getId();
        tnommedicament.setText(vente.getNomMedicament());
        tquantite.setText(String.valueOf(vente.getQtMedicament()));
        tprixunitaire.setText(String.valueOf(vente.getPrixUnitaire()));
        tdate.setText(String.valueOf(vente.getDateVente()));

        btnSave.setDisable(true);// désactiver le bouton save quand mouse is clicked





    }
    @FXML
    void checkproduit(ActionEvent event) {
        String sqlStock = "select * from produits where  nom=?";
        con2 = ConnexionDB.getConnection();
        try {
            st2 = con2.prepareStatement(sqlStock);
            st2.setString(1, tnommedicament.getText());
            rs2= st2.executeQuery();//rs stock l'execution du query dans ce cas la table tabvente
            Produit stk = new Produit();
            while (rs2.next()){ //parcourir la table de vente ENREGISTREMENT PAR ENREGISTREMENT
                stk.setNom(rs2.getString("nom"));//!!!!! atttenntion aux espacepour des nommmmmmmssssssssssssssssssss
                stk.setQte(rs2.getInt("qte"));
                stk.setPrix(rs2.getInt("prix"));// PrixProduit c'est le prix unitaire du produit qui est stoker dans ka table du stock
            }
            tnommedicament.setText(stk.getNom());
            tquantite.setText(String.valueOf(stk. getQte()));
            tprixunitaire.setText(String.valueOf(stk.getPrix()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





}