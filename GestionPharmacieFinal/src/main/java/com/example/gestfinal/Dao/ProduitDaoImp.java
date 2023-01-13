package com.example.gestfinal.Dao;

import com.example.gestfinal.Classes.Produit;
import com.example.gestfinal.ConnexionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProduitDaoImp implements ProduitDao {
    @Override
    public void save(Produit p)
    {
        ConnexionDB conDb = new ConnexionDB();
        try(Statement st = conDb.getConnection().createStatement())
        {
            String req="INSERT INTO produits(nom, prix, description) VALUES('"+p.getNom()+"',"+p.getPrix()+",'"+p.getDescription()+"')";
            System.out.println(req);
            st.executeUpdate(req);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("saved");


    }


    @Override
    public ObservableList<Produit> getProduits() throws SQLException {
            ObservableList<Produit> prods = FXCollections.observableArrayList();
            String query ="select * from produits";
            ConnexionDB conDb = new ConnexionDB();
            try(Statement st = conDb.getConnection().createStatement())
            {
                ResultSet rs =st.executeQuery(query);
                while(rs.next()){
                    Produit prod = new Produit(rs.getString("nom"),rs.getInt("qte"),rs.getInt("prix"), rs.getString("description"));
                    prods.add(prod);
                }
            }catch(SQLException e){
                throw new RuntimeException();
            }
            return prods;
        }


    }


