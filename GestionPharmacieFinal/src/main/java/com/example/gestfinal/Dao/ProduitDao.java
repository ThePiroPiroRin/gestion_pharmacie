package com.example.gestfinal.Dao;


import com.example.gestfinal.Classes.Produit;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ProduitDao {
    public void save(Produit p);
    public ObservableList<Produit> getProduits() throws SQLException;

}