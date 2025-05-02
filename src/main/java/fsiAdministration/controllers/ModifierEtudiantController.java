package fsiAdministration.controllers;

import fsiAdministration.BO.Etudiant;
import fsiAdministration.BO.Section;
import fsiAdministration.DAO.EtudiantDAO;
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

public class ModifierEtudiantController extends MenuController implements Initializable {

    @FXML
    private TextField tfNomEtud, tfPrenomEtud;
    @FXML
    private ListView<Section> lvSectionEtud;
    @FXML
    private ListView<Etudiant> lvEtudiant;
    @FXML
    private Button bRetour;

    private Etudiant etudiant;
    @FXML
    private DatePicker dpDateNaissEtud;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SectionDAO sectionDAO = new SectionDAO();
        List<Section> sectionList = sectionDAO.findAll();
        ObservableList<Section> observableSections = FXCollections.observableArrayList(sectionList);
        lvSectionEtud.setItems(observableSections);

        EtudiantDAO etudDAO = new EtudiantDAO();
        List<Etudiant> etudiantList = etudDAO.findAll();
        ObservableList<Etudiant> observableList = FXCollections.observableArrayList(etudiantList);
        lvEtudiant.setItems(observableList);
        System.out.println(observableList);
    }



    @FXML
    public void bModifierClick(ActionEvent event) {
        String nom = tfNomEtud.getText();
        String prenom = tfPrenomEtud.getText();
        Section selectedSection = lvSectionEtud.getSelectionModel().getSelectedItem();

        if (selectedSection == null) {
            System.out.println("Veuillez sélectionner une section !");
            return;
        }
        if (etudiant == null) {
            System.out.println("Veuillez sélectionner un étudiant !");
            return;
        }
        etudiant.setNomEtudiant(nom);
        etudiant.setPrenomEtudiant(prenom);
        etudiant.setSection(selectedSection);
        etudiant.setDateNaissEtudiant(dpDateNaissEtud.getValue());


        EtudiantDAO etudDAO = new EtudiantDAO();
        boolean controle = etudDAO.update(etudiant);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Etudiant modifier");
        alert.setContentText("L'etudiant a bien ete modifier .");
        alert.showAndWait();
        System.out.println("Modification réussie : " + controle);
    }

    @FXML
    public void bAnnulerClick(ActionEvent event) {
        tfNomEtud.clear();
        tfPrenomEtud.clear();
        lvSectionEtud.getSelectionModel().clearSelection();
    }

    @FXML
    public void bRetourClick(ActionEvent event) {
        Stage stageP = (Stage) bRetour.getScene().getWindow();
        stageP.close();
    }

    @FXML
    public void bListEtudClick(ActionEvent event) {
        Etudiant selectedEtudiant = lvEtudiant.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void bAjouterEtudClick(ActionEvent event) {
        System.out.println("Afficher le formulaire d'ajout d'étudiant");
    }

    @FXML
    public void bListeSectionClick(ActionEvent event) {
        Section selectedSection = lvSectionEtud.getSelectionModel().getSelectedItem();
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

    public void handleEtudiantSelection(MouseEvent mouseEvent) {
        etudiant = lvEtudiant.getSelectionModel().getSelectedItem();
        if (etudiant != null) {
            tfNomEtud.setText(etudiant.getNomEtudiant());
            tfPrenomEtud.setText(etudiant.getPrenomEtudiant());
            lvSectionEtud.getSelectionModel().select(etudiant.getSection());
            dpDateNaissEtud.setValue(etudiant.getDateNaissEtudiant());

        }
    }
}
