package fsiAdministration.controllers;

import fsiAdministration.BO.Etudiant;
import fsiAdministration.BO.Section;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListeEtudiantController extends MenuController implements Initializable {

    @FXML private TableView<Etudiant> tvEtudiants;
    @FXML private TableColumn<Etudiant, String> tcNomEtud;
    @FXML private TableColumn<Etudiant, String> tcPrenomEtud;
    @FXML private TableColumn<Etudiant, String> tcDateNaissEtud;
    @FXML public TableColumn<Etudiant, String> tcLibelleSection;
    @FXML
    private Button bRetour;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EtudiantDAO etudDAO = new EtudiantDAO();
        List<Etudiant> mesEtud = etudDAO.findAll();
        ObservableList<Etudiant> mesEtudOL = FXCollections.observableArrayList(mesEtud);

        tcNomEtud.setCellValueFactory(cellData -> cellData.getValue().nomEtudiantProperty());
        tcPrenomEtud.setCellValueFactory(cellData -> cellData.getValue().prenomEtudiantProperty());
        tcDateNaissEtud.setCellValueFactory(cellData -> cellData.getValue().dateNaissEtudiantProperty());
        tcLibelleSection.setCellValueFactory(cellData -> cellData.getValue().getSection().libelleSectionProperty());

        tvEtudiants.setItems(mesEtudOL);
    }



    public void bActualiserClick(ActionEvent actionEvent) {
        EtudiantDAO etudDAO = new EtudiantDAO();
        List<Etudiant> mesEtud = etudDAO.findAll();
        ObservableList<Etudiant> mesEtudOL = FXCollections.observableArrayList(mesEtud);

        // Mise à jour de la TableView
        tvEtudiants.setItems(mesEtudOL);
    }

    public void bRetourClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_accueil.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Accueil");
        stage.setScene(new Scene(root));
        stage.show();
        Stage retour = (Stage) bRetour.getScene().getWindow();
        retour.close();
    }
}
