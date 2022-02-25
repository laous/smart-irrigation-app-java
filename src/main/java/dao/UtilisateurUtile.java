package dao;


import model.Administrateur;
import model.Technicien;
import model.Utilisateur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class UtilisateurUtile<T extends Utilisateur> {

    private final Connection con;

    public UtilisateurUtile(Connection con) {
        this.con = con;
    }

    public LinkedList<T> getAllUsers() throws SQLException {
        LinkedList<T> users = new LinkedList<>();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from users");

        while (rs.next()){
            T user = null;
            if(rs.getString("type").equals("admin")){
                user = (T) new Administrateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("password"),rs.getString("cin"));
            }else if(rs.getString("type").equals("technicien")){
                user = (T) new Technicien(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("password"),rs.getString("cin"));
            }else{
                user = (T) new Utilisateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("password"),rs.getString("cin"));
            }
            users.add(user);
        }
        return  users;
    }

    public T getUserByCIN(String cin) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from users where cin like '" + cin + "'");
        T user = null;

        while(rs.next()){
            if(rs.getString("type").equals("admin")){
                user = (T) new Administrateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("password"),rs.getString("cin"));
            }else if(rs.getString("type").equals("technicien")){
                user = (T) new Technicien(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("password"),rs.getString("cin"));
            }
        }
        return user;
    }

    public boolean ajouterUtilisateur(T user) throws SQLException {
        if(getUserByCIN(user.getCin()) != null){
            return false;
        }
        Statement stmt = con.createStatement();
        String query = "";

        if(user instanceof Administrateur){
            query = "INSERT INTO users (`nom`,`prenom`, `username`, `password`, `cin`, `type`) VALUES " +
                    "('" + user.getNom() + "','" + user.getPrenom() + "','" + user.getUsername() + "','" + user.getPassword() + "','" + user.getCin() + "','admin')" ;
        }else if(user instanceof Technicien){
            query = "INSERT INTO users (`nom`,`prenom`, `username`, `password`, `cin`, `type`) VALUES " +
                    "('" + user.getNom() + "','" + user.getPrenom() + "','" + user.getUsername() + "','" + user.getPassword() + "','" + user.getCin() + "','technicien')" ;
        }else{
            query = "INSERT INTO users (`nom`,`prenom`, `username`, `password`, `cin`, `type`) VALUES " +
                    "('" + user.getNom() + "','" + user.getPrenom() + "','" + user.getUsername() + "','" + user.getPassword() + "','" + user.getCin() + "','utilisateur')" ;
        }
        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated > 0;
    }

    public boolean supprimerUtilisateur(T user) throws SQLException {
        Statement stmt = con.createStatement();
        String query = "";

        if(user instanceof Administrateur){
            query = "DELETE FROM users where id like '" + user.getIdUser() + "' and type like 'admin'" ;
        }else if(user instanceof Technicien){
            query = "DELETE FROM users where id like '" + user.getIdUser() + "' and type like 'technicien'" ;
        }else{
            query = "DELETE FROM users where id like '" + user.getIdUser() + "' and type like 'utilisateur'" ;
        }
        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated > 0;
    }

    public boolean majUtilisateur(T user) throws SQLException {
        Statement stmt = con.createStatement();
        String query = "";

        if(user instanceof Administrateur){
            query = "UPDATE users SET nom='"+ user.getNom()  + "',prenom='" + user.getPrenom() + "',username='" + user.getUsername() + "',password='" + user.getPassword() + "',cin='" + user.getCin() + "' where id like '" + user.getIdUser() +"' and type like 'admin'" ;
        }else if(user instanceof Technicien){
            query = "UPDATE users SET nom='"+ user.getNom()  + "',prenom='" + user.getPrenom() + "',username='" + user.getUsername() + "',password='" + user.getPassword() + "',cin='" + user.getCin() + "' where id like '" + user.getIdUser() +"' and type like 'technicien'" ;
        }else{
            query = "UPDATE users SET nom='"+ user.getNom()  + "',prenom='" + user.getPrenom() + "',username='" + user.getUsername() + "',password='" + user.getPassword() + "',cin='" + user.getCin() + "' where id like '" + user.getIdUser() +"' and type like 'utilisateur'" ;
        }
        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated > 0;
    }


}

