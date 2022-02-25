package model;

public class Administrateur extends Utilisateur{


    public Administrateur() {
    }

    public Administrateur(int idUser, String nom, String prenom, String email, String password, String cin) {
        super(idUser, nom, prenom, email, password, cin);
    }

    @Override
    public String toString() {
        return "Administrateur{" +
                super.toString();
    }
}
