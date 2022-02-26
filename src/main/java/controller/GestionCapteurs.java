package controller;


import dao.CapteurUtile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.net.URL;
import java.sql.*;

import java.util.LinkedList;
import java.util.ResourceBundle;





public class GestionCapteurs implements Initializable {

    CapteurUtile<Capteur> capteurUtile = new CapteurUtile<>(getConnection());

    @FXML
    private TextField codeCapteur;

    @FXML
    private TextField etatCapteur;

    @FXML
    private TextField zoneCapteur;

    @FXML
    private TextField typeCapteur;

    @FXML
    private Button insertButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    Label errorLabel;

    @FXML
    private TableView<Capteur> TableView;

    @FXML
    private TableColumn<Capteur, String> codeColumn;

    @FXML
    private TableColumn<Capteur, String> etatColumn;

    @FXML
    private TableColumn<Capteur, Integer> zoneColumn;

    @FXML
    private TableColumn<Capteur , String> typeColumn;

    public GestionCapteurs() throws SQLException {
    }

    public void setErrorLabel(String s){
        errorLabel.setText(s);
    }


    @FXML
    private void insertButton() throws SQLException {
        if(typeCapteur.getText().equals("humidite")){
            CapteurHumidite ch = new CapteurHumidite(codeCapteur.getText(), etatCapteur.getText(),Integer.parseInt(zoneCapteur.getText()));
            boolean status = capteurUtile.ajouterCapteur(ch);
            if (status) {
                setErrorLabel("Capteur ajoute");
                showCapteurs();
                clearInputs();
            } else {
                setErrorLabel("Capteur non ajoute");
            }
        }else if(typeCapteur.getText().equals("temperature")){
            CapteurTemperature ct = new CapteurTemperature(codeCapteur.getText(), etatCapteur.getText(),Integer.parseInt(zoneCapteur.getText()));
            boolean status = capteurUtile.ajouterCapteur(ct);
            if (status) {
                setErrorLabel("Capteur ajoute");
                showCapteurs();
                clearInputs();
            } else {
                setErrorLabel("Capteur non ajoute");
            }
        } else {
            setErrorLabel("Capteur non ajoute");
        }
    }


    @FXML
    private void updateButton() throws SQLException {
        if(typeCapteur.getText().equals("humidite")){
            CapteurHumidite ch = new CapteurHumidite(codeCapteur.getText(), etatCapteur.getText(),Integer.parseInt(zoneCapteur.getText()));
            boolean status = capteurUtile.majCapteur(ch);
            if (status) {
                setErrorLabel("Capteur modifie");
                showCapteurs();
                clearInputs();
            } else {
                setErrorLabel("Capteur non modifie");
            }
        }else if(typeCapteur.getText().equals("temperature")){
            CapteurTemperature ct = new CapteurTemperature(codeCapteur.getText(), etatCapteur.getText(),Integer.parseInt(zoneCapteur.getText()));
            boolean status = capteurUtile.majCapteur(ct);
            if (status) {
                setErrorLabel("Capteur modifie");
                showCapteurs();
                clearInputs();
            } else {
                setErrorLabel("Capteur non modifie");
            }
        } else {
            setErrorLabel("Capteur non modifie");
        }
    }

    @FXML
    private void deleteButton() throws SQLException {
        if(typeCapteur.getText().equals("humidite")){
            CapteurHumidite ch = new CapteurHumidite(codeCapteur.getText(), etatCapteur.getText(),Integer.parseInt(zoneCapteur.getText()));
            boolean status = capteurUtile.supprimerCapteur(ch);
            if (status) {
                setErrorLabel("Capteur supprime");
                showCapteurs();
                clearInputs();
            } else {
                setErrorLabel("Capteur non supprime");
            }
        }else if(typeCapteur.getText().equals("temperature")){
            CapteurTemperature ct = new CapteurTemperature(codeCapteur.getText(), etatCapteur.getText(),Integer.parseInt(zoneCapteur.getText()));
            boolean status = capteurUtile.supprimerCapteur(ct);
            if (status) {
                setErrorLabel("Capteur supprime");
                showCapteurs();
                clearInputs();
            } else {
                setErrorLabel("Capteur non supprime");
            }
        } else {
            setErrorLabel("Capteur non supprime");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showCapteurs();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public ObservableList<Capteur> getCapteurList() throws SQLException {
        ObservableList<Capteur> capteurs = FXCollections.observableArrayList();
        LinkedList<Capteur> linkedCapteurs = capteurUtile.getAllCapteurs();
        for(Capteur c : linkedCapteurs){
            capteurs.add(c);
        }

        return capteurs;
    }

    // I had to change ArrayList to ObservableList I didn't find another option to do this but this works :)
    public void showCapteurs() throws SQLException {
        ObservableList<Capteur> list = getCapteurList();

        etatColumn.setCellValueFactory(new PropertyValueFactory<Capteur,String>("etat"));
        zoneColumn.setCellValueFactory(new PropertyValueFactory<Capteur,Integer>("zone"));
        codeColumn.setCellValueFactory(new PropertyValueFactory<Capteur,String>("code"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Capteur,String>("type"));

        TableView.setItems(list);
    }

    public void handleMouseAction(javafx.scene.input.MouseEvent mouseEvent) {
        Capteur c = TableView.getSelectionModel().getSelectedItem();
        codeCapteur.setText(String.valueOf(c.getCode()));
        zoneCapteur.setText((String.valueOf(c.getZone())));
        typeCapteur.setText(c.getType());
        etatCapteur.setText(c.getEtat());
    }

    public void clearInputs(){
        etatCapteur.setText("");
        typeCapteur.setText("");
        zoneCapteur.setText("");
        codeCapteur.setText("");

    }

}