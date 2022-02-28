package controller;


import dao.UtilisateurUtile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Administrateur;
import model.Technicien;
import model.Utilisateur;
import org.w3c.dom.events.MouseEvent;
import view.HelloApplication;

import java.io.IOException;
import java.net.URL;
import java.sql.*;

import java.util.LinkedList;
import java.util.ResourceBundle;





public class GestionUsers implements Initializable {

    private UtilisateurUtile<Utilisateur> utilisateurUtile = new UtilisateurUtile<>(getConnection());

    @FXML
    private TextField idUtilisateur;

    @FXML
    private TextField prenomUtilisateur;

    @FXML
    private TextField nomUtilisateur;

    @FXML
    private TextField cinUtilisateur;

    @FXML
    private TextField usernameUtilisateur;

    @FXML
    private PasswordField passwordUtilisateur;

    @FXML
    private TextField typeUtilisateur;

    @FXML Label errorLabel;

    @FXML
    private Button insertButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Utilisateur> TableView;

    @FXML
    private TableColumn<Utilisateur, Integer> idColumn;

    @FXML
    private TableColumn<Utilisateur, String> prenomColumn;

    @FXML
    private TableColumn<Utilisateur, String> nomColumn;

    @FXML
    private TableColumn<Utilisateur, String> cinColumn;

    @FXML
    private TableColumn<Utilisateur, String> usernameColumn;

    @FXML TableColumn<Utilisateur, String> passwordColumn;

    @FXML TableColumn<Utilisateur, String> typeColumn;

    public void setErrorLabel(String s){
        errorLabel.setText(s);
    }


    @FXML
    private void insertButton() throws SQLException {

        if(typeUtilisateur.getText().equals("utilisateur")){
            Utilisateur user = new Utilisateur(nomUtilisateur.getText(),prenomUtilisateur.getText(),usernameUtilisateur.getText(),passwordUtilisateur.getText(),cinUtilisateur.getText());
            boolean status = utilisateurUtile.ajouterUtilisateur(user);
            if (status) {
                setErrorLabel("Utilisateur ajoute");
                showUsers();
            } else {
                setErrorLabel("Utilisateur non ajoute");
            }
        }else if(typeUtilisateur.getText().equals("admin")){
            Administrateur user = new Administrateur(nomUtilisateur.getText(),prenomUtilisateur.getText(),usernameUtilisateur.getText(),passwordUtilisateur.getText(),cinUtilisateur.getText());
            boolean status = utilisateurUtile.ajouterUtilisateur(user);
            if (status) {
                setErrorLabel("Admin ajoute");
                showUsers();
            } else {
                setErrorLabel("Admin non ajoute");
            }
        }else if(typeUtilisateur.getText().equals("technicien")){
            Technicien user = new Technicien(nomUtilisateur.getText(),prenomUtilisateur.getText(),usernameUtilisateur.getText(),passwordUtilisateur.getText(),cinUtilisateur.getText());
            boolean status = utilisateurUtile.ajouterUtilisateur(user);
            if (status) {
                setErrorLabel("Technicien ajoute");
                showUsers();
            } else {
                setErrorLabel("Technicien non ajoute");
            }
        }else{
            setErrorLabel("Utilisateur non ajoute, type est incorrect.");
        }
        clearInputs();


    }

    @FXML
    private void updateButton() throws SQLException {
        if(typeUtilisateur.getText().equals("utilisateur")){
            Utilisateur user = new Utilisateur(Integer.parseInt(idUtilisateur.getText()),nomUtilisateur.getText(),prenomUtilisateur.getText(),usernameUtilisateur.getText(),passwordUtilisateur.getText(),cinUtilisateur.getText());
            boolean status = utilisateurUtile.majUtilisateur(user);
            if (status) {
                setErrorLabel("Utilisateur modifie");
                showUsers();
            } else {
                setErrorLabel("Utilisateur non modifie");
            }
        }else if(typeUtilisateur.getText().equals("admin")){
            Administrateur user = new Administrateur(Integer.parseInt(idUtilisateur.getText()),nomUtilisateur.getText(),prenomUtilisateur.getText(),usernameUtilisateur.getText(),passwordUtilisateur.getText(),cinUtilisateur.getText());
            boolean status = utilisateurUtile.majUtilisateur(user);
            if (status) {
                setErrorLabel("Admin modifie");
                showUsers();
            } else {
                setErrorLabel("Admin non modifie");
            }
        }else if(typeUtilisateur.getText().equals("technicien")){
            Technicien user = new Technicien(Integer.parseInt(idUtilisateur.getText()),nomUtilisateur.getText(),prenomUtilisateur.getText(),usernameUtilisateur.getText(),passwordUtilisateur.getText(),cinUtilisateur.getText());
            boolean status = utilisateurUtile.majUtilisateur(user);
            if (status) {
                setErrorLabel("Technicien modifie");
                showUsers();
            } else {
                setErrorLabel("Technicien non modifie");
            }
        }else{
            setErrorLabel("Utilisateur non modifie, type est incorrect.");
        }
        clearInputs();
    }

    @FXML
    private void deleteButton() throws SQLException {
        if(typeUtilisateur.getText().equals("utilisateur")){
            Utilisateur user = new Utilisateur(Integer.parseInt(idUtilisateur.getText()),nomUtilisateur.getText(),prenomUtilisateur.getText(),usernameUtilisateur.getText(),passwordUtilisateur.getText(),cinUtilisateur.getText());
            boolean status = utilisateurUtile.supprimerUtilisateur(user);
            if (status) {
                setErrorLabel("Utilisateur supprime");
                showUsers();
            } else {
                setErrorLabel("Utilisateur non supprime");
            }
        }else if(typeUtilisateur.getText().equals("admin")){
            Administrateur user = new Administrateur(Integer.parseInt(idUtilisateur.getText()),nomUtilisateur.getText(),prenomUtilisateur.getText(),usernameUtilisateur.getText(),passwordUtilisateur.getText(),cinUtilisateur.getText());
            boolean status = utilisateurUtile.supprimerUtilisateur(user);
            if (status) {
                setErrorLabel("Admin supprime");
                showUsers();
            } else {
                setErrorLabel("Admin non supprime");
            }
        }else if(typeUtilisateur.getText().equals("technicien")){
            Technicien user = new Technicien(Integer.parseInt(idUtilisateur.getText()),nomUtilisateur.getText(),prenomUtilisateur.getText(),usernameUtilisateur.getText(),passwordUtilisateur.getText(),cinUtilisateur.getText());
            boolean status = utilisateurUtile.supprimerUtilisateur(user);
            if (status) {
                setErrorLabel("Technicien supprime");
                showUsers();
            } else {
                setErrorLabel("Technicien non supprime");
            }
        }else{
            setErrorLabel("Utilisateur non ajoute, type est incorrect.");
        }
        clearInputs();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showUsers();
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

    public ObservableList<Utilisateur> getUsersList() throws SQLException {
        ObservableList<Utilisateur> usersList = FXCollections.observableArrayList();
        LinkedList<Utilisateur> linkedUsers = utilisateurUtile.getAllUsers();
        for(Utilisateur u : linkedUsers){
            usersList.add(u);
        }

        return usersList;
    }

    public void showUsers() throws SQLException {
        ObservableList<Utilisateur> list = getUsersList();

        idColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("idUser"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("prenom"));
        cinColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("cin"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("password"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("type"));

        TableView.setItems(list);
    }

    public void handleMouseAction(javafx.scene.input.MouseEvent mouseEvent) {
        Utilisateur user = TableView.getSelectionModel().getSelectedItem();
        idUtilisateur.setText(String.valueOf(user.getIdUser()));
        nomUtilisateur.setText(user.getNom());
        prenomUtilisateur.setText(user.getPrenom());
        cinUtilisateur.setText(user.getCin());
        usernameUtilisateur.setText(user.getUsername());
        typeUtilisateur.setText(user.getType());
        passwordUtilisateur.setText(user.getPassword());
    }

    public void clearInputs(){
        idUtilisateur.setText("");
        nomUtilisateur.setText("");
        prenomUtilisateur.setText("");
        cinUtilisateur.setText("");
        usernameUtilisateur.setText("");
        typeUtilisateur.setText("");
        passwordUtilisateur.setText("");
    }

    public void goBack(ActionEvent actionEvent) throws IOException {

        HelloApplication m = new HelloApplication();
        m.changeScene("admin.fxml");
    }
}