package controller;

import dao.AuthenticationUtile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Administrateur;
import model.Technicien;
import model.Utilisateur;
import view.HelloApplication;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class HandleLogin {


    AuthenticationUtile authDao = new AuthenticationUtile(getConnection());

    @FXML
    private Button loginBtn ;
    @FXML
    private Text conStatus;
    @FXML
    private TextField username ;
    @FXML
    private PasswordField password;
    @FXML
    private ToggleGroup accountType;

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/irrigation","root","");
            return conn;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void seConnecter(ActionEvent ev) throws  IOException, SQLException {

        String user = username.getText();
        String pw = password.getText();
        HelloApplication m = new HelloApplication();



        RadioButton selectedRadioButton = (RadioButton) accountType.getSelectedToggle();
        String typeValue = selectedRadioButton.getText();



        if(typeValue.equals("Administrateur")){
            Administrateur admin = (Administrateur) authDao.authentication(user, pw, "admin");
            System.out.println(admin!=null ? admin.toString() : "null");
            if(admin !=null){
                conStatus.setText("Connection reussie!");
                m.changeScene("gestionUsers.fxml");
            }else{
                conStatus.setText("Connection echoue!");
            }

        }else  if(typeValue.equals("Technicien")){
            Technicien  technicien = (Technicien) authDao.authentication(user, pw,"technicien");
            System.out.println(technicien!=null ? technicien.toString() : "null");
            if(technicien!=null){
                conStatus.setText("Connection reussie!");
                m.changeScene("gestionCapteurs.fxml");
            }else{
                conStatus.setText("Connection echoue!");
            }


        }else  if(typeValue.equals("Utilisateur")){
            Utilisateur utilisateur = authDao.authentication(user,pw,"utilisateur");
            System.out.println(utilisateur!=null ? utilisateur.toString() : "null");
            if(utilisateur!=null){
                conStatus.setText("Connection reussie!");
                m.changeScene("dashboard.fxml");
            }else{
                conStatus.setText("Connection echoue!");
            }


        }



    }

//    public void changeScene(ActionEvent ev , String fxml) throws  IOException{
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
//        Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }


}
