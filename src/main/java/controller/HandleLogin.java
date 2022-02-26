package controller;

import dao.AuthenticationUtile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

        if(response != null){
            conStatus.setText("Connection reussie!");
            HelloApplication m = new HelloApplication();
            m.changeScene("gestionUsers.fxml");
        }else{
            conStatus.setText("Connection echoue!");
        }
    }
}
