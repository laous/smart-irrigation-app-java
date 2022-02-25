package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import view.HelloApplication;

import java.io.IOException;

public class HandleLogin {
    String sampleEmail = "admin";
    String samplePassword = "irrigation";

    @FXML
    private Button loginBtn ;
    @FXML
    private Label conStatus ;
    @FXML
    private TextField email ;
    @FXML
    private PasswordField password;

    public void seConnecter(ActionEvent ev) throws InterruptedException, IOException {

        if(sampleEmail.equals(email.getText()) && samplePassword.equals(password.getText())){
            conStatus.setText("Connection reussie!");
            Thread.sleep(3000);
            HelloApplication m = new HelloApplication();
            m.changeScene("gestionCapteurs.fxml");
        }else{
            conStatus.setText("Connection echoue!");
        }
    }
}
