package fsiAdministration.BO;

import javafx.beans.property.SimpleStringProperty;

public class Cours {
    private int idCours;
    private SimpleStringProperty libelleCours;
    private SimpleStringProperty descriptionCours;
    private Section libelleSection;

    public Cours(int idCours, String libelleCours, String descriptionCours, Section libelleSection) {
        this.idCours = idCours;
        this.libelleCours = new SimpleStringProperty(libelleCours);
        this.descriptionCours = new SimpleStringProperty(descriptionCours);
        this.libelleSection = libelleSection;
    }

    public int getIdCours() {
        return idCours;
    }

    public int idCoursProperty() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public String getLibelleCours() {
        return libelleCours.get();
    }

    public SimpleStringProperty libelleCoursProperty() {
        return libelleCours;
    }

    public void setLibelleCours(String libelleCours) {
        this.libelleCours.set(libelleCours);
    }

    public String getDescriptionCours() {
        return descriptionCours.get();
    }

    public SimpleStringProperty descriptionCoursProperty() {
        return descriptionCours;
    }

    public void setDescriptionCours(String descriptionCours) {
        this.descriptionCours.set(descriptionCours);
    }

    public Section getLibelleSection() {
        return libelleSection;
    }

    public void setLibelleSection(Section libelleSection) {
        this.libelleSection = libelleSection;
    }

    @Override
    public String toString() {
        return libelleCours.get();
    }
}
