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

    public AuthenticationUtile(Connection con){
        this.con = con;
    }
    public Utilisateur authentication(String username, String password , String type) throws SQLException {
        String query = "SELECT * FROM users WHERE username like '"+username+"' and password like '"+password+"' and type like '"+type+"'";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            if (rs.getString("type").equals("technicien")) {  // Type technicien
                return new Technicien(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("password"),rs.getString("cin"));
            }else  if (rs.getString("type").equals("admin")){
                return new Administrateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("password"),rs.getString("cin"));
            }else{
                return  new Utilisateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("username"), rs.getString("password"),rs.getString("cin"));
            }
        }
        return null;
    }
}
