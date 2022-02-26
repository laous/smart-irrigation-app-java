package iot;

import model.Capteur;
import model.CapteurTemperature;
import model.Reservoir;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @author Jonas
 */
public class Reservoir1 {

    public static void main(String[] args) throws IOException, InterruptedException {
        Reservoir r = new Reservoir("APS56","2");

        String hote = "127.0.0.1";
        int port = 3001;
        Socket soc = new Socket(hote, port);

        OutputStream streamOut = soc.getOutputStream();
        OutputStreamWriter sortie = new OutputStreamWriter(streamOut);

        // send infos reservoir
        sendInfosReservoir(sortie,r);

        BufferedReader fs = new BufferedReader(new FileReader("D:\\Top Secret\\WebDev\\Java Workspace\\smart-irrigation-app-java\\src\\main\\java\\iot\\temperature1.txt"));
        String data = null;
        while ((data = fs.readLine()) != null) {
            System.out.println(data);
            TimeUnit.SECONDS.sleep(30); // sleep 30 secs
            //l'envoyer au serveur reservoir
            sortie.write(data + "\n");
            sortie.flush(); // forcer l'envoi
        }
    }

    public static void sendInfosReservoir(OutputStreamWriter sortie, Reservoir c) throws IOException {
        sortie.write(c.getCode()+"\n"); // send code
        sortie.flush();
        sortie.write(c.getZone()+"\n"); // send zone
        sortie.flush();
    }
}

