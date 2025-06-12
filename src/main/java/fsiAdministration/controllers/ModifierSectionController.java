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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ModifierSectionController extends MenuController implements Initializable {

    @FXML
    private ListView<Section> lvSections;

    @FXML
    private TextField tfIdSection, tfLibelleSection;

    @FXML
    private Button bModifier, bAnnuler, bRetour;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SectionDAO sectionDAO = new SectionDAO();
        List<Section> sectionList = sectionDAO.findAll();
        ObservableList<Section> observableSections = FXCollections.observableArrayList(sectionList);
        lvSections.setItems(observableSections);

        lvSections.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                tfIdSection.setText(String.valueOf(newVal.getIdSection()));
                tfLibelleSection.setText(newVal.getLibelleSection());
            }
        });
    }

    @FXML
    public void bModifierClick(ActionEvent event) {
        Section selectedSection = lvSections.getSelectionModel().getSelectedItem();
        if (selectedSection != null) {
            String nouveauLibelle = tfLibelleSection.getText();
            if (nouveauLibelle.isEmpty()) {
                System.out.println("Le libellé ne peut pas être vide.");
                return;
            }

            selectedSection.setLibelleSection(nouveauLibelle);
            SectionDAO sectionDAO = new SectionDAO();
            boolean success = sectionDAO.update(selectedSection);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Section Modifier");
            alert.setContentText("La section a bien ete modifier .");
            alert.showAndWait();
            System.out.println("Modification réussie ? " + success);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText("Section non modifier");
            alert.setContentText("La section n a pas ete modifier .");
            alert.showAndWait();
            System.out.println("Aucune section sélectionnée.");
        }
    }

    @FXML
    public void bAnnulerClick(ActionEvent event) {
        tfIdSection.clear();
        tfLibelleSection.clear();
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
    public void bListeSectionClick(ActionEvent event) {
        // Redirige vers la liste des sections (implémenter si besoin)
        System.out.println("Redirection vers la liste des sections.");
    }

    @FXML
    public void bAjouterSectionClick(ActionEvent event) {
        // Redirige vers l'ajout de section (implémenter si besoin)
        System.out.println("Redirection vers l'ajout de section.");
    }

    @FXML
    public void bAccueilClick(ActionEvent event) {
        // Retour à l'accueil (implémenter si besoin)
        System.out.println("Retour à l'accueil.");
    }

    @FXML
    public void bQuitterClick(ActionEvent event) {
        System.exit(0);
    }
}
