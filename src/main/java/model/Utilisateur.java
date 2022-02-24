package model;

public class Utilisateur {
    int idUser; // l'id primaire de la table user
    String prenom;
    String nom;
    String cin;
    String email;

    public Utilisateur() {

    }

    public Utilisateur(int idUser, String prenom, String nom, String cin, String email) {
        this.idUser = idUser;
        this.prenom = prenom;
        this.nom = nom;
        this.cin = cin;
        this.email = email;
    }

    public Utilisateur(String prenom, String nom, String cin, String email) {
        this.prenom = prenom;
        this.nom = nom;
        this.cin = cin;
        this.email = email;
    }


    public int getIdUser() {
        return idUser;
    }

    public String getNom() {
        return nom;
    }

    public String getCin() {
        return cin;
    }

    public String getEmail() {
        return email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", cin='" + cin + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}


