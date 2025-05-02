package fsiAdministration.controllers;

import fsiAdministration.BO.Etudiant;
import fsiAdministration.DAO.EtudiantDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SupprimerEtudiantController extends MenuController implements Initializable {

    @FXML
    private ListView<Etudiant> lvEtudiants;
    @FXML
    private Button bSupprimer;
    @FXML
    private Button bAnnuler;
    @FXML
    private Button bRetour;

    private Etudiant etudiant;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EtudiantDAO etudiantDAO = new EtudiantDAO();
        List<Etudiant> etudiantList = etudiantDAO.findAll();
        ObservableList<Etudiant> observableList = FXCollections.observableArrayList(etudiantList);
        lvEtudiants.setItems(observableList);
    }

    @FXML
    public void bSupprimerClick(ActionEvent event) {
        Etudiant selectedEtudiant = lvEtudiants.getSelectionModel().getSelectedItem();
        if (selectedEtudiant == null) {
            // Alerte si aucun étudiant n'est sélectionné
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText("Aucun étudiant sélectionné");
            alert.setContentText("Veuillez sélectionner un étudiant avant de supprimer.");
            alert.showAndWait();
            return;
        }

        EtudiantDAO etudiantDAO = new EtudiantDAO();
        boolean controle = etudiantDAO.delete(selectedEtudiant);

        if (controle) {
            // Alerte de succès
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Suppression réussie");
            alert.setContentText("L'étudiant " + selectedEtudiant.getNomEtudiant() + " a été supprimé avec succès.");
            alert.showAndWait();

            // Rafraîchir la liste des étudiants après suppression
            lvEtudiants.getItems().remove(selectedEtudiant);
        } else {
            // Alerte d'échec
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Échec de la suppression");
            alert.setContentText("Une erreur est survenue lors de la suppression de l'étudiant.");
            alert.showAndWait();
        }
    }

    @FXML
    public void bAnnulerClick(ActionEvent event) {
        // Réinitialiser la sélection
        lvEtudiants.getSelectionModel().clearSelection();
    }

    @FXML
    public void bRetourClick(ActionEvent event) {
        // Fermer la fenêtre de suppression
        Stage stage = (Stage) bRetour.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void bListeEtudClick(ActionEvent event) {
        // Gérer l'affichage de la liste des étudiants
        System.out.println("Liste des étudiants affichée");
    }

    @FXML
    public void bAjouterEtudClick(ActionEvent event) {
        // Gérer l'ajout d'un étudiant
        System.out.println("Formulaire d'ajout d'étudiant");
    }

    @FXML
    public void bListeSectionClick(ActionEvent event) {
        // Gérer l'affichage de la liste des sections
        System.out.println("Liste des sections affichée");
    }

    @FXML
    public void bAjouterSectionClick(ActionEvent event) {
        // Gérer l'ajout d'une section
        System.out.println("Formulaire d'ajout de section");
    }

    @FXML
    public void bAccueilClick(ActionEvent event) {
        // Retour à l'accueil
        System.out.println("Retour à l'accueil");
    }

    @FXML
    public void bQuitterClick(ActionEvent event) {
        // Quitter l'application
        System.exit(0);
    }

    @FXML
    public void handleEtudiantSelection() {
        etudiant = lvEtudiants.getSelectionModel().getSelectedItem();
        if (etudiant != null) {
            // Si un étudiant est sélectionné, on peut afficher ses informations
            System.out.println("Étudiant sélectionné : " + etudiant.getNomEtudiant());
        }
    }
}
