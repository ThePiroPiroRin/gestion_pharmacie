package com.example.gestfinal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StatistiqueController implements Initializable {
    @FXML
    private  Label ventes_mois;
    @FXML
    private  Label ventes_jour;
    @FXML
    private  Label achats_mois;
    @FXML
    private  Label achats_jour;

    public static int getSalesCountThisMonth() {
        String url = "jdbc:mysql://localhost:3306/gestionpharma";
        String username = "root";
        String password = "ABCabc12455@#?$";
        int count = 0;

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            LocalDate today = LocalDate.now();
            String thisMonth = today.format(DateTimeFormatter.ofPattern("yyyy-MM"));


            String query = "SELECT PrixVente FROM vente WHERE LaDate LIKE '" + thisMonth + "%'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                count += rs.getInt("PrixVente");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

       // ventes_mois.setText(String.valueOf(count));
        return count;
    }
    public static int getPurchasesCountThisMonth() {
        List<Integer>prix=new ArrayList<>();
        List<Integer>qte=new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/gestionpharma";
        String username = "root";
        String password = "ABCabc12455@#?$";
        int count = 0;

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            LocalDate today = LocalDate.now();
            String thisMonth = today.format(DateTimeFormatter.ofPattern("yyyy-MM"));

            String query = "SELECT prix FROM commandes WHERE LaDate LIKE '" + thisMonth + "%'";
            String query2 = "SELECT qtite FROM commandes WHERE LaDate LIKE '" + thisMonth + "%'";
            Statement stmt = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSet rs2 = stmt2.executeQuery(query2);
            while (rs.next()) {
                prix.add(rs.getInt("prix"));
            }
            while(rs2.next()){
                qte.add(rs2.getInt("qtite"));
            }
            for(int i=0;i<prix.size();i++){
                count+= prix.get(i)* qte.get(i);
                System.out.println(prix.get(i));
                System.out.println(count);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //achats_mois.setText(String.valueOf(count));
        return count;

    }
    public static int getSalesCountToday() {
        String url = "jdbc:mysql://localhost:3306/gestionpharma";
        String username = "root";
        String password = "ABCabc12455@#?$";
        int count = 0;

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            LocalDate today = LocalDate.now();
            String todayString = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            String query = "SELECT PrixVente FROM vente WHERE LaDate = '" + todayString + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                count += rs.getInt("PrixVente");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //ventes_jour.setText(String.valueOf(count));
        return count;
    }
    public static int getPurchasesCountToday() {
        List<Integer>prix=new ArrayList<>();
        List<Integer>qte=new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/gestionpharma";
        String username = "root";
        String password = "ABCabc12455@#?$";
        int count = 0;

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            LocalDate today = LocalDate.now();
            String todayString = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            String query = "SELECT prix FROM commandes WHERE LaDate = '" + todayString + "'";
            String query2 = "SELECT qtite FROM commandes WHERE LaDate = '" + todayString + "'";
            Statement stmt = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSet rs2 = stmt2.executeQuery(query2);
            while (rs.next()) {
                prix.add(rs.getInt("prix"));
            }
            while(rs2.next()){
                qte.add(rs2.getInt("qtite"));
            }
            for(int i=0;i<prix.size();i++){
                count+= prix.get(i)* qte.get(i);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //achats_jour.setText(String.valueOf(count));
        return count ;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        getSalesCountThisMonth();
        getPurchasesCountThisMonth();
        getSalesCountToday();
        getPurchasesCountToday();

         */
        ventes_mois.setText(String.valueOf(getSalesCountThisMonth()));
        achats_mois.setText(String.valueOf(getPurchasesCountThisMonth()));
        ventes_jour.setText(String.valueOf(getSalesCountToday()));
        achats_jour.setText(String.valueOf(getPurchasesCountToday()));
    }
}
