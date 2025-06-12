package fsiAdministration.controllers;

import fsiAdministration.BO.Utilisateur;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuController {

    @FXML
    protected MenuItem bListeEtud, bAjouterEtud, bListeSection, bAjouterSection, bQuitter, bAccueil;
    @FXML
    private Label labelNomUtilisateur;

    private Utilisateur utilisateurConnecte;

    public void setUtilisateurConnecte(Utilisateur utilisateur) { //Permet de récuperer le login de l'utilisateur
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

        // Passage de l'utilisateur connecté au nouveau contrôleur
        AccueilController controller = fxmlLoader.getController();
        controller.setUtilisateurConnecte(utilisateurConnecte);

        Stage stage = new Stage();
        stage.setTitle("Accueil");
        stage.setScene(new Scene(root));
        stage.show();

        Stage currentStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        currentStage.close();
    }

    @FXML
    public void bEditClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_modif_etudiant.fxml"));
        Parent root = fxmlLoader.load();

        ModifierEtudiantController controller = fxmlLoader.getController();
        controller.setUtilisateurConnecte(utilisateurConnecte);

        Stage stage = new Stage();
        stage.setTitle("Modification etudiant");
        stage.setScene(new Scene(root));
        stage.show();
        Stage currentStage = (Stage) ((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow();
        currentStage.close();
    }

    @FXML
    public void bdeletClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_delet_etudiant.fxml"));
        Parent root = fxmlLoader.load();

        SupprimerEtudiantController controller = fxmlLoader.getController();
        controller.setUtilisateurConnecte(utilisateurConnecte);

        Stage stage = new Stage();
        stage.setTitle("Suppression d'etudiant");
        stage.setScene(new Scene(root));
        stage.show();

        Stage currentStage = (Stage) ((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow();
        currentStage.close();
    }

    @FXML
    public void bListEtudClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_liste_etudiant.fxml"));
            Parent root = fxmlLoader.load();

            ListeEtudiantController listeEtudiantController = fxmlLoader.getController();
            listeEtudiantController.setUtilisateurConnecte(utilisateurConnecte);

            Stage stage = new Stage();
            stage.setTitle("Liste etudiant");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            Stage currentStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void bAjouterEtudClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_ajout_etudiant.fxml"));
            Parent root = fxmlLoader.load();

            AjouterEtudiantController ajouterEtudiantController = fxmlLoader.getController();
            ajouterEtudiantController.setUtilisateurConnecte(utilisateurConnecte);

            Stage stage = new Stage();
            stage.setTitle("ajouter un etudiant");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            Stage currentStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void bListeSectionClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_liste_section.fxml"));
            Parent root = fxmlLoader.load();

            ListeSectionController abc = fxmlLoader.getController();
            abc.setUtilisateurConnecte(utilisateurConnecte);

            Stage stage = new Stage();
            stage.setTitle("Liste Section");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            Stage currentStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void bAjouterSectionClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_ajout_section.fxml"));
        Parent root = fxmlLoader.load();

        AjouterSectionController controller = fxmlLoader.getController();
        controller.setUtilisateurConnecte(utilisateurConnecte);

        Stage stage = new Stage();
        stage.setTitle("Ajouter une section");
        stage.setScene(new Scene(root));
        stage.show();

        Stage currentStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        currentStage.close();
    }

    @FXML
    public void bEditrSectionClick(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_modif_section.fxml"));
            Parent root = fxmlLoader.load();

            ModifierSectionController controller = fxmlLoader.getController();
            controller.setUtilisateurConnecte(utilisateurConnecte);

            Stage stage = new Stage();
            stage.setTitle("Modifier une section");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            Stage currentStage = (Stage) ((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void bListeEtudiantsSectionClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_etudiant_section.fxml"));
            Parent root = fxmlLoader.load();

            ListeEtudiantsSectionController controller = fxmlLoader.getController();
            controller.setUtilisateurConnecte(utilisateurConnecte);

            Stage stage = new Stage();
            stage.setTitle("Etudiant dans une section");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            Stage currentStage = (Stage) ((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void bListeCoursSectionClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_cours_section.fxml"));
            Parent root = fxmlLoader.load();

            CoursSectionController controller = fxmlLoader.getController();
            controller.setUtilisateurConnecte(utilisateurConnecte);

            Stage stage = new Stage();
            stage.setTitle("Cours par section");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            Stage currentStage = (Stage) ((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void bListeCoursClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_liste_cours.fxml"));
            Parent root = fxmlLoader.load();

            ListeCoursController controller = fxmlLoader.getController();
            controller.setUtilisateurConnecte(utilisateurConnecte);

            Stage stage = new Stage();
            stage.setTitle("Liste des cours");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            Stage currentStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void bAjouterCoursClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_ajout_cours.fxml"));
            Parent root = fxmlLoader.load();

            AjouterCoursController controller = fxmlLoader.getController();
            controller.setUtilisateurConnecte(utilisateurConnecte);

            Stage stage = new Stage();
            stage.setTitle("Ajouter des cours");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            Stage currentStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void bModifierCoursClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_modif_cours.fxml"));
            Parent root = fxmlLoader.load();

            ModifierCoursController controller = fxmlLoader.getController();
            controller.setUtilisateurConnecte(utilisateurConnecte);

            Stage stage = new Stage();
            stage.setTitle("Modifier des cours");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            Stage currentStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void bSupprimerCoursClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_delete_cours.fxml"));
            Parent root = fxmlLoader.load();

            SupprimerCoursController controller = fxmlLoader.getController();
            controller.setUtilisateurConnecte(utilisateurConnecte);

            Stage stage = new Stage();
            stage.setTitle("Supprimer des cours");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            Stage currentStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void bDeconnexionClick(ActionEvent event) {
        try {
            // Déconnexion
            utilisateurConnecte = null;

            // Permet de fermer les pages actives
            List<Window> openWindows = new ArrayList<>(Window.getWindows());
            for (Window window : openWindows) {
                if (window instanceof Stage) {
                    ((Stage) window).close();
                }
            }

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_connexion.fxml"));
            Parent root = fxmlLoader.load();

            Stage connexionStage = new Stage();
            connexionStage.setTitle("Connexion");
            connexionStage.setScene(new Scene(root));
            connexionStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de déconnexion");
            alert.setContentText("Impossible de revenir à la page de connexion.");
            alert.showAndWait();
        }
    }

    public void bSupprimerSectionClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_delete_section.fxml"));
            Parent root = fxmlLoader.load();

            SupprimerSectionController controller = fxmlLoader.getController();
            controller.setUtilisateurConnecte(utilisateurConnecte);

            Stage stage = new Stage();
            stage.setTitle("Supprimer des sections");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            Stage currentStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
