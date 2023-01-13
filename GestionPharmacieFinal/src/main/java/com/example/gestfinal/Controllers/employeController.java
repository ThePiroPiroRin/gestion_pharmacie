package com.example.gestfinal.Controllers;

import com.example.gestfinal.ConnexionDB;
import com.example.gestfinal.employe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class employeController implements Initializable {


    Connection con =null;
    PreparedStatement st=null;
    ResultSet rs=null;




    @FXML
    private Button btnsave;
    @FXML
    private Button btnclear;
    @FXML
    private Button btnTclear;
    @FXML
    private TableColumn<employe, String> colfonction;

    @FXML
    private TableColumn<employe, Integer> colid;

    @FXML
    private TableColumn<employe, String> colnom;

    @FXML
    private TableColumn<employe, String> colpasswd;

    @FXML
    private TableColumn<employe, String> colprenom;

    @FXML
    private TableColumn<employe, String> coltel;
    @FXML
    private TableColumn<employe, String> colusername;
    @FXML
    private ChoiceBox<String> mychoicebox;
    private String[] foncions={"employé","gérant"};
    @FXML
    private TextField nomid;

    @FXML
    private TextField passwdid;

    @FXML
    private TextField prenomid;

    @FXML
    private TableView<employe> table;

    @FXML
    private TextField telid;
   int id=0;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            mychoicebox.getItems().addAll(foncions);
            showEmploye();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public ObservableList<employe> getemploye() throws SQLException {
        ObservableList<employe> emps = FXCollections.observableArrayList();

        String query ="select * from employes";
        con = ConnexionDB.getConnection();
        try{
            st =con.prepareStatement(query);
            rs =st.executeQuery();
            while(rs.next()){
                /*
                employe emp=new employe();
                emp.setId(rs.getInt("id"));
                emp.setNom(rs.getString("nom"));
                emp.setPrenom(rs.getString("prenom"));
                emp.setTel(rs.getString("tel"));
                emp.setPasswd(rs.getString("passwd"));
                emp.setFonction(rs.getString("fonction"));

                 */
                emps.add(new employe(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("tel"),rs.getString("passwd"),rs.getString("fonction"),rs.getString("username")));
            }
        }catch(SQLException e){
            throw new RuntimeException();
        }



        return emps;
    }


    public void showEmploye() {
        ObservableList<employe> list= null;
        try {
            list = getemploye();
            System.out.println(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        colid.setCellValueFactory(new PropertyValueFactory<employe,Integer>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<employe,String>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<employe,String>("prenom"));


        coltel.setCellValueFactory(new PropertyValueFactory<employe,String>("tel"));
        colpasswd.setCellValueFactory(new PropertyValueFactory<employe,String>("passwd"));
        colfonction.setCellValueFactory(new PropertyValueFactory<employe,String>("fonction"));
        colusername.setCellValueFactory(new PropertyValueFactory<employe,String>("username"));
        table.setItems(list);

    }




    @FXML
    void saveemploye(ActionEvent event)  {

        String insert="insert into employes(nom,prenom,tel,fonction,passwd,username) values (?,?,?,?,?,?)";
        con=ConnexionDB.getConnection();
        try {
            st=con.prepareStatement(insert);
            st.setString(1,nomid.getText());
            st.setString(2,prenomid.getText());
            st.setString(3,telid.getText());
            st.setString(4,mychoicebox.getValue());
            st.setString(5,passwdid.getText());
            st.setString(6,nomid.getText()+prenomid.getText());
            st.executeUpdate();
            showEmploye();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    void getData(MouseEvent event){
        employe emp =table.getSelectionModel().getSelectedItem();
        id= emp.getId();
        nomid.setText(emp.getNom());
        prenomid.setText(emp.getPrenom());
        passwdid.setText(emp.getPasswd());
        telid.setText(emp.getTel());
        mychoicebox.setValue(emp.getFonction());
        btnsave.setDisable(true);

    }


    void update(){

        String update="update employe set nom=? ,prenom=? ,tel=? ,passwd=? ,fonction=? ,username=? where id=? ;";

        con=ConnexionDB.getConnection();
        try {
            st=con.prepareStatement(update);
            st.setString(1,nomid.getText());
            st.setString(2,prenomid.getText());
            st.setString(3,telid.getText());
            st.setString(5,mychoicebox.getValue());
            st.setString(4,passwdid.getText());
            st.setString(6,nomid.getText()+prenomid.getText());
            st.setInt(5,id);
            st.executeUpdate();
            showEmploye();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        }
        @FXML
        void clear(){
            nomid.setText(null);
            prenomid.setText(null);
            passwdid.setText(null);
            telid.setText(null);
            mychoicebox.setValue(null);


        }
        void delete(){

           String delete="delete from employe where id=? ;";
            con=ConnexionDB.getConnection();

            try {
                st=con.prepareStatement(delete);
                st.setInt(1,id);
                st.executeUpdate();
                showEmploye();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }



}
