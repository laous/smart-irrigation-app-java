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
public class ServeurTemperature extends Thread {


    public void run() {
        try {
            ServerSocket sersoc = new ServerSocket(3003);
            while (true) {
                Socket soc = sersoc.accept();
                SocketThread st = new SocketThread(soc);
                Thread t = new Thread(st);
                t.start();
            }

        } catch (IOException | SQLException ex) {
            Logger.getLogger(ServeurTemperature.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Classe interne
    class SocketThread implements Runnable {

        private Socket soc;
        Connection con = ConnectionBD.con;
        private CapteurUtile capDAO = new CapteurUtile(con);


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
                        float temperature = Float.parseFloat(data);
                        //Traitement à realiser
                        // capDAO.majCapteurByTemp(temp)
                    } else {
                        break;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ServeurTemperature.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    streamOut.close();
                    streamIn.close();
                } catch (IOException ex) {
                    Logger.getLogger(ServeurTemperature.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
