package model;

/**
 * @author Jonas
 */
public class Reservoir {

    String code_reservoir;
    float niveau;

    public Reservoir(String code_reservoir, float niveau) {
        this.code_reservoir = code_reservoir;
        this.niveau = niveau;
    }

    public String getCode_reservoir() {
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
}
