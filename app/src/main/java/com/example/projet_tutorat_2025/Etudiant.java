package com.example.projet_tutorat_2025;

import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

public class Etudiant {
    @SerializedName("id")
    private int id;

    @SerializedName("nom")
    private String nom;

    @SerializedName("prenom")
    private String prenom;

    @SerializedName("email")
    private String mail;

    @SerializedName("telephone")
    private String tel;

    @SerializedName("adresse")
    private String adresse;

    @SerializedName("codePostal")
    private String codePostal;

    @SerializedName("ville")
    private String ville;

    @SerializedName("login")
    private String login;

    @SerializedName("nomMai")
    private String nomMaitre;

    @SerializedName("prenomMai")
    private String prenomMaitre;

    @SerializedName("telMai")
    private String telMaitre;

    @SerializedName("nomEnt")
    private String nomEntreprise;

    @SerializedName("adresseEnt")
    private String adresseEntreprise;

    @SerializedName("codePostalEnt")
    private String codePostalEntreprise;

    @SerializedName("villeEnt")
    private String villeEntreprise;

    private boolean success;

    public Etudiant(int id, String nom, String prenom, String mail, String tel, String adresse, String codePostal, String ville, String login,
                    String nomMaitre, String prenomMaitre, String telMaitre, String nomEntreprise, String adresseEntreprise,
                    String codePostalEntreprise, String villeEntreprise) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.tel = tel;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.login = login;
        this.nomMaitre = nomMaitre;
        this.prenomMaitre = prenomMaitre;
        this.telMaitre = telMaitre;
        this.nomEntreprise = nomEntreprise;
        this.adresseEntreprise = adresseEntreprise;
        this.codePostalEntreprise = codePostalEntreprise;
        this.villeEntreprise = villeEntreprise;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getMail() { return mail; }
    public String getTel() { return tel; }
    public String getAdresse() { return adresse; }
    public String getCodePostal() { return codePostal; }
    public String getVille() { return ville; }
    public String getLogin() { return login; }
    public String getNomMaitre() { return nomMaitre; }
    public String getPrenomMaitre() { return prenomMaitre; }
    public String getTelMaitre() { return telMaitre; }
    public String getNomEntreprise() { return nomEntreprise; }
    public String getAdresseEntreprise() { return adresseEntreprise; }
    public String getCodePostalEntreprise() { return codePostalEntreprise; }
    public String getVilleEntreprise() { return villeEntreprise; }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setNomMaitre(String nomMaitre) {
        this.nomMaitre = nomMaitre;
    }

    public void setPrenomMaitre(String prenomMaitre) {
        this.prenomMaitre = prenomMaitre;
    }

    public void setTelMaitre(String telMaitre) {
        this.telMaitre = telMaitre;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public void setAdresseEntreprise(String adresseEntreprise) {
        this.adresseEntreprise = adresseEntreprise;
    }

    public void setCodePostalEntreprise(String codePostalEntreprise) {
        this.codePostalEntreprise = codePostalEntreprise;
    }

    public void setVilleEntreprise(String villeEntreprise) {
        this.villeEntreprise = villeEntreprise;
    }

    @NonNull
    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mail='" + mail + '\'' +
                ", tel='" + tel + '\'' +
                ", adresse='" + adresse + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", login='" + login + '\'' +
                ", nomMaitre='" + nomMaitre + '\'' +
                ", prenomMaitre='" + prenomMaitre + '\'' +
                ", telMaitre='" + telMaitre + '\'' +
                ", nomEntreprise='" + nomEntreprise + '\'' +
                ", adresseEntreprise='" + adresseEntreprise + '\'' +
                ", codePostalEntreprise='" + codePostalEntreprise + '\'' +
                ", villeEntreprise='" + villeEntreprise + '\'' +
                ", success=" + success +
                '}';
    }
}
