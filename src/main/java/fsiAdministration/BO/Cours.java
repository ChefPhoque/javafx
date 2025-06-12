package fsiAdministration.BO;

import javafx.beans.property.SimpleStringProperty;


    public class Cours {

        private int idCours;
        private SimpleStringProperty libelleCours;
        private SimpleStringProperty descriptionCours;
        private Section libelleSection;
        private Professeur professeur;
        private int volumeHoraire;

        public Cours(int idCours, String libelleCours, String descriptionCours, Section libelleSection, Professeur professeur, int volumeHoraire) {
            this.idCours = idCours;
            this.libelleCours = new SimpleStringProperty(libelleCours);
            this.descriptionCours = new SimpleStringProperty(descriptionCours);
            this.libelleSection = libelleSection;
            this.professeur = professeur;  // Initialisation
            this.volumeHoraire = volumeHoraire;
        }

        public int getIdCours() {
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

        public Professeur getProfesseur() {
            return professeur;
        }

        public void setProfesseur(Professeur professeur) {
            this.professeur = professeur;
        }

        public int getVolumeHoraire() {
            return volumeHoraire;
        }

        public void setVolumeHoraire(int volumeHoraire) {
            this.volumeHoraire = volumeHoraire;
        }

        @Override
        public String toString() {
            return libelleCours.get();
        }
    }




