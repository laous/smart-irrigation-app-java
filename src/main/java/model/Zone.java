package model;

import java.util.LinkedList;

/**
 * @author Jonas
 */
public class Zone {

    String code_zone;
//    LinkedList<String> codes_capteur;
    String code_capteur_humidite;
    String code_capteur_temperture;
    String code_capteur_eau;


    public Zone(String code_zone, String code_capteur_humidite, String code_capteur_temperture, String code_capteur_eau) {
        this.code_zone = code_zone;
        this.code_capteur_humidite = code_capteur_humidite;
        this.code_capteur_temperture = code_capteur_temperture;
        this.code_capteur_eau = code_capteur_eau;
    }

    public String getCode_zone() {
        return code_zone;
    }

    public void setCode_zone(String code_zone) {
        this.code_zone = code_zone;
    }

    public String getCode_capteur_humidite() {
        return code_capteur_humidite;
    }

    public void setCode_capteur_humidite(String code_capteur_humidite) {
        this.code_capteur_humidite = code_capteur_humidite;
    }

    public String getCode_capteur_temperture() {
        return code_capteur_temperture;
    }

    public void setCode_capteur_temperture(String code_capteur_temperture) {
        this.code_capteur_temperture = code_capteur_temperture;
    }

    public String getCode_capteur_eau() {
        return code_capteur_eau;
    }

    public void setCode_capteur_eau(String code_capteur_eau) {
        this.code_capteur_eau = code_capteur_eau;
    }
}
