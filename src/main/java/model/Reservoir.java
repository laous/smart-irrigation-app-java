package model;

/**
 * @author Jonas
 */
public class Reservoir {

    String code;
    float niveau;
    String zone;
    String etat;

    public Reservoir(String code, String zone) {
        this.code = code;
        this.zone = zone;
    }

    public Reservoir(String code, float niveau, String zone) {
        this.code = code;
        this.niveau = niveau;
        this.zone = zone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code_reservoir) {
        this.code = code_reservoir;
    }

    public float getNiveau() {
        return niveau;
    }

    public void setNiveau(float niveau) {
        this.niveau = niveau;
    }

    public String getZone() {
        return zone;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
