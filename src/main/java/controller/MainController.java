package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Utilisateur;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ResourceBundle;





public class MainController implements Initializable {

    @FXML
    private TextField idField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField yearField;

    @FXML
    private TextField pagesField;

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
    private TableColumn<Utilisateur, String> titleColumn;

    @FXML
    private TableColumn<Utilisateur, String> authorColumn;

    @FXML
    private TableColumn<Utilisateur, Integer> yearColumn;

    @FXML
    private TableColumn<Utilisateur, Integer> pagesColumn;

    @FXML
    private void insertButton() {
        String query = "insert into 4 values("+idField.getText()+",'"+titleField.getText()+"','"+authorField.getText()+"',"+yearField.getText()+","+pagesField.getText()+")";
        executeQuery(query);
        showBooks();
    }


    @FXML
    private void updateButton() {
        String query = "UPDATE books SET Title='"+titleField.getText()+"',Author='"+authorField.getText()+"',Year="+yearField.getText()+",Pages="+pagesField.getText()+" WHERE ID="+idField.getText()+"";
        executeQuery(query);
        showBooks();
    }

    @FXML
    private void deleteButton() {
        String query = "DELETE FROM books WHERE ID="+idField.getText()+"";
        executeQuery(query);
        showBooks();
    }

    public void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showBooks();
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","admin");
            return conn;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Utilisateur> getBooksList(){
        ObservableList<Utilisateur> booksList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM books ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Utilisateur users;
            while(rs.next()) {
                users = new Utilisateur();
                booksList.add(users);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booksList;
    }

    // I had to change ArrayList to ObservableList I didn't find another option to do this but this works :)
    public void showBooks() {
        ObservableList<Utilisateur> list = getBooksList();

        idColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("author"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("year"));
        pagesColumn.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("pages"));

        TableView.setItems(list);
    }

}