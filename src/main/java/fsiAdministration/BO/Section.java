package fsiAdministration.BO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Section {
    private SimpleIntegerProperty idSection;
    private SimpleStringProperty libelleSection;

    public Section(int idSection, String libelleSection) {
        this.idSection = new SimpleIntegerProperty(idSection);
        this.libelleSection = new SimpleStringProperty(libelleSection);
    }

    public int getIdSection() {
        return idSection.get();
    }

    public void setIdSection(int idSection) {
        this.idSection.set(idSection);
    }

    public SimpleIntegerProperty idSectionProperty() {
        return idSection;
    }

    public String getLibelleSection() {
        return libelleSection.get();
    }

    public void setLibelleSection(String libelleSection) {
        this.libelleSection.set(libelleSection);
    }

    public SimpleStringProperty libelleSectionProperty() {
        return libelleSection;
    }

    @Override
    public String toString() {
        return libelleSection.get() ;
    }
}
