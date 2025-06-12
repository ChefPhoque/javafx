package fsiAdministration.controllers;

import fsiAdministration.BO.Section;
import fsiAdministration.DAO.SectionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
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
    public void bRetourClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_accueil.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Accueil");
        stage.setScene(new Scene(root));
        stage.show();
        Stage stage1 = (Stage) lvSections.getScene().getWindow();
        stage1.close();
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
    public void bAccueilClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_accueil.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Accueil");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void bQuitterClick() {
        System.exit(0);
    }
}
