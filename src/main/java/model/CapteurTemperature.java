package model;

/**
 * @author Jonas
 */
public class CapteurTemperature extends Capteur{

    float temperature;

    public CapteurTemperature(String code,String etat,float temperature) {
        super(code,etat);
        this.temperature = temperature;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}
