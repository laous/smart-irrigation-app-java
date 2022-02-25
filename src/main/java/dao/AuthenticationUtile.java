package dao;

import model.Administrateur;
import model.Technicien;
import model.Utilisateur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Jonas
 */
public class AuthenticationUtile {

    private final Connection con;

    AuthenticationUtile(Connection con){
        this.con = con;
    }
    public Utilisateur authentication(String email, String password ) throws SQLException {
        String query = "SELECT * FROM users WHERE email ='"+email+"' and password='"+password+"'";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            if (rs.getString("type").equals("technicien")) {  // Type technicien
                return new Technicien(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"),rs.getString("cin"));
            }else  if (rs.getString("type").equals("admin")){
                return new Administrateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"),rs.getString("cin"));
            }else{
                return  new Utilisateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"),rs.getString("cin"));
            }
        }
        return null;
    }
}
