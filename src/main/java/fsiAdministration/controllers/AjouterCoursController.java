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
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AjouterCoursController extends MenuController implements Initializable {

    @FXML
    private ListView<Section> lvSections;

    @FXML
    private TextField tfLibelleCours, tfDescriptionCours;

    @FXML
    private Button bRetour;

    // Méthode d'initialisation
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SectionDAO sectionDAO = new SectionDAO();
        List<Section> sectionList = sectionDAO.findAll();
        ObservableList<Section> observableSections = FXCollections.observableArrayList(sectionList);
        lvSections.setItems(observableSections);

        // Ajout d'un listener pour récupérer la section sélectionnée
    }

    @FXML
    public void bEnregistrerClick(ActionEvent event) {
        // Code pour enregistrer le cours
        String libelle = tfLibelleCours.getText();
        String description = tfDescriptionCours.getText();
        Section selectedSection = lvSections.getSelectionModel().getSelectedItem();

        if (libelle.isEmpty() || description.isEmpty()) {
            System.out.println("Veuillez remplir tous les champs !");
            return;
        }

        Cours cours = new Cours(0,libelle,description,selectedSection);
        CoursDAO coursDAO = new CoursDAO();
        Boolean controle = coursDAO.create(cours);
        System.out.println("Enregistrement du cours : " + controle);
    }

    @FXML
    public void bEffacerClick(ActionEvent event) {
        tfLibelleCours.clear();
        tfDescriptionCours.clear();
    }

    @FXML
    public void bRetourClick(ActionEvent event) {
        Stage stage = (Stage) bRetour.getScene().getWindow();
        stage.close();
    }
}
