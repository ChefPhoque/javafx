package fsiAdministration.controllers;

import fsiAdministration.BO.Utilisateur;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController {


    @FXML
    protected MenuItem bListeEtud, bAjouterEtud, bListeSection, bAjouterSection, bQuitter, bAccueil;
    @FXML
    private Label labelNomUtilisateur;

    private Utilisateur utilisateurConnecte;

    public void setUtilisateurConnecte(Utilisateur utilisateur) { //Permet de récuperé le login de l'utilisateur
        this.utilisateurConnecte = utilisateur;
        if (labelNomUtilisateur != null) {
            labelNomUtilisateur.setText("Bienvenue " + utilisateur.getLoginUtilisateur());
        }
    }

    @FXML
    public void bQuitterClick(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void bAccueilClick(ActionEvent event) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_accueil.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Accueil");
        stage.setScene(new Scene(root));
        stage.show();
    }
@FXML
public void bEditClick(ActionEvent actionEvent) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_modif_etudiant.fxml"));
    Parent root = fxmlLoader.load();

    Stage stage = new Stage();
    stage.setTitle("Modification etudiant");
    stage.setScene(new Scene(root));
    stage.show();
}
    public void bdeletClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_delet_etudiant.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Suppression d'etudiant");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void bListEtudClick(ActionEvent event) {

        try {

            // Charger le fichier FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_liste_etudiant.fxml"));
            Parent root = fxmlLoader.load();


            // Obtenir le contrôleur de la nouvelle fenetre
            ListeEtudiantController listeEtudiantController = fxmlLoader.getController();

            // Créer une nouvelle fenêtre (Stage)
            Stage stage = new Stage();
            stage.setTitle("Liste etudiant");
            stage.setScene(new Scene(root));

            // Configurer la fenêtre en tant que modal
            stage.initModality(Modality.APPLICATION_MODAL);

            // Afficher la fenêtre et attendre qu'elle se ferme
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void bAjouterEtudClick(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_ajout_etudiant.fxml"));
            Parent root = fxmlLoader.load();


            // Obtenir le contrôleur de la nouvelle fenetre
            AjouterEtudiantController ajouterEtudiantController = fxmlLoader.getController();

            // Créer une nouvelle fenêtre (Stage)
            Stage stage = new Stage();
            stage.setTitle("ajouter un etudiant");
            stage.setScene(new Scene(root));

            // Configurer la fenêtre en tant que modal
            stage.initModality(Modality.APPLICATION_MODAL);

            // Afficher la fenêtre et attendre qu'elle se ferme
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void bListeSectionClick(ActionEvent event) {
        try {

            // Charger le fichier FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_liste_section.fxml"));
            Parent root = fxmlLoader.load();


            // Obtenir le contrôleur de la nouvelle fenetre
            ListeSectionController abc = fxmlLoader.getController();

            // Créer une nouvelle fenêtre (Stage)
            Stage stage = new Stage();
            stage.setTitle("Liste Section");
            stage.setScene(new Scene(root));

            // Configurer la fenêtre en tant que modal
            stage.initModality(Modality.APPLICATION_MODAL);

            // Afficher la fenêtre et attendre qu'elle se ferme
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void bAjouterSectionClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_ajout_section.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Ajouter une section");
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void bEditrSectionClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_modif_section.fxml"));
            Parent root = fxmlLoader.load();

            // Obtenir le contrôleur (si tu veux faire des actions dessus)
            ModifierSectionController controller = fxmlLoader.getController();

            Stage stage = new Stage();
            stage.setTitle("Modifier une section");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void bListeEtudiantsSectionClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_etudiant_section.fxml"));
            Parent root = fxmlLoader.load();

            // Obtenir le contrôleur (si tu veux faire des actions dessus)
            ListeEtudiantsSectionController controller = fxmlLoader.getController();

            Stage stage = new Stage();
            stage.setTitle("Etudiant dans une section");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void bListeCoursSectionClick(ActionEvent actionEvent) {

    }
    @FXML
    private void bListeCoursClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_liste_cours.fxml"));
            Parent root = fxmlLoader.load();

            // Obtenir le contrôleur (si tu veux faire des actions dessus)
            ListeCoursController controller = fxmlLoader.getController();

            Stage stage = new Stage();
            stage.setTitle("Liste des cours");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void bAjouterCoursClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_ajout_cours.fxml"));
            Parent root = fxmlLoader.load();

            // Obtenir le contrôleur
            AjouterCoursController controller = fxmlLoader.getController();

            Stage stage = new Stage();
            stage.setTitle("Ajouter des cours");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void bModifierCoursClick(ActionEvent event) {
        System.out.println("Modification d'un cours existant...");
        // Code pour choisir et modifier un cours
    }

    @FXML
    private void bSupprimerCoursClick(ActionEvent event) {
        System.out.println("Suppression d'un cours...");
        // Code pour sélectionner et supprimer un cours
    }

}
