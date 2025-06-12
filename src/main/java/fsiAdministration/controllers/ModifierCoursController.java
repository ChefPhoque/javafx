package fsiAdministration.controllers;

import fsiAdministration.BO.Cours;
import fsiAdministration.BO.Section;
import fsiAdministration.DAO.CoursDAO;
import fsiAdministration.DAO.SectionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
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
    public void bRetourClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_accueil.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Accueil");
        stage.setScene(new Scene(root));
        stage.show();
        Stage stageP = (Stage) bRetour.getScene().getWindow();
        stageP.close();
    }

    @FXML
    public void bListCoursClick(ActionEvent event) {
        Cours selectedCours = lvCours.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void bAjouterCoursClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/ajouter_cours.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Ajouter un cours");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d’ouvrir le formulaire d’ajout");
            alert.setContentText("Une erreur est survenue lors du chargement de la vue.");
            alert.showAndWait();
        }
    }

    @FXML
    public void bListeSectionClick(ActionEvent event) {
        Section selectedSection = lvSections.getSelectionModel().getSelectedItem();
        System.out.println("Section sélectionnée : " + selectedSection);
    }

    @FXML
    public void bAjouterSectionClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/ajouter_section.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Ajouter une section");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible d’ouvrir le formulaire d’ajout");
            alert.setContentText("Une erreur est survenue lors du chargement de la vue.");
            alert.showAndWait();
        }
    }

    @FXML
    public void bAccueilClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_accueil.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Accueil");
        stage.setScene(new Scene(root));
        stage.show();

        Stage currentStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        currentStage.close();
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
