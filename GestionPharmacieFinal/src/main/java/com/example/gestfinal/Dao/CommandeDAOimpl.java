package com.example.gestfinal.Dao;

import com.example.gestfinal.Classes.Commande;
import com.example.gestfinal.ConnexionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.sql.*;

public class CommandeDAOimpl implements CommandeDAO{
    @Override
    public void save(Commande cmd)
    {
        ConnexionDB conDb = new ConnexionDB();
        Statement st;
        try
        {
            st = conDb.getConnection().createStatement();
            String req="INSERT INTO commandes(nom_pro,prix,qtite,nom_Fr,LaDate) VALUES('"
                    +cmd.getNom_pro()+"',"+cmd.getPrix()+","+cmd.getQtite()+",'"+cmd.getNom_Fr()+"','"+cmd.getDate()+"')";
            System.out.println(req);
            st.executeUpdate(req);
            conDb.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("saved");
    }

    @Override
    public ObservableList<Commande> getCommandes() throws SQLException {
        ObservableList<Commande> data = FXCollections.observableArrayList();
        String query ="select * from commandes";
        ConnexionDB conDb = new ConnexionDB();
        try(Statement st = conDb.getConnection().createStatement())
        {
            ResultSet result =st.executeQuery(query);
            while(result.next()){
                CheckBox Precu = new CheckBox();
                CheckBox Pcomplet = new CheckBox();
                TextField Qrecu = new TextField();


                if(result.getInt(5)==1){
                    Precu.setSelected(true);
                }
                if(result.getInt(6)==1){
                    Pcomplet.setSelected(true);
                }
                Qrecu.setText(String.valueOf(result.getInt(7)));
                Commande cmd1 = new Commande(result.getString("nom_pro"), result.getInt("prix"), result.getInt("qtite"), result.getString("nom_Fr"), Precu, Pcomplet, Qrecu, result.getInt("id"));
                data.add(cmd1);

            }
        }catch(SQLException e){
            throw new RuntimeException();
        }
        return data;
    }

}
