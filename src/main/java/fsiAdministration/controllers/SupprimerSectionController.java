package fsiAdministration.controllers;

import fsiAdministration.BO.Section;
import fsiAdministration.DAO.SectionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SupprimerSectionController extends MenuController implements Initializable {

    @FXML
    private ListView<Section> lvSections;
    @FXML
    private Button bSupprimer;
    @FXML
    private Button bAnnuler;
    @FXML
    private Button bRetour;

    private Section section;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SectionDAO sectionDAO = new SectionDAO();
        List<Section> sectionList = sectionDAO.findAll();
        ObservableList<Section> observableList = FXCollections.observableArrayList(sectionList);
        lvSections.setItems(observableList);
    }

    @FXML
    public void bSupprimerClick(ActionEvent event) {
        Section selectedSection = lvSections.getSelectionModel().getSelectedItem();
        if (selectedSection == null) {
            // Alerte si aucune section n'est sélectionnée
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText("Aucune section sélectionnée");
            alert.setContentText("Veuillez sélectionner une section avant de supprimer.");
            alert.showAndWait();
            return;
        }

        SectionDAO sectionDAO = new SectionDAO();
        boolean controle = sectionDAO.delete(selectedSection);

        if (controle) {
            // Alerte de succès
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Suppression réussie");
            alert.setContentText("La section \"" + selectedSection.getLibelleSection() + "\" a été supprimée avec succès.");
            alert.showAndWait();

            // Rafraîchir la liste des sections après suppression
            lvSections.getItems().remove(selectedSection);
        } else {
            // Alerte d'échec
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Échec de la suppression");
            alert.setContentText("Une erreur est survenue lors de la suppression de la section.");
            alert.showAndWait();
        }
    }

    @FXML
    public void bAnnulerClick(ActionEvent event) {
        // Réinitialiser la sélection
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
    public void bQuitterClick(ActionEvent event) {
        // Quitter l'application
        System.exit(0);
    }

    @FXML
    public void handleSectionSelection() {
        section = lvSections.getSelectionModel().getSelectedItem();
        if (section != null) {
            // Si une section est sélectionnée, on peut afficher ses informations
            System.out.println("Section sélectionnée : " + section.getLibelleSection());
        }
    }
}
