package model;

/**
 * @author Jonas
 */
public class CapteurHumidite extends Capteur{

    float humidite;

    public CapteurHumidite(String code,String etat, float humodite) {
        super(code,etat);
        this.humidite = humodite;
    }

    public float getHumidite() {
        return humidite;
    }

    public void setHumidite(float humidite) {
        this.humidite = humidite;
    }
}
