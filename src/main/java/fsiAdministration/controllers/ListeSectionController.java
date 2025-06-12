package fsiAdministration.controllers;

import fsiAdministration.BO.Section;
import fsiAdministration.DAO.EtudiantDAO;
import fsiAdministration.DAO.SectionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListeSectionController extends MenuController implements Initializable {

    @FXML
    private TableView<SectionTableItem> tvSections;

    @FXML
    private TableColumn<SectionTableItem, String> tcLibelleSection;

    @FXML
    private TableColumn<SectionTableItem, Integer> tcNombreEtudiants;

    private SectionDAO sectionDAO = new SectionDAO();
    private EtudiantDAO etudiantDAO = new EtudiantDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurer les colonnes
        tcLibelleSection.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        tcNombreEtudiants.setCellValueFactory(new PropertyValueFactory<>("nombreEtudiants"));

        chargerSections();
    }

    private void chargerSections() {
        List<Section> sections = sectionDAO.findAll();

        ObservableList<SectionTableItem> data = FXCollections.observableArrayList();

        for (Section section : sections) {
            int nbEtudiants = etudiantDAO.getNombreEtudiantsDansSection(section.getIdSection());
            data.add(new SectionTableItem(section.getLibelleSection(), nbEtudiants));
        }

        tvSections.setItems(data);
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

        Stage currentStage = (Stage) tvSections.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    public void bSupprimerSectionClick() {
        // Supprimer fonctionnel uniquement si tu ajoutes la sélection dans TableView (à adapter)
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

    // Classe interne pour afficher les données dans le TableView
    public static class SectionTableItem {
        private final String libelle;
        private final Integer nombreEtudiants;

        public SectionTableItem(String libelle, Integer nombreEtudiants) {
            this.libelle = libelle;
            this.nombreEtudiants = nombreEtudiants;
        }

        public String getLibelle() {
            return libelle;
        }

        public Integer getNombreEtudiants() {
            return nombreEtudiants;
        }
    }
}
