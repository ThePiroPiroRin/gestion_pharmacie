package com.example.gestfinal.Controllers;
import com.example.gestfinal.Classes.Produit;
import com.example.gestfinal.Dao.ProduitDao;
import com.example.gestfinal.Dao.ProduitDaoImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class StockController implements Initializable {
        @FXML
        private TableColumn<Produit,String> colNom;
        @FXML
        private TableColumn<Produit,Integer> colQte;
        @FXML
        private TableColumn<Produit,Integer> colPrix;
        @FXML
        private TableColumn<Produit,String> colDescription;
        @FXML
        private TableView<Produit> tableStock;
        @FXML
        private TextField Nom;

        @FXML
        private TextField Prix;
        @FXML
        private TextField Description;

        @FXML
        private Label message;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colNom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("qte"));
        colQte.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("prix"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Produit,String>("description"));
        //tableStock.getColumns().addAll(colNom,colQte,colPrix,colDescription);
        try {
            initializeTableview();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private void initializeTableview() throws SQLException {
        ObservableList<Produit> prods;
        ProduitDao dao=new ProduitDaoImp();
        prods= dao.getProduits();
        tableStock.setItems(prods);

    }
    @FXML
    void sendProduct(ActionEvent event) {
        String name = Nom.getText();
        int price = Integer.parseInt(Prix.getText());
        int qte=0;
        String description=Description.getText();
        message.setText(message.getText()+Nom.getText()+" de prix: "+Prix.getText());
        ProduitDao produitDAO = new ProduitDaoImp();
        Produit pr = new Produit(name,price,qte,description);
        produitDAO.save(pr);
        Nom.setText("");
        Prix.setText("");
        Description.setText("");
        try {
            initializeTableview();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}


