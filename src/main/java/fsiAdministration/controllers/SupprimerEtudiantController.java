package fsiAdministration.controllers;

import fsiAdministration.BO.Etudiant;
import fsiAdministration.DAO.EtudiantDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
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
    public void handleEtudiantSelection() {
        etudiant = lvEtudiants.getSelectionModel().getSelectedItem();
        if (etudiant != null) {
            // Si un étudiant est sélectionné, on peut afficher ses informations
            System.out.println("Étudiant sélectionné : " + etudiant.getNomEtudiant());
        }
    }
}
