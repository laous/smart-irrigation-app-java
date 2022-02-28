package controller;

import javafx.scene.input.MouseEvent;
import view.HelloApplication;

import java.io.IOException;

public class AdminController {
    HelloApplication m = new HelloApplication();
    public void changeDahsboard(MouseEvent mouseEvent) throws IOException {
        m.changeScene("dashboard.fxml");
    }

    public void changeGestion(MouseEvent mouseEvent) throws IOException {
        m.changeScene("gestionUsers.fxml");
    }
}
