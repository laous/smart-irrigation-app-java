package controller;


import dao.UtilisateurUtile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Utilisateur;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ResourceBundle;





public class GestionUsers implements Initializable {

    private UtilisateurUtile<Utilisateur> utilisateurUtile;

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

    @FXML
    private void insertButton() {
        Utilisateur user = new Utilisateur(Integer.parseInt(idUtilisateur.getText()),nomUtilisateur.getText(),nomUtilisateur.getText(),usernameUtilisateur.getText(),passwordUtilisateur.getText(),cinUtilisateur.getText());

    }


    @FXML
    private void updateButton() {

    }

    @FXML
    private void deleteButton() {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showBooks();
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
            return conn;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Utilisateur> getBooksList(){
//        ObservableList<Utilisateur> booksList = FXCollections.observableArrayList();
//        Connection connection = getConnection();
//        String query = "SELECT * FROM books ";
//        Statement st;
//        ResultSet rs;
//
//        try {
//            st = connection.createStatement();
//            rs = st.executeQuery(query);
//            Utilisateur users;
//            while(rs.next()) {
//                users = new Utilisateur();
//                booksList.add(users);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return booksList;
        return null;
    }

    // I had to change ArrayList to ObservableList I didn't find another option to do this but this works :)
    public void showBooks() {
        ObservableList<Utilisateur> list = getBooksList();

//        idColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("id"));
//        titleColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("title"));
//        authorColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("author"));
//        yearColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("year"));
//        pagesColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("pages"));

        TableView.setItems(list);
    }

}