package fsiAdministration.controllers;

import fsiAdministration.BO.Etudiant;
import fsiAdministration.BO.Section;
import fsiAdministration.DAO.EtudiantDAO;
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
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AjouterEtudiantController extends MenuController implements Initializable {

    @FXML
    private TextField tfNomEtud, tfPrenomEtud;

    @FXML
    private DatePicker dpDateNaissance;

    @FXML
    private Button bRetour;

    @FXML
    private ListView<Section> lvSectionEtud;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SectionDAO sectionDAO = new SectionDAO();
        List<Section> sectionList = sectionDAO.findAll();
        ObservableList<Section> observableSections = FXCollections.observableArrayList(sectionList);
        lvSectionEtud.setItems(observableSections);
    }

    @FXML
    public void bEnregistrerClick(ActionEvent event) {
        String nom = tfNomEtud.getText();
        String prenom = tfPrenomEtud.getText();
        LocalDate dateNaiss = dpDateNaissance.getValue();
        Section selectedSection = lvSectionEtud.getSelectionModel().getSelectedItem();

        if (selectedSection == null || dateNaiss == null || nom.isEmpty() || prenom.isEmpty()) {
            System.out.println("Veuillez remplir tous les champs !");
            return;
        }

        Etudiant newEtud = new Etudiant(0, nom, prenom, selectedSection, dateNaiss);
        EtudiantDAO etudDAO = new EtudiantDAO();
        boolean controle = etudDAO.create(newEtud);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Etudiant enregistrer");
        alert.setContentText("L etudiant a bien ete enregistrer .");
        alert.showAndWait();
        System.out.println("Étudiant enregistré ? " + controle);
    }

    @FXML
    public void bEffacerClick(ActionEvent event) {
        tfNomEtud.clear();
        tfPrenomEtud.clear();
        dpDateNaissance.setValue(null);
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
}
