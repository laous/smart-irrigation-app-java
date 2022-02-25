package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HandleLogin {
    String sampleEmail = "oussama@admin.com";
    String samplePassword = "irrigation";

    @FXML
    private Button loginBtn ;
    @FXML
    private Label conStatus ;
    @FXML
    private TextField email ;
    @FXML
    private PasswordField password;

    public void seConnecter(ActionEvent ev) {

        if(sampleEmail.equals(email.getText()) && samplePassword.equals(password.getText())){
            conStatus.setText("Connection reussie!");
        }else{
            conStatus.setText("Connection echoue!");
        }
    }
}
