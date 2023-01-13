package com.example.gestfinal;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {


    @FXML

    private Label exit;

    @FXML
    private JFXButton EMID;
    @FXML
    private JFXButton STID;

    @FXML
     StackPane contentArea;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
     if(Data.fonction.equals("employé")) {
         EMID.setVisible(false);
         STID.setVisible(false);
     }
        exit.setOnMouseClicked(e ->{
            System.exit(0);
        });

        try{
            Parent fxml= FXMLLoader.load(getClass().getResource("stock.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);

        }catch (IOException e){

        }
    }

    public void stock(ActionEvent actionEvent) throws IOException{
        Parent fxml= FXMLLoader.load(getClass().getResource("stock.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);

    }

    public void vente(ActionEvent actionEvent) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("vente.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void commande(ActionEvent actionEvent) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("Commande.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    @FXML
    public void employe(ActionEvent actionEvent) throws IOException{
        Parent fxml= FXMLLoader.load(getClass().getResource("employe.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    @FXML
    public void statistique(ActionEvent actionEvent) throws IOException{
        Parent fxml= FXMLLoader.load(getClass().getResource("statistique.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
}
