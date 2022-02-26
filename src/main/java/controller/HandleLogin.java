package controller;

import dao.AuthenticationUtile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Administrateur;
import model.Technicien;
import model.Utilisateur;
import view.HelloApplication;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HandleLogin {
    String sampleEmail = "admin";
    String samplePassword = "irrigation";

    AuthenticationUtile authDao = new AuthenticationUtile(getConnection());

    @FXML
    private Button loginBtn ;
    @FXML
    private Label conStatus ;
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

    public void seConnecter(ActionEvent ev) throws InterruptedException, IOException, SQLException {

        String user = username.getText();
        String pw = password.getText();

        Utilisateur response = authDao.authentication(user, pw);

        RadioButton selectedRadioButton = (RadioButton) accountType.getSelectedToggle();
        String typeValue = selectedRadioButton.getText();

        if(response != null){
            HelloApplication m = new HelloApplication();
            if(response instanceof Administrateur && typeValue.equals("administrateur")){
                conStatus.setText("Connection reussie!");
                Thread.sleep(2000);
                m.changeScene("getstionUsers.fxml");
            }else if(response instanceof Technicien && typeValue.equals("technicien")) {
                conStatus.setText("Connection reussie!");
                Thread.sleep(2000);
                m.changeScene("gestionCapteurs.fxml");
            }else if(typeValue.equals("utilisateur")){
                conStatus.setText("Connection reussie!");
                Thread.sleep(2000);
                m.changeScene("dashboard.fxml");
            }
        }else{
            conStatus.setText("Connection echoue!");
        }
    }


}
