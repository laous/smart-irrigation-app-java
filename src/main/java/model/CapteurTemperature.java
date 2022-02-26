package model;

/**
 * @author Jonas
 */
public class CapteurTemperature extends Capteur{

    float temperature;

    public CapteurTemperature(String code,String etat,int zone,float temperature) {
        super(code,etat,zone);
        this.temperature = temperature;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}
