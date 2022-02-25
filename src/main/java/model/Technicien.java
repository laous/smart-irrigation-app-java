package model;

public class Technicien extends Utilisateur{


    public Technicien() {
    }

    public Technicien(int idUser, String nom, String prenom, String email, String password, String cin) {
        super(idUser, nom, prenom, email, password, cin);
    }

    @Override
    public String toString() {
        return "Technicien{" + super.toString();
    }
}
