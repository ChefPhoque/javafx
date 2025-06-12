package fsiAdministration.controllers;

import fsiAdministration.BO.Cours;
import fsiAdministration.BO.Section;
import fsiAdministration.BO.Professeur;
import fsiAdministration.DAO.CoursDAO;
import fsiAdministration.DAO.SectionDAO;
import fsiAdministration.DAO.ProfesseurDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AjouterCoursController extends MenuController implements Initializable {

    @FXML
    private ListView<Section> lvSections;

    @FXML
    private ListView<Professeur> lvProfesseurs;

    @FXML
    private TextField tfLibelleCours, tfDescriptionCours, tfVolumeHoraire;

    @FXML
    private Button bRetour;

    // Méthode d'initialisation
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Charger les sections
        SectionDAO sectionDAO = new SectionDAO();
        List<Section> sectionList = sectionDAO.findAll();
        ObservableList<Section> observableSections = FXCollections.observableArrayList(sectionList);
        lvSections.setItems(observableSections);

        // Charger les professeurs
        ProfesseurDAO professeurDAO = new ProfesseurDAO();
        List<Professeur> professeurList = professeurDAO.findAll();
        ObservableList<Professeur> observableProfesseurs = FXCollections.observableArrayList(professeurList);
        lvProfesseurs.setItems(observableProfesseurs);
    }

    @FXML
    public void bEnregistrerClick(ActionEvent event) {
        String libelle = tfLibelleCours.getText();
        String description = tfDescriptionCours.getText();
        Section selectedSection = lvSections.getSelectionModel().getSelectedItem();
        Professeur selectedProf = lvProfesseurs.getSelectionModel().getSelectedItem();

        int volumeHoraire;
        try {
            volumeHoraire = Integer.parseInt(tfVolumeHoraire.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le volume horaire doit être un nombre entier.");
            alert.showAndWait();
            return;
        }

        if (libelle.isEmpty() || description.isEmpty() || selectedSection == null || selectedProf == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs et sélectionner une section et un professeur.");
            alert.showAndWait();
            return;
        }

        Cours cours = new Cours(0, libelle, description, selectedSection, selectedProf, volumeHoraire);

        CoursDAO coursDAO = new CoursDAO();
        boolean controle = coursDAO.create(cours);

        Alert alert;
        if (controle) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Cours enregistré");
            alert.setContentText("Le cours a bien été enregistré.");
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Échec");
            alert.setHeaderText("Cours non enregistré");
            alert.setContentText("Le cours n'a pas pu être enregistré.");
        }
        alert.showAndWait();
    }

    @FXML
    public void bEffacerClick(ActionEvent event) {
        tfLibelleCours.clear();
        tfDescriptionCours.clear();
        tfVolumeHoraire.clear();
        lvSections.getSelectionModel().clearSelection();
        lvProfesseurs.getSelectionModel().clearSelection();
    }

    @FXML
    public void bRetourClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_accueil.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Accueil");
        stage.setScene(new Scene(root));
        stage.show();

        Stage currentStage = (Stage) bRetour.getScene().getWindow();
        currentStage.close();
    }
}
