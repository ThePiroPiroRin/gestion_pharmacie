package com.example.gestfinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;

public class LoginController {

    @FXML
    private Button cancelbutton;
    @FXML
    private TextField usernametextfield;
    @FXML
    private PasswordField passwordtextfield;
    @FXML
    private Label loginmessagelabel;
    public void cancelbuttonaction(ActionEvent e){

        Stage stage =(Stage) cancelbutton.getScene().getWindow();
        stage.close();
    }
    public void loginButtonOnAction(ActionEvent e){
        if(!usernametextfield.getText().isBlank() && !passwordtextfield.getText().isBlank()){
            validatelogin(e);
        }
    }
  public void validatelogin(ActionEvent event){
        ConnexionDB connectnow=new  ConnexionDB();
        Connection connectiondb=connectnow.getConnection();
        String req="select count(1) from employes where username=? and passwd=?";
        String req1="select * from employes where username=? and passwd=?";



        try{
            PreparedStatement statement=connectiondb.prepareStatement(req);
            statement.setString(1,usernametextfield.getText());
            statement.setString(2,passwordtextfield.getText());
            ResultSet queryResult=statement.executeQuery();

            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                   // Data.username=usernametextfield.getText();
                    //Data.motdepasse=passwordtextfield.getText();


                    PreparedStatement statement1=connectiondb.prepareStatement(req1);
                    statement1.setString(1,usernametextfield.getText());
                    statement1.setString(2,passwordtextfield.getText());
                     ResultSet queryResult1=statement1.executeQuery();
                     queryResult1.next();
                    Data.fonction= queryResult1.getString(5);

                    Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Dashboard.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 950, 600);
                    stage.setTitle("Hello!");
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    loginmessagelabel.setText("mauvais mot de passe");
                }

            }


        }catch(Exception e){
            e.printStackTrace();
        }
  }

}