package model;

/**
 * @author Jonas
 */
public class CapteurHumidite extends Capteur{

    float humidite;

    public CapteurHumidite(String code,String etat,int zone, float humudite) {
        super(code,etat,zone);
        this.humidite = humudite;
    }

    public float getHumidite() {
        return humidite;
    }

    public void setHumidite(float humidite) {
        this.humidite = humidite;
    }
}
