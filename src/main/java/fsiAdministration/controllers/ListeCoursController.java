package fsiAdministration.controllers;

import fsiAdministration.BO.Cours;
import fsiAdministration.DAO.CoursDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListeCoursController extends MenuController implements Initializable {

    // Référence au tableau dans le FXML
    @FXML
    private TableView<Cours> tvCours;
    @FXML
    private Button bRetour;


    // Colonnes affichées : libellé et description uniquement
    @FXML
    private TableColumn<Cours, String> tcLibelleCours;

    @FXML
    private TableColumn<Cours, String> tcDescriptionCours;

    /**
     * Initialise les colonnes du tableau et charge les cours depuis la base de données.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Liaison entre les colonnes du FXML et les propriétés de la classe Cours
        tcLibelleCours.setCellValueFactory(new PropertyValueFactory<>("libelleCours"));
        tcDescriptionCours.setCellValueFactory(new PropertyValueFactory<>("descriptionCours"));

        // Appel au DAO pour récupérer la liste des cours
        CoursDAO dao = new CoursDAO();
        List<Cours> coursList = dao.findAll();

        // Conversion de la liste en ObservableList pour affichage
        ObservableList<Cours> observableCours = FXCollections.observableArrayList(coursList);
        tvCours.setItems(observableCours);
    }

    public void bRetourClick(ActionEvent actionEvent) {
        Stage stageP = (Stage) bRetour.getScene().getWindow();
        stageP.close();
    }

    public void bActualiserClick(ActionEvent actionEvent) {
        CoursDAO dao = new CoursDAO();
        List<Cours> coursList = dao.findAll();
        // Conversion de la liste en ObservableList pour affichage
        ObservableList<Cours> observableCours = FXCollections.observableArrayList(coursList);
        tvCours.setItems(observableCours);
    }
}
