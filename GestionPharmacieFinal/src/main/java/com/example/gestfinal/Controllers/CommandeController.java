package com.example.gestfinal.Controllers;

//import com.example.pharmacieapp.HelloApplication;

import com.example.gestfinal.Classes.Commande;
import com.example.gestfinal.ConnexionDB;
import com.example.gestfinal.Dao.CommandeDAO;
import com.example.gestfinal.Dao.CommandeDAOimpl;
import com.mysql.cj.protocol.Resultset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;



public class CommandeController implements Initializable {
    int QtStock = 0;

    @FXML
    private TextField nom_pro;

    @FXML
    private TextField prix;

    @FXML
    private TextField qtite;

    @FXML
    private TextField nom_Fr;
    @FXML
    private TextField LaDate;

    @FXML
    private Label messg;

    @FXML
    private TableColumn<Commande, String> colnom_pro;

    @FXML
    private TableColumn<Commande, Integer> colprix;

    @FXML
    private TableColumn<Commande, Integer> colqtite;

    @FXML
    private TableColumn<Commande, String> colnom_Fr;

    @FXML
    private TableColumn<Commande, CheckBox> colest_recu;

    @FXML
    private TableColumn<Commande, CheckBox> colest_complet;

    @FXML
    private TableColumn<Commande, TextField> colqtite_recu;
    @FXML
    private TableColumn<Commande, Integer> colid;
    @FXML
    private TableView<Commande> table;





    @FXML
    void sendCommande(ActionEvent event) {
        String nomP = nom_pro.getText();
        int prixP = Integer.parseInt(prix.getText());
        int qtiteP = Integer.parseInt(qtite.getText());
        String nomFR = nom_Fr.getText();
        String date=LaDate.getText();

        messg.setText("Commande "+nom_pro.getText()+" saved");

        CommandeDAO cmdDAO = new CommandeDAOimpl();
        Commande cmd = new Commande(nomP,prixP,qtiteP,nomFR,date);
        cmdDAO.save(cmd);
        nom_pro.setText("");
        prix.setText("");
        qtite.setText("");
        nom_Fr.setText("");
        LaDate.setText("");
        try {
            initializeTableview();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        colnom_pro.setCellValueFactory(new PropertyValueFactory<>("nom_pro"));
        colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colqtite.setCellValueFactory(new PropertyValueFactory<>("qtite"));
        colnom_Fr.setCellValueFactory(new PropertyValueFactory<>("nom_Fr"));
        colest_recu.setCellValueFactory(new PropertyValueFactory<>("est_recu"));
        colest_complet.setCellValueFactory(new PropertyValueFactory<>("est_complet"));
        colqtite_recu.setCellValueFactory(new PropertyValueFactory<>("qtite_recu"));
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));

        try {
            initializeTableview();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void initializeTableview() throws SQLException {

        ObservableList<Commande> data;
        CommandeDAO dao=new CommandeDAOimpl();
        data= dao.getCommandes();
        table.setItems(data);



    }


    public void Valider(ActionEvent event) throws SQLException {

        Statement st;
        ConnexionDB condb =new ConnexionDB();
        st = condb.getConnection().createStatement();
        for( Commande C:table.getItems()){
            if(C.getEst_recu().isSelected()){

                String sql="update commandes set est_recu=1 where id="+C.getId()+";";
                st.executeUpdate(sql);
            }

            if(C.getEst_complet().isSelected()){
                String sql1="update commandes set est_complet=1 where id="+C.getId()+";";
                st.executeUpdate(sql1);
            }
            //int prixP = Integer.parseInt(qtite_recu.getText());
            int val = Integer.parseInt( C.getQtite_recu().getText());
            String sql2="update commandes set qtite_recu="+val+" where id="+C.getId()+";";
            st.executeUpdate(sql2);

            try(Statement stmnt = condb.getConnection().createStatement()) {
                // enregistrer la quantité disponible en stock dans une var QtStock
                String sqlQtStock = String.format("SELECT qte FROM produits WHERE nom='%s'",C.getNom_pro() );
                ResultSet result = stmnt.executeQuery(sqlQtStock);
                while (result.next()) {
                    QtStock = result.getInt("qte");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try(Statement stmnt = condb.getConnection().createStatement()) {
                // update la quantité dans produits
                String updateStock = String.format("update produits set qte=%d where nom='%s'",(QtStock+val),C.getNom_pro() );
                stmnt.executeUpdate(updateStock);
                }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }


}