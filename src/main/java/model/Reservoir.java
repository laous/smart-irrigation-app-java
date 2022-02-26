package model;

/**
 * @author Jonas
 */
public class Reservoir {

    String code_reservoir;
    float niveau;
    String zone;

    public Reservoir(String code_reservoir,String zone) {
        this.code_reservoir = code_reservoir;
        this.zone = zone;
    }

    public Reservoir(String code_reservoir, float niveau,String zone) {
        this.code_reservoir = code_reservoir;
        this.niveau = niveau;
        this.zone = zone;
    }

    public String getCode() {
        return code_reservoir;
    }

    public void setCode_reservoir(String code_reservoir) {
        this.code_reservoir = code_reservoir;
    }

    public float getNiveau() {
        return niveau;
    }

    public void setNiveau(float niveau) {
        this.niveau = niveau;
    }

    public String getCode_reservoir() {
        return code_reservoir;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
