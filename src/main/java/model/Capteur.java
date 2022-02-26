package model;

/**
 * @author Jonas
 */
public abstract class Capteur {

    private String code;
    private String etat;
    private int zone;

    Capteur(String code,String etat,int zone) {
        this.code = code;
        this.etat = etat;
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
