package model;

public class Utilisateur {
    int idUser; // l'id primaire de la table user
    String nom;
    String prenom;
    String email;
    String password;
    String cin;

    public Utilisateur() {

    }

    public Utilisateur(int idUser, String nom, String prenom, String email, String password, String cin) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.cin = cin;
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

    public String getPassword() {
        return password;
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
        return " " +
                "idUser=" + idUser +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", cin='" + cin + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}


