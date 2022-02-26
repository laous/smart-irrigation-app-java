package dao;

/**
 * @author Jonas
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import model.Capteur;
import model.CapteurHumidite;
import model.CapteurTemperature;


public class CapteurUtile<T extends Capteur> {

    private final Connection con;

    public CapteurUtile(Connection con) throws SQLException {
        this.con = con;
    }

    public T getCapteurByZone(int zone) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from capteurs where zone like '" + zone + "'");
        T c = null;

        while (rs.next()) {
            if (rs.getString("type").equals("humidite")) {
                c = (T) new CapteurHumidite(rs.getString("etat"), rs.getString("code"), rs.getInt("zone"), rs.getFloat("valeur"));
                return c;

            } else if (rs.getString("type").equals("temperature")) {
                c = (T) new CapteurTemperature(rs.getString("etat"), rs.getString("code"), rs.getInt("zone"), rs.getFloat("valeur"));
                return c;
            }
        }
        return null;
    }

    public T getCapteurByCode(String code) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from capteurs where code like '" + code + "'");
        T c = null;

        while (rs.next()) {
            if (rs.getString("type").equals("humidite")) {
                c = (T) new CapteurHumidite(rs.getString("etat"), rs.getString("code"), rs.getInt("zone"), rs.getFloat("valeur"));
                return c;
            } else if (rs.getString("type").equals("temperature")) {
                c = (T) new CapteurTemperature(rs.getString("etat"), rs.getString("code"), rs.getInt("zone"), rs.getFloat("valeur"));
                return c;
            }
        }
        return null;
    }

    public LinkedList<T> getAllCapteurs() throws SQLException {

        LinkedList<T> capteurs = new LinkedList<T>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from capteurs");


        while (rs.next()) {
            T c = null;
            if (rs.getString("type").equals("humidite")) {
                c = (T) new CapteurHumidite(rs.getString("code"), rs.getString("etat"), rs.getInt("zone"), rs.getFloat("valeur"));

            } else if (rs.getString("type").equals("temperature")) {
                c = (T) new CapteurTemperature(rs.getString("code"), rs.getString("etat"), rs.getInt("zone"), rs.getFloat("valeur"));

            }
            capteurs.add(c);
        }
        return capteurs;
    }

    public boolean ajouterCapteur(T c) throws SQLException {

        if (getCapteurByCode(c.getCode()) != null) {
            return false;
        }

        Statement stmt = con.createStatement();
        String query = "";

        if (c instanceof CapteurHumidite) {
            System.out.println("here");
            query = "INSERT INTO capteurs (`code`,`etat`, `zone`, `type`) VALUES" +
                    "('" + c.getCode() + "','" + c.getEtat() + "','" + c.getZone() + "','humidite')";
        } else if (c instanceof CapteurTemperature) {
            query = "INSERT INTO capteurs (`code`,`etat`, `zone`,`type`) VALUES" +
                    "('" + c.getCode() + "','" + c.getEtat() + "','" + c.getZone()+ "','temperature')";
        }

        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated > 0;
    }

    public boolean supprimerCapteur(T c) throws SQLException {
        Statement stmt = con.createStatement();
        String query = "";

        if (c instanceof CapteurHumidite) {
            query = "DELETE FROM capteurs where code like '" + c.getCode() + "' and type like 'humidite'";
        } else if (c instanceof CapteurTemperature) {
            query = "DELETE FROM capteurs where code like '" + c.getCode() + "' and type like 'temperature'";
        }

        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated > 0;
    }

    public boolean majCapteur(T c) throws SQLException {
        Statement stmt = con.createStatement();
        String query = "";

        if (c instanceof CapteurHumidite) {
            query = "UPDATE capteurs SET etat='" + c.getEtat() + "', zone='"+c.getZone()+"'  where code like '" + c.getCode() + "' and type like 'humidite'";
        } else if (c instanceof CapteurTemperature) {
            query = "UPDATE capteurs SET etat='" + c.getEtat() + "', zone='"+c.getZone()+"' where code like '" + c.getCode() + "' and type like 'temperature'";
        }
        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated > 0;
    }

    public boolean updateCapteurValue(T c) throws SQLException {
        Statement stmt = con.createStatement();
        String query = "";

        if (c instanceof CapteurHumidite) {
            query = "UPDATE capteurs SET valeur='" + ((CapteurHumidite) c).getHumidite() +"' where code like '" + c.getCode() + "' and type like 'humidite'";
        } else if (c instanceof CapteurTemperature) {
            query = "UPDATE capteurs SET valeur='" + ((CapteurTemperature) c).getTemperature() +"' where code like '" + c.getCode() + "' and type like 'temperature'";
        }
        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated > 0;
    }

}
