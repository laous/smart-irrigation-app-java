package server;

import dao.CapteurUtile;
import dao.ReservoirUtile;
import model.Reservoir;
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

public class ServeurReservoir extends Thread {

    public void run() {
        try {
            ServerSocket sersoc = new ServerSocket(3001);
            while (true) {
                Socket soc = sersoc.accept();
                ServeurReservoir.SocketThread st = new ServeurReservoir.SocketThread(soc);
                Thread t = new Thread(st);
                t.start();
            }

        } catch (IOException | SQLException ex) {
            Logger.getLogger(ServeurReservoir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Classe interne
    class SocketThread implements Runnable {

        private Socket soc;
        Connection con = ConnectionBD.con;
        ReservoirUtile resDAO;


        public SocketThread(Socket soc) throws SQLException {
            this.soc = soc;
            this.resDAO = new ReservoirUtile(con);
        }

        public void run() {
            OutputStream streamOut = null;
            InputStream streamIn = null;

            try {
                streamIn = soc.getInputStream();
                BufferedReader entree = new BufferedReader(new InputStreamReader(streamIn));
                Reservoir r = getInfosReservoir(entree);

                while (true) {

                    Thread.sleep(5000);
                    String data = entree.readLine();
                    if (data != null) {
                        System.out.println(data);
                        float niveau = Float.parseFloat(data);
                        //Traitement à realiser
                        boolean updated = resDAO.updateNiveauReservoir(r, niveau);
                        System.out.println("Updated: " + updated);
                    } else {
                        break;
                    }
                }
            } catch (IOException ex) {
//                Logger.getLogger(ServeurReservoir.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            } finally {
                try {
//                    streamOut.close();
                    streamIn.close();
                } catch (IOException ex) {
                    Logger.getLogger(ServeurReservoir.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        public static Reservoir getInfosReservoir(BufferedReader entree) throws IOException {
            System.out.println("Reading infos");
            String code = entree.readLine(); // get infos code
            String zone = entree.readLine(); // get infos zone

            return new Reservoir(code, zone);
        }
    }
}