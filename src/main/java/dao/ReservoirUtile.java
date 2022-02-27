package dao;

import model.CapteurHumidite;
import model.Reservoir;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 * @author Jonas
 */
public class ReservoirUtile {

    private final Connection con;

    public ReservoirUtile(Connection con) throws SQLException {
        this.con = con;
    }


    public Reservoir getReservoirByCode(String code) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from reservoirs where code like '" + code + "'");
        Reservoir r = null;

        while (rs.next()) {
            r = new Reservoir(rs.getString("code"), rs.getFloat("valeur"), rs.getString("zone"));
        }
        return r;
    }

    public Reservoir getReservoirByZone(int zone) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from reservoirs where zone like '" + zone + "'");
        Reservoir r = null;

        while (rs.next()) {
            r = new Reservoir(rs.getString("code"), rs.getFloat("valeur"), rs.getString("zone"));
        }
        return r;
    }

    public LinkedList<Reservoir> getAllReservoirs() throws SQLException {

        LinkedList<Reservoir> reservoirs = new LinkedList<Reservoir>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from reservoirs");

        while (rs.next()) {
            Reservoir r = null;
            r = new Reservoir(rs.getString("code"), rs.getFloat("valeur"), rs.getString("zone"));
            reservoirs.add(r);
        }
        return reservoirs;
    }

    public boolean deleteReservoir(Reservoir r) throws SQLException {
        Statement stmt = con.createStatement();
        String query = "DELETE FROM reservoirs where code like '" + r.getCode() +"'";

        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated > 0;
    }

    public boolean addReservoir(Reservoir r) throws SQLException {
        Statement stmt = con.createStatement();
        String query = "INSERT INTO reservoirs (`code`,`valeur`, `zone`) VALUES" +
                "(" + r.getCode() + "','" + r.getNiveau() + "','" + r.getZone() + "')";

        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated > 0;
    }

    public boolean updateReservoir(Reservoir r) throws SQLException {
        Statement stmt = con.createStatement();
        String query = "UPDATE reservoirs SET valeur='" + r.getNiveau() + "' where code like '" + r.getCode() + "'";

        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated > 0;
    }

    public boolean updateNiveauReservoir(Reservoir r, float niveau) throws SQLException {
        Statement stmt = con.createStatement();
        String query = "UPDATE reservoirs SET valeur=" + niveau + " where code like '" + r.getCode() + "'";

        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated > 0;
    }

    public boolean updateEtatReservoir(Reservoir r, String etat) throws SQLException {
        Statement stmt = con.createStatement();
        String query = "UPDATE reservoirs SET etat=" + etat + " where code like '" + r.getCode() + "'";

        int nbUpdated = stmt.executeUpdate(query);
        return nbUpdated > 0;
    }

    public String getReservoirEtat(Reservoir r) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from reservoirs where code='"+r.getCode()+"'");
        while (rs.next()){
            return rs.getString("etat");
        }
        return "";
    }
}
