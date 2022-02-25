package model;

public class Utilisateur {
    int idUser; // l'id primaire de la table user
    String nom;
    String prenom;
    String username;
    String password;
    String cin;
    String type="utilisateur";
    public Utilisateur() {

    }

    public Utilisateur(int idUser, String nom, String prenom, String username, String password, String cin) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.password = password;
        this.cin = cin;
    }
    public Utilisateur( String nom, String prenom, String username, String password, String cin) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
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

    public String getUsername() {
        return username;
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

    public void setUsername(String email) {
        this.username = username;
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
                ", username='" + username + '\'' +
                '}';
    }
}


