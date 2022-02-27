package server;

/**
 * @author Jonas
 */
public class Server {

    public static void main(String[] args) {

//        ServeurHumidite sh = new ServeurHumidite();
//        sh.start();
        ServeurTemperature st = new ServeurTemperature();
        st.start();

//        ServeurReservoir sr = new ServeurReservoir();
//        sr.start();
    }
}
