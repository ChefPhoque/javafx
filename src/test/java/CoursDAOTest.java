

import fsiAdministration.BO.Cours;
import fsiAdministration.DAO.CoursDAO;
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
}
