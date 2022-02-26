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
public class Temperature1 {

    public static void main(String[] args) throws IOException, InterruptedException {

        Capteur c = new CapteurTemperature("APS56","A","2", (float) 7.98);

        String hote = "127.0.0.1";
        int port = 3003;
        Socket soc = new Socket(hote, port);

        OutputStream streamOut = soc.getOutputStream();
        OutputStreamWriter sortie = new OutputStreamWriter (streamOut) ;

        BufferedReader fs = new BufferedReader(new FileReader("D:\\Top Secret\\WebDev\\Java Workspace\\smart-irrigation-app-java\\src\\main\\java\\iot\\temperature1.txt"));

        String data= null;
        while((data = fs.readLine()) != null){
            System.out.println(data);
            TimeUnit.SECONDS.sleep(30); // sleep 30 secs
            //l'envoyer au serveur temperature
            sortie.write(data+"\n");
            sortie.flush(); // forcer l'envoi
        }
    }
}
