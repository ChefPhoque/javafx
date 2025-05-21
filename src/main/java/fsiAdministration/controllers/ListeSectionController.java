package fsiAdministration.controllers;

import fsiAdministration.BO.Section;
import fsiAdministration.DAO.SectionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListeSectionController extends MenuController implements Initializable {

    @FXML
    private ListView<Section> lvSections;

    private SectionDAO sectionDAO = new SectionDAO();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        chargerSections();
    }

    private void chargerSections() {
        List<Section> sections = sectionDAO.findAll();
        ObservableList<Section> observableSections = FXCollections.observableArrayList(sections);
        lvSections.setItems(observableSections);
    }

    @FXML
    public void bActualiserClick() {
        chargerSections();
    }

    @FXML
    public void bRetourClick() {
        Stage stage = (Stage) lvSections.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void bAjouterSectionClick() {
        System.out.println("Redirection vers ajout de section.");
        // Ici tu pourras ouvrir une nouvelle fenêtre si tu veux
    }

    @FXML
    public void bModifierSectionClick() {
        System.out.println("Redirection vers modification de section.");
        // Ici tu pourras ouvrir une nouvelle fenêtre aussi
    }

    @FXML
    public void bSupprimerSectionClick() {
        Section selectedSection = lvSections.getSelectionModel().getSelectedItem();
        if (selectedSection != null) {
            boolean success = sectionDAO.delete(selectedSection);
            if (success) {
                System.out.println("Section supprimée avec succès !");
                chargerSections();
            } else {
                System.out.println("Erreur lors de la suppression de la section.");
            }
        } else {
            System.out.println("Aucune section sélectionnée.");
        }
    }

    @FXML
    public void bAccueilClick() {
        System.out.println("Retour à l'accueil.");
        // Implémente si besoin la redirection vers accueil
    }

    @FXML
    public void bQuitterClick() {
        System.exit(0);
    }
}
