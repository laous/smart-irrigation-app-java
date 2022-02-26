package model;

/**
 * @author Jonas
 */
public abstract class Capteur {

    private String code;
    private String etat;
    private String zone;
    protected String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    Capteur(String code, String etat, String zone) {
        this.code = code;
        this.etat = etat;
        if(this instanceof CapteurTemperature){
            this.type="temperature";
        }else if(this instanceof  CapteurHumidite){
            this.type="humidite";
        }
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "{" +
                "code='" + code + '\'' +
                ", etat='" + etat + '\'' +
                '}';
    }
}
