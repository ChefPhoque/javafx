package com.example.projet_tutorat_2025;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Bilan2 {

    @SerializedName("id")
    private int id;


    @SerializedName("noteOral")
    private float noteOral;

    @SerializedName("noteDossier")
    private float noteDossier;

    @SerializedName("moyenne")
    private float moyenne;

    @SerializedName("remarque")
    private String remarque;

    @SerializedName("SujetMemoir")
    private String sujetMemoire;

    @SerializedName("idEtu")
    private int idEtudiant;


    public Bilan2(int id, float noteOral, float noteDossier, float moyenne, String remarque, String sujetMemoire, int idEtudiant) {
        this.id = id;

        this.noteOral = noteOral;
        this.noteDossier = noteDossier;
        this.moyenne = moyenne;
        this.remarque = remarque;
        this.sujetMemoire = sujetMemoire;
        this.idEtudiant = idEtudiant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public float getNoteOral() {
        return noteOral;
    }

    public void setNoteOral(float noteOral) {
        this.noteOral = noteOral;
    }

    public float getNoteDossier() {
        return noteDossier;
    }

    public void setNoteDossier(float noteDossier) {
        this.noteDossier = noteDossier;
    }

    public float getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(float moyenne) {
        this.moyenne = moyenne;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getSujetMemoire() {
        return sujetMemoire;
    }

    public void setSujetMemoire(String sujetMemoire) {
        this.sujetMemoire = sujetMemoire;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }
}