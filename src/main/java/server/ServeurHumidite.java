package server;

import dao.CapteurUtile;
import util.ConnectionBD;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jonas
 */
public class ServeurHumidite extends Thread {

    public void run() {
        try {
            ServerSocket sersoc = new ServerSocket(3000);
            while (true) {
                Socket soc = sersoc.accept();
                ServeurHumidite.SocketThread st = new ServeurHumidite.SocketThread(soc);
                Thread t = new Thread(st);
                t.start();
            }

        } catch (IOException ex) {
            Logger.getLogger(ServeurHumidite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Classe interne
    class SocketThread implements Runnable {

        private Socket soc;
        Connection con = ConnectionBD.con;
        private CapteurUtile capteurDAO = new CapteurUtile(con);


        public SocketThread(Socket soc) throws SQLException {
            this.soc = soc;
        }

        public void run() {
            OutputStream streamOut = null;
            InputStream streamIn = null;
            try {
                while (true) {
                    streamIn = soc.getInputStream();
                    BufferedReader entree = new BufferedReader(new InputStreamReader(streamIn));
                    Thread.sleep(30000);
                    String data = entree.readLine();
                    if (data != null) {
                        float humidite = Float.parseFloat(data);
                        //Traitement à realiser
                        // capteurDAO.majCapteur(humudite)
                    } else {
                        break;
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(ServeurHumidite.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    streamOut.close();
                    streamIn.close();
                } catch (IOException ex) {
                    Logger.getLogger(ServeurHumidite.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
