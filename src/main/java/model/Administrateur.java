package model;

public class Administrateur extends Utilisateur{
    String type="admin";
    public Administrateur() {

    }

    public Administrateur(int idUser, String nom, String prenom, String username, String password, String cin) {
        super(idUser, nom, prenom, username, password, cin);
    }
    public Administrateur(String nom, String prenom, String username, String password, String cin) {
        super(nom, prenom, username, password, cin);
    }



    @Override
    public String toString() {
        return "Administrateur{" +
                super.toString();
    }
}
