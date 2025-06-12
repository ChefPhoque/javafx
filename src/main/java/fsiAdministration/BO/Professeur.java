package fsiAdministration.BO;

import javafx.beans.property.SimpleStringProperty;

public class Professeur {

    private int id;
    private SimpleStringProperty nom;
    private SimpleStringProperty prenom;
    private SimpleStringProperty courriel;

    public Professeur(int id, String nom, String prenom, String courriel) {
        this.id = id;
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.courriel = new SimpleStringProperty(courriel);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getPrenom() {
        return prenom.get();
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public String getCourriel() {
        return courriel.get();
    }

    public void setCourriel(String courriel) {
        this.courriel.set(courriel);
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public SimpleStringProperty prenomProperty() {
        return prenom;
    }

    public SimpleStringProperty courrielProperty() {
        return courriel;
    }

    @Override
    public String toString() {
        return nom.get() + " " + prenom.get()  ;
    }
}
