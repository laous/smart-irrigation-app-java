package model;

/**
 * @author Jonas
 */
public class CapteurNiveauEau extends Capteur {

    float niveau;

    public CapteurNiveauEau(String code, String etat, float niveau) {
        super(code, etat);
        this.niveau = niveau;
    }

    public float getNiveau() {
        return niveau;
    }
}
