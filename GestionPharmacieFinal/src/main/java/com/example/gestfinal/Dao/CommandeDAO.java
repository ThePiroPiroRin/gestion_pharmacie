package com.example.gestfinal.Dao;

import com.example.gestfinal.Classes.Commande;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface CommandeDAO {
     void save(Commande cmd);

     ObservableList<Commande> getCommandes() throws SQLException;
}
