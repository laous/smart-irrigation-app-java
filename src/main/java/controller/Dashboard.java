package controller;

import dao.CapteurUtile;
import dao.ReservoirUtile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Capteur;
import model.CapteurHumidite;
import model.CapteurTemperature;
import model.Reservoir;
import view.HelloApplication;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class Dashboard {


    public Pane zonePane1 , zonePane2, zonePane3;
    public Button backBtn;

    HelloApplication m = new HelloApplication();

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
    ReservoirUtile reservoirUtile= new ReservoirUtile(getConnection());

    @FXML
    Label temperatureLabel1;
    @FXML
    Label humiditeLabel1;
    @FXML
    Label reservoirLabel1;
    @FXML
    public Label resultat1;
    @FXML
    Label temperatureLabel2;
    @FXML
    Label humiditeLabel2;
    @FXML
    Label reservoirLabel2;
    @FXML
    public Label resultat2;
    @FXML
    Label temperatureLabel3;
    @FXML
    Label humiditeLabel3;
    @FXML
    Label reservoirLabel3;
    @FXML
    public Label resultat3;


    public void handleCLick(MouseEvent mouseEvent) throws SQLException {
        // do it every n seconds
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    showValues();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1000);
    }


    public void showValues() throws SQLException {
        /* Zone 1 */
        // Temperature
        CapteurTemperature ct1 =(CapteurTemperature) capteurUtile.getCapteurByCode(zone1.getCodeTemperature());
        float t1 = ct1.getTemperature();
        temperatureLabel1.setText("Temperature = " + t1);
        // Humidite
        CapteurHumidite ch1 =(CapteurHumidite) capteurUtile.getCapteurByCode(zone1.getCodeHumidite());
        float h1 = ch1.getHumidite();
        humiditeLabel1.setText("Humidite = " + h1);
        // Reservoir
        Reservoir r1 = reservoirUtile.getReservoirByCode("R1");
        if(h1<30){
            // irrigation lance
            resultat1.setText("Irrigation Lance");
            // change bg
            zonePane1.setStyle("-fx-background-color:#65C18C;");
            // reservoir
            reservoirUtile.updateEtatReservoir(r1,"a");
        }else{
            // irrigation stope
            resultat1.setText("Irrigation Arrete");
            // change bg
            zonePane1.setStyle("-fx-background-color:#E0DDAA;");
            // reservoir
            reservoirUtile.updateEtatReservoir(r1,"e");
        }


        /* Zone 2 */
        // Temperature
        CapteurTemperature ct2 =(CapteurTemperature) capteurUtile.getCapteurByCode(zone2.getCodeTemperature());
        float t2 = ct2.getTemperature();
        temperatureLabel2.setText("Temperature = " + t2);
        // Humidite
        CapteurHumidite ch2 =(CapteurHumidite) capteurUtile.getCapteurByCode(zone2.getCodeHumidite());
        float h2 = ch2.getHumidite();
        humiditeLabel2.setText("Humidite = " + h2);
        // Reservoir
        Reservoir r2 = reservoirUtile.getReservoirByCode("R2");
        if(h2<30){
            // irrigation lance
            resultat2.setText("Irrigation Lance");
            // change bg
            zonePane2.setStyle("-fx-background-color:#65C18C;");
            // reservoir
            reservoirUtile.updateEtatReservoir(r2,"a");
        }else{
            // irrigation stope
            resultat2.setText("Irrigation Arrete");
            // change bg
            zonePane2.setStyle("-fx-background-color:#E0DDAA;");
            // reservoir
            reservoirUtile.updateEtatReservoir(r2,"e");
        }

        /* Zone 3 */
        // Temperature
        CapteurTemperature ct3 =(CapteurTemperature) capteurUtile.getCapteurByCode(zone3.getCodeTemperature());
        float t3 = ct3.getTemperature();
        temperatureLabel3.setText("Temperature = " + t3);
        // Humidite
        CapteurHumidite ch3 =(CapteurHumidite) capteurUtile.getCapteurByCode(zone3.getCodeHumidite());
        float h3 = ch3.getHumidite();
        humiditeLabel3.setText("Humidite = " + h3);
        // Reservoir
        Reservoir r3 = reservoirUtile.getReservoirByCode("R3");
        if(h3<30){
            // irrigation lance
            resultat3.setText("Irrigation Lance");
            // change bg
            zonePane3.setStyle("-fx-background-color:#65C18C;");
            // reservoir
            reservoirUtile.updateEtatReservoir(r3,"a");
        }else{
            // irrigation stope
            resultat3.setText("Irrigation Arrete");
            // change bg
            zonePane3.setStyle("-fx-background-color:#E0DDAA;");
            // reservoir
            reservoirUtile.updateEtatReservoir(r3,"e");
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

    public void goBack(ActionEvent actionEvent) throws IOException {
        m.changeScene("login.fxml");
    }



}

