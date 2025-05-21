package fsiAdministration.BO;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Etudiant {

    private int idEtudiant;
    private SimpleStringProperty nomEtudiant;
    private SimpleStringProperty prenomEtudiant;
    private Section section;
    private LocalDate dateNaissEtudiant;


    public Etudiant(int idEtudiant, String nomEtudiant, String prenomEtudiant, Section section, LocalDate dateNaissEtudiant) {
        this.idEtudiant = idEtudiant;
        this.nomEtudiant = new SimpleStringProperty(nomEtudiant);
        this.prenomEtudiant = new SimpleStringProperty(prenomEtudiant);
        this.section = section;
        this.dateNaissEtudiant = dateNaissEtudiant;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getNomEtudiant() {
        return nomEtudiant.get();
    }

    public void setNomEtudiant(String nomEtudiant) {
        this.nomEtudiant.set(nomEtudiant);
    }

    public String getPrenomEtudiant() {
        return prenomEtudiant.get();
    }

    public void setPrenomEtudiant(String prenomEtudiant) {
        this.prenomEtudiant.set(prenomEtudiant);
    }

    public SimpleStringProperty nomEtudiantProperty() {
        return nomEtudiant;
    }

    public SimpleStringProperty prenomEtudiantProperty() {
        return prenomEtudiant;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
    public LocalDate getDateNaissEtudiant() {
        return dateNaissEtudiant;
    }

    public void setDateNaissEtudiant(LocalDate dateNaissEtudiant) {
        this.dateNaissEtudiant = dateNaissEtudiant;
    }

    public ObservableValue<String> dateNaissEtudiantProperty() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Permet de formater une date sur un modele
        String dateStr = (dateNaissEtudiant != null) ? dateNaissEtudiant.format(formatter) : ""; // transformer la date en string
        return new SimpleStringProperty(dateStr);
    }
    @Override
    public String toString() {
        return nomEtudiant.get() + " " + prenomEtudiant.get() + " (" + section.getLibelleSection() + ")";
    }
}
