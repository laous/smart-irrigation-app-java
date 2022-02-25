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
import model.CapteurNiveauEau;
import model.CapteurTemperature;


public class CapteurUtile<T extends Capteur> {

    private final Connection con;

    public CapteurUtile(Connection con) throws SQLException {
        this.con = con;
    }

    public T getCapteurByCode(String code) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from capteurs where code like '" + code + "'");
        T c = null;

        while (rs.next()) {
            if (rs.getString("type").equals("humidite")) {
                c = (T) new CapteurHumidite(rs.getString("etat"), rs.getString("code"), rs.getFloat("valeur"));

            } else if (rs.getString("type").equals("temperature")) {
                c = (T) new CapteurTemperature(rs.getString("etat"), rs.getString("code"), rs.getFloat("valeur"));

            }else if (rs.getString("type").equals("eau")) {
                c = (T) new CapteurNiveauEau(rs.getString("etat"), rs.getString("code"), rs.getFloat("valeur"));
            }
        }
        return c;
    }

    public LinkedList<T> getAllCapteurs() throws SQLException {

        LinkedList<T> capteurs = new LinkedList<T>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from capteurs");


        while (rs.next()) {
            T c = null;
            if (rs.getString("type").equals("humidite")) {
                c = (T) new CapteurHumidite(rs.getString("etat"), rs.getString("code"), rs.getFloat("valeur"));

            } else if (rs.getString("type").equals("temperature")) {
                c = (T) new CapteurTemperature(rs.getString("etat"), rs.getString("code"), rs.getFloat("valeur"));

            } else if (rs.getString("type").equals("eau")) {
                c = (T) new CapteurTemperature(rs.getString("etat"), rs.getString("code"), rs.getFloat("valeur"));
            }
            capteurs.add(c);
        }
        return capteurs;
    }

    public boolean ajouterCapteur(T c) throws SQLException {

        Statement stmt = con.createStatement();
        String query = "";

        if (c instanceof CapteurHumidite) {
            query = "INSERT INTO capteurs (`code`,`etat`, `valeur`, `type`) VALUES" +
                    "(" + c.getEtat() + "','" + c.getCode() + "','" + ((CapteurHumidite) c).getHumidite() + "','humidite')";
        } else if (c instanceof CapteurTemperature) {
            query = "INSERT INTO capteurs (`code`,`etat`, `valeur`,`type`) VALUES" +
                    "(" + c.getEtat() + "','" + c.getCode() + "','" + ((CapteurTemperature) c).getTemperature() + "','temperature')";
        } else if (c instanceof CapteurNiveauEau) {
            query = "INSERT INTO capteurs (`etat`,`code`, `valeur`,`type`) VALUES" +
                    "(" + c.getEtat() + "','" + c.getCode() + "','" + ((CapteurNiveauEau) c).getNiveau() + "', 'eau')";
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
            query = "DELETE FROM capteurs where code=" + c.getCode() + " and type like 'temperature'";
        } else if (c instanceof CapteurNiveauEau) {
            query = "DELETE FROM capteurs where code=" + c.getCode() + " and type like 'eau'";
        }

        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated > 0;
    }

}
