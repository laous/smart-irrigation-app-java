package model;

public class Technicien extends Utilisateur{
    String type="technicien";


    public Technicien() {

    }

    public Technicien(int idUser, String nom, String prenom, String username, String password, String cin) {
        super(idUser, nom, prenom, username, password, cin);
    }
    public Technicien(String nom, String prenom, String username, String password, String cin) {
        super(nom, prenom, username, password, cin);
    }

    @Override
    public String toString() {
        return "Technicien{" + super.toString();
    }
}
