package controller;

import dao.CapteurUtile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Capteur;
import model.CapteurHumidite;
import model.CapteurTemperature;
import view.HelloApplication;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class Dashboard {


    public Pane zonePane1;
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

    @FXML
    Label temperatureLabel1;
    @FXML
    Label humiditeLabel1;
    @FXML
    Label reservoirLabel1;
    @FXML
    Label temperatureLabel2;
    @FXML
    Label humiditeLabel2;
    @FXML
    Label reservoirLabel2;
    @FXML
    Label temperatureLabel3;
    @FXML
    Label humiditeLabel3;
    @FXML
    Label reservoirLabel3;


    public void handleCLick(MouseEvent mouseEvent) throws SQLException {
        // do it every n seconds
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    showValues();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, 0, 1000);
        showValues();
    }


    public void showValues() throws SQLException {
        /* Zone 1 */
        // Temperature
        CapteurTemperature ct1 =(CapteurTemperature) capteurUtile.getCapteurByCode(zone1.getCodeTemperature());
        Float t1 = ct1.getTemperature();
        temperatureLabel1.setText("Temperature = " + t1);
        // Humidite
        CapteurHumidite ch1 =(CapteurHumidite) capteurUtile.getCapteurByCode(zone1.getCodeHumidite());
        Float h1 = ch1.getHumidite();
        humiditeLabel1.setText("Humidite = " + h1);
        if(h1<15){
            // irrigation lance
            // change bg
        }else{
            // irrigation stope
            // change bg
        }
        zonePane1.setStyle("-fx-background-color:rgba(255, 255, 255, 0.87);");

        /* Zone 2 */
        // Temperature
        CapteurTemperature ct2 =(CapteurTemperature) capteurUtile.getCapteurByCode(zone2.getCodeTemperature());
        Float t2 = ct2.getTemperature();
        temperatureLabel2.setText("Temperature = " + t2);
        // Humidite
        CapteurHumidite ch2 =(CapteurHumidite) capteurUtile.getCapteurByCode(zone2.getCodeHumidite());
        Float h2 = ch2.getHumidite();
        humiditeLabel2.setText("Humidite = " + h2);
        zonePane1.setStyle("-fx-background-color:rgba(255, 255, 255, 0.87);");

        /* Zone 3 */
        // Temperature
        CapteurTemperature ct3 =(CapteurTemperature) capteurUtile.getCapteurByCode(zone3.getCodeTemperature());
        Float t3 = ct3.getTemperature();
        temperatureLabel3.setText("Temperature = " + t3);
        // Humidite
        CapteurHumidite ch3 =(CapteurHumidite) capteurUtile.getCapteurByCode(zone3.getCodeHumidite());
        Float h3 = ch3.getHumidite();
        humiditeLabel3.setText("Humidite = " + h3);
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

    public void goBack(ActionEvent actionEvent) throws IOException {
        m.changeScene("login.fxml");
    }



}

