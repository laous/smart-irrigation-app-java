package controller;

import dao.CapteurUtile;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Capteur;
import model.CapteurHumidite;
import model.CapteurTemperature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dashboard {


    public Pane zonePane1;

    public Dashboard() throws SQLException {
    }



    class Zone {
        String codeZone;
        String codeTemperature;
        String codeHumidite;
        String codeReservoir;

        public String getCodeZone() {
            return codeZone;
        }

        public String getCodeTemperature() {
            return codeTemperature;
        }

        public String getCodeHumidite() {
            return codeHumidite;
        }

        public String getCodeReservoir() {
            return codeReservoir;
        }

        public Zone(String codeZone, String codeTemperature, String codeHumidite, String codeReservoir) {
            this.codeZone = codeZone;
            this.codeTemperature = codeTemperature;
            this.codeHumidite = codeHumidite;
            this.codeReservoir = codeReservoir;
        }
    }

    Zone zone1 = new Zone("1","T1","H1","R1");
    Zone zone2 = new Zone("2","T2","H2","R2");
    Zone zone3 = new Zone("3","T3","H3","R3");

    CapteurUtile<Capteur> capteurUtile = new CapteurUtile<>(getConnection());

    @FXML
    Label temperatureLabel1;
    @FXML
    Label humiditeLabel1;
    @FXML
    Label reservoirLabel1;


    public void handleCLick(MouseEvent mouseEvent) throws SQLException {
        showValues();
    }


    public void showValues() throws SQLException {
        /* Zone 1 */
        // Temperature
        CapteurTemperature ct =(CapteurTemperature) capteurUtile.getCapteurByCode(zone1.getCodeTemperature());
        Float t1 = ct.getTemperature();
        temperatureLabel1.setText("Temperature = " + t1);
        // Humidite
        CapteurHumidite ch =(CapteurHumidite) capteurUtile.getCapteurByCode(zone1.getCodeHumidite());
        Float h1 = ch.getHumidite();
        humiditeLabel1.setText("Humidite = " + h1);
        zonePane1.setStyle("-fx-background-color:rgba(255, 255, 255, 0.87);");
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





}

