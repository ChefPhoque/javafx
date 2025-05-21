package fsiAdministration.controllers;

import fsiAdministration.BO.Cours;
import fsiAdministration.BO.Section;
import fsiAdministration.DAO.CoursDAO;
import fsiAdministration.DAO.SectionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ModifierCoursController extends MenuController implements Initializable {

    @FXML
    private TextField tfLibelleCours, tfDescriptionCours;
    @FXML
    private ListView<Cours> lvCours;
    @FXML
    private ListView<Section> lvSections;
    @FXML
    private Button bRetour;

    private Cours cours;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Charger la liste des cours
        CoursDAO coursDAO = new CoursDAO();
        List<Cours> coursList = coursDAO.findAll();
        ObservableList<Cours> observableCours = FXCollections.observableArrayList(coursList);
        lvCours.setItems(observableCours);

        // Charger la liste des sections
        SectionDAO sectionDAO = new SectionDAO();
        List<Section> sectionList = sectionDAO.findAll();
        ObservableList<Section> observableSections = FXCollections.observableArrayList(sectionList);
        lvSections.setItems(observableSections);
    }

    @FXML
    public void bModifierClick(ActionEvent event) {
        // Récupérer les données du formulaire
        String libelle = tfLibelleCours.getText();
        String description = tfDescriptionCours.getText();
        Section selectedSection = lvSections.getSelectionModel().getSelectedItem();

        if (selectedSection == null) {
            System.out.println("Veuillez sélectionner une section !");
            return;
        }
        if (cours == null) {
            System.out.println("Veuillez sélectionner un cours !");
            return;
        }

        // Mettre à jour le cours
        cours.setLibelleCours(libelle);
        cours.setDescriptionCours(description);
        cours.setLibelleSection(selectedSection);

        // Enregistrer la modification dans la base de données
        CoursDAO coursDAO = new CoursDAO();
        boolean controle = coursDAO.update(cours);

        // Affichage de la confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Cours modifié");
        alert.setContentText("Le cours a bien été modifié.");
        alert.showAndWait();
        System.out.println("Modification réussie : " + controle);
    }

    @FXML
    public void bAnnulerClick(ActionEvent event) {
        // Effacer les champs de texte
        tfLibelleCours.clear();
        tfDescriptionCours.clear();
        lvSections.getSelectionModel().clearSelection();
    }

    @FXML
    public void bRetourClick(ActionEvent event) {
        // Fermer la fenêtre actuelle
        Stage stageP = (Stage) bRetour.getScene().getWindow();
        stageP.close();
    }

    @FXML
    public void bListCoursClick(ActionEvent event) {
        Cours selectedCours = lvCours.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void bAjouterCoursClick(ActionEvent event) {
        System.out.println("Afficher le formulaire d'ajout de cours");
    }

    @FXML
    public void bListeSectionClick(ActionEvent event) {
        Section selectedSection = lvSections.getSelectionModel().getSelectedItem();
        System.out.println("Section sélectionnée : " + selectedSection);
    }

    @FXML
    public void bAjouterSectionClick(ActionEvent event) {
        System.out.println("Afficher le formulaire d'ajout de section");
    }

    @FXML
    public void bAccueilClick(ActionEvent event) {
        System.out.println("Retour à l'accueil");
    }

    @FXML
    public void bQuitterClick(ActionEvent event) {
        System.exit(0);
    }

    public void handleCoursSelection(MouseEvent mouseEvent) {
        cours = lvCours.getSelectionModel().getSelectedItem();
        if (cours != null) {
            tfLibelleCours.setText(cours.getLibelleCours());
            tfDescriptionCours.setText(cours.getDescriptionCours());
            lvSections.getSelectionModel().select(cours.getLibelleSection());
        }
    }
}
