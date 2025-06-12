//package fsiAdministration.DAO;
//
//import fsiAdministration.BO.Cours;
//import fsiAdministration.BO.Section;
//import fsiAdministration.BO.Professeur;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class CoursDAOTest {
//
//    private CoursDAO coursDAO;
//    private Section dummySection;
//    private Professeur dummyProfesseur;
//
//    @BeforeEach
//    public void setUp() {
//        coursDAO = new CoursDAO();
//
//        // Création d’un dummy Section (id fictif pour test)
//        dummySection = new Section();
//        dummySection.setIdSection(1);
//        dummySection.setLibelleSection("Section Test");
//
//        // Création d’un dummy Professeur (id fictif pour test)
//        dummyProfesseur = new Professeur();
//        dummyProfesseur.setId(1);
//        dummyProfesseur.setNom("Prof Test");
//        dummyProfesseur.setPrenom("Jean");
//    }
//
//    @Test
//    public void testCreateCoursWithVolumeHoraire() {
//        Cours newCours = new Cours(100,"test","test",dummySection,dummyProfesseur,5);
//
//        boolean result = coursDAO.create(newCours);
//        assertTrue(result, "Le cours doit être créé avec succès");
//
//        // Optionnel : Vérifier que l'id est bien set (si ta méthode le fait)
//        assertTrue(newCours.getIdCours() > 0, "L'id du cours doit être supérieur à 0 après création");
//    }
//}
