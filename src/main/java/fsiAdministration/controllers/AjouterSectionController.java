package fsiAdministration.controllers;

import fsiAdministration.BO.Section;
import fsiAdministration.DAO.SectionDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AjouterSectionController extends MenuController implements Initializable {

    @FXML
    private TextField tfNomSection;

    @FXML
    private Button bRetour;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void bAjouterClick(ActionEvent actionEvent) {
        String nomSection = tfNomSection.getText();

        if (nomSection == null || nomSection.trim().isEmpty()) {
            System.out.println("Le nom de la section est requis !");
            return;
        }

        Section nouvelleSection = new Section(0, nomSection);
        SectionDAO sectionDAO = new SectionDAO();
        boolean success = sectionDAO.create(nouvelleSection);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Section enregistrer");
        alert.setContentText("La section a bien ete enregistrer .");
        alert.showAndWait();
        System.out.println("Section ajoutée ? " + success);
    }

    @FXML
    public void bEffacerClick(ActionEvent actionEvent) {
        tfNomSection.clear();
    }

    @FXML
    public void bRetourClick(ActionEvent actionEvent) throws IOException {
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
