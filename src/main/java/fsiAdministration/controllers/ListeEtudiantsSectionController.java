package fsiAdministration.controllers;

import fsiAdministration.DAO.SectionDAO;
import fsiAdministration.BO.Section;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListeEtudiantsSectionController extends MenuController implements Initializable {

    public Button bRetour;
    @FXML
    private ListView<Section> lvSections; // ListView pour les sections
    @FXML
    private ListView<String> lvEtudiants; // ListView pour les étudiants

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
            chargerEtudiants(idSection);
        } else {
            lvEtudiants.getItems().clear();
            lvEtudiants.getItems().add("Veuillez sélectionner une section.");
        }
    }

    public void bRetourClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_accueil.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Accueil");
        stage.setScene(new Scene(root));
        stage.show();
        Stage currentStage = (Stage) ((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow();
        currentStage.close();
    }


    private void chargerSections() {
        lvSections.getItems().clear();
        List<Section> sections = sectionDAO.findAll();
        lvSections.getItems().addAll(sections);
    }

    private void chargerEtudiants(int idSection) {
        lvEtudiants.getItems().clear();
        List<String> etudiants = sectionDAO.findEtudiantsBySection(idSection);

        if (etudiants != null && !etudiants.isEmpty()) {
            lvEtudiants.getItems().addAll(etudiants);
        } else {
            lvEtudiants.getItems().add("Aucun étudiant trouvé pour cette section.");
        }
    }
}
