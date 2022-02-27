package iot;

import model.Capteur;
import model.CapteurHumidite;
import model.CapteurTemperature;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author Jonas
 */

public class Humidite1 {

    public static void main(String[] args) throws IOException, InterruptedException {

        Capteur c = new CapteurHumidite("H1", "A", "1");

        String hote = "127.0.0.1";
        int port = 3000;
        Socket soc = new Socket(hote, port);

        OutputStream streamOut = soc.getOutputStream();
        OutputStreamWriter sortie = new OutputStreamWriter(streamOut);

        BufferedReader fs = new BufferedReader(new FileReader("D:\\Top Secret\\WebDev\\Java Workspace\\smart-irrigation-app-java\\src\\main\\java\\iot\\humidite1.txt"));

        sendInfosReservoir(sortie,c);

        String data = null;
        while ((data = fs.readLine()) != null) {
            System.out.println(data);
            TimeUnit.SECONDS.sleep(5); // sleep 30 secs
            //l'envoyer au serveur humidite
            sortie.write(data + "\n");
            sortie.flush(); // forcer l'envoi
        }
    }

    public static void sendInfosReservoir(OutputStreamWriter sortie, Capteur c) throws IOException {
        System.out.println("Sending infos");
        sortie.write(c.getCode()+"\n"); // send code
        sortie.flush();
        sortie.write(c.getEtat()+"\n"); // send etat
        sortie.flush();
        sortie.write(c.getZone()+"\n"); // send zone
        sortie.flush();
    }
}
