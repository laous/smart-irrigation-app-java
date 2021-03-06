package model;

/**
 * @author Jonas
 */
public class CapteurHumidite extends Capteur{


    float humidite;

    public CapteurHumidite(String code, String etat, String zone){
        super(code, etat, zone);
        this.type ="humidite";
    }

    public CapteurHumidite(String code,String etat,String zone, float humudite) {
        super(code,etat,zone);
        this.humidite = humudite;
        this.type ="humidite";
    }

    public float getHumidite() {
        return humidite;
    }

    public void setHumidite(float humidite) {
        this.humidite = humidite;
    }
}
