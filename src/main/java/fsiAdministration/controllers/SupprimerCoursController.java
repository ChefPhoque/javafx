package fsiAdministration.controllers;

import fsiAdministration.BO.Cours;
import fsiAdministration.DAO.CoursDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SupprimerCoursController extends MenuController implements Initializable {

    @FXML
    private ListView<Cours> lvCours;

    @FXML
    private Button bSupprimer;

    @FXML
    private Button bRetour;

    private Cours coursSelectionne;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CoursDAO coursDAO = new CoursDAO();
        List<Cours> coursList = coursDAO.findAll();
        ObservableList<Cours> observableList = FXCollections.observableArrayList(coursList);
        lvCours.setItems(observableList);
    }

    @FXML
    public void bSupprimerClick(ActionEvent event) {
        Cours selected = lvCours.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText("Aucun cours sélectionné");
            alert.setContentText("Veuillez sélectionner un cours avant de supprimer.");
            alert.showAndWait();
            return;
        }

        CoursDAO coursDAO = new CoursDAO();
        boolean success = coursDAO.delete(selected);

        if (success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Suppression réussie");
            alert.setContentText("Le cours \"" + selected.getLibelleCours() + "\" a été supprimé.");
            alert.showAndWait();

            lvCours.getItems().remove(selected);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Échec de la suppression");
            alert.setContentText("Une erreur est survenue lors de la suppression du cours.");
            alert.showAndWait();
        }
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
        System.exit(0);
    }

    @FXML
    public void handleCoursSelection() {
        coursSelectionne = lvCours.getSelectionModel().getSelectedItem();
        if (coursSelectionne != null) {
            System.out.println("Cours sélectionné : " + coursSelectionne.getLibelleCours());
        }
    }
}