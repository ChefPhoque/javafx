package com.example.projet_tutorat_2025;

import com.google.gson.annotations.SerializedName;

public class Bilan1 {

    @SerializedName("id")
    private int id;

    @SerializedName("noteBil")
    private float noteBil;

    @SerializedName("noteOral1")
    private float noteOral1;

    @SerializedName("noteDossier1")
    private float noteDossier1;

    @SerializedName("moyenne")
    private float moyenne;

    @SerializedName("remarque1")
    private String remarque1;

    @SerializedName("idEtu")
    private int idEtu;  // L'attribut dans la classe correspond maintenant à "id_etudiant" dans la table

    public Bilan1(int id, float noteBil, float noteOral1, float noteDossier1, float moyenne, String remarque1, int idEtu) {
        this.id = id;
        this.noteBil = noteBil;
        this.noteOral1 = noteOral1;
        this.noteDossier1 = noteDossier1;
        this.moyenne = moyenne;
        this.remarque1 = remarque1;
        this.idEtu = idEtu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getNoteBil() {
        return noteBil;
    }

    public void setNoteBil(float noteBil) {
        this.noteBil = noteBil;
    }

    public float getNoteOral1() {
        return noteOral1;
    }

    public void setNoteOral1(int noteOral1) {
        this.noteOral1 = noteOral1;
    }

    public float getNoteDossier1() {
        return noteDossier1;
    }

    public void setNoteDossier1(float noteDossier1) {
        this.noteDossier1 = noteDossier1;
    }

    public float getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(float moyenne) {
        this.moyenne = moyenne;
    }

    public String getRemarque1() {
        return remarque1;
    }

    public void setRemarque1(String remarque1) {
        this.remarque1 = remarque1;
    }

    public int getIdEtu() {
        return idEtu;
    }

    public void setIdEtu(int idEtu) {
        this.idEtu = idEtu;
    }
}
