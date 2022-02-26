package model;

/**
 * @author Jonas
 */
public class CapteurNiveauEau extends Capteur {

    float niveau;

    public CapteurNiveauEau(String code, String etat,int zone, float niveau) {
        super(code, etat,zone);
        this.niveau = niveau;
    }

    public float getNiveau() {
        return niveau;
    }
}
