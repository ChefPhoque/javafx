

import fsiAdministration.BO.Cours;
import fsiAdministration.BO.Section;
import fsiAdministration.DAO.CoursDAO;
import fsiAdministration.DAO.SectionDAO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CoursDAOTest {

    private final CoursDAO coursDAO = new CoursDAO();

    @Test
    public void testFind() {
        int idTest = 4;
        Cours cours = coursDAO.find(idTest);

        assertNotNull(cours, "Le cours ne doit pas être null");
        assertEquals(idTest, cours.getIdCours());
        assertNotNull(cours.getLibelleSection(), "La section doit être récupérée");
        assertNotNull(cours.getProfesseur(), "Le professeur doit être récupéré");
        System.out.println("Cours trouvé : " + cours.getLibelleCours());
    }

    @Test
    public void testFindAll() {
        List<Cours> coursList = coursDAO.findAll();

        assertFalse(coursList.isEmpty(), "La liste des cours ne doit pas être vide");
        for (Cours c : coursList) {
            assertNotNull(c.getLibelleSection(), "Chaque cours doit avoir une section");
        }
        System.out.println("Nombre de cours trouvés : " + coursList.size());
    }
    @Test
    public void testUpdate() {
        int idTest = 4;
        Cours cours = coursDAO.find(idTest);
        assertNotNull(cours, "Le cours doit exister pour être modifié");

        String ancienLibelle = cours.getLibelleCours();
        String nouveauLibelle = ancienLibelle + " - arabe";

        // Modifier le libellé du cours
        cours.setLibelleCours(nouveauLibelle);
        SectionDAO sectionDAO = new SectionDAO();
        // Modifier la section si possible
        Section nouvelleSection = sectionDAO.find(2); // supposons que cette section existe
        if (nouvelleSection != null) {
            cours.setLibelleSection(nouvelleSection);
        }

        boolean updateSuccess = coursDAO.update(cours);
        assertTrue(updateSuccess, "La mise à jour doit réussir");

        // Recharger le cours pour vérifier la modification
        Cours coursModifie = coursDAO.find(idTest);
        assertEquals(nouveauLibelle, coursModifie.getLibelleCours(), "Le libellé doit être mis à jour");

        System.out.println("Cours modifié avec succès : " + coursModifie.getLibelleCours());

        // Remettre la valeur d'origine pour éviter effets de bord dans les tests
        coursModifie.setLibelleCours(ancienLibelle);
        coursDAO.update(coursModifie);
    }

}
