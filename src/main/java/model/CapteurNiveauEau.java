package model;

/**
 * @author Jonas
 */
public class CapteurNiveauEau extends Capteur {

    float niveau;

    CapteurNiveauEau(String code, String etat, float niveau) {
        super(code, etat);
        this.niveau = niveau;
    }
}
