package fsiAdministration.controllers;

import fsiAdministration.BO.Section;
import fsiAdministration.DAO.SectionDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CoursSectionController extends MenuController implements Initializable {

    public Button bRetour;
    @FXML
    private ListView<Section> lvSections; // ListView pour les sections
    @FXML
    private ListView<String> lvCours;     // ListView pour les cours

    private SectionDAO sectionDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sectionDAO = new SectionDAO();
        chargerSections();
    }

    public void bActualiserClick(ActionEvent actionEvent) {
        Section sectionSelectionnee = lvSections.getSelectionModel().getSelectedItem();

        if (sectionSelectionnee != null) {
            int idSection = sectionSelectionnee.getIdSection();
            chargerCours(idSection);
        } else {
            lvCours.getItems().clear();
            lvCours.getItems().add("Veuillez sélectionner une section.");
        }
    }

    public void bRetourClick(ActionEvent actionEvent) {
        retourMenu(actionEvent);
    }

    private void retourMenu(ActionEvent actionEvent) {
        Stage stageP = (Stage) bRetour.getScene().getWindow();
        stageP.close();
    }

    private void chargerSections() {
        lvSections.getItems().clear();
        List<Section> sections = sectionDAO.findAll();
        lvSections.getItems().addAll(sections);
    }

    private void chargerCours(int idSection) {
        lvCours.getItems().clear();
        List<String> cours = sectionDAO.findCoursBySection(idSection);

        if (cours != null && !cours.isEmpty()) {
            lvCours.getItems().addAll(cours);
        } else {
            lvCours.getItems().add("Aucun cours trouvé pour cette section.");
        }
    }
}
