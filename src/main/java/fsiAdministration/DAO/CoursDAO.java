package fsiAdministration.DAO;

import fsiAdministration.BO.Cours;
import fsiAdministration.BO.Professeur;
import fsiAdministration.BO.Section;
import fsiAdministration.ConnexionPGSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursDAO extends DAO<Cours> {

    @Override
    public boolean create(Cours obj) {
        String sql = "INSERT INTO cours(idCours, libelleCours, descriptionCours, idSection, idProf, volumeHoraire) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connect = ConnexionPGSQL.getConnection();
             PreparedStatement statement = connect.prepareStatement(sql)) {

            int id = lastId() + 1;
            obj.setIdCours(id);

            statement.setInt(1, obj.getIdCours());
            statement.setString(2, obj.getLibelleCours());
            statement.setString(3, obj.getDescriptionCours());
            statement.setInt(4, obj.getLibelleSection().getIdSection());
            statement.setInt(5, obj.getProfesseur().getId());  // Attention, méthode getId() sur Professeur
            statement.setInt(6, obj.getVolumeHoraire());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public int lastId() {
        String sql = "SELECT max(idCours) FROM cours";

        try (Connection connect = ConnexionPGSQL.getConnection();
             Statement stmt = connect.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            return rs.next() ? rs.getInt(1) : 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean delete(Cours obj) {
        String sql = "DELETE FROM cours WHERE idCours = ?";

        try (Connection connect = ConnexionPGSQL.getConnection();
             PreparedStatement statement = connect.prepareStatement(sql)) {

            statement.setInt(1, obj.getIdCours());
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Cours obj) {
        String sql = "UPDATE cours SET libelleCours = ?, descriptionCours = ?, idSection = ?, idProf = ?, volumeHoraire = ? WHERE idCours = ?";

        try (Connection connect = ConnexionPGSQL.getConnection();
             PreparedStatement statement = connect.prepareStatement(sql)) {

            statement.setString(1, obj.getLibelleCours());
            statement.setString(2, obj.getDescriptionCours());
            statement.setInt(3, obj.getLibelleSection().getIdSection());
            statement.setInt(4, obj.getProfesseur().getId());
            statement.setInt(5, obj.getVolumeHoraire());
            statement.setInt(6, obj.getIdCours());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Cours find(int id) {
        String sql = "SELECT * FROM cours WHERE idCours = ?";
        SectionDAO sectionDAO = new SectionDAO();
        ProfesseurDAO professeurDAO = new ProfesseurDAO();

        try (Connection connect = ConnexionPGSQL.getConnection();
             PreparedStatement statement = connect.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Section section = sectionDAO.find(rs.getInt("idSection"));
                    Professeur professeur = professeurDAO.find(rs.getInt("idProf"));

                    return new Cours(
                            rs.getInt("idCours"),
                            rs.getString("libelleCours"),
                            rs.getString("descriptionCours"),
                            section,
                            professeur,
                            rs.getInt("volumeHoraire")  // ajouté volumeHoraire ici
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cours> findAll() {
        List<Cours> coursList = new ArrayList<>();
        String sql = "SELECT * FROM cours";
        SectionDAO sectionDAO = new SectionDAO();
        ProfesseurDAO professeurDAO = new ProfesseurDAO();

        try (Connection connect = ConnexionPGSQL.getConnection();
             Statement stmt = connect.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Section section = sectionDAO.find(rs.getInt("idSection"));
                Professeur professeur = professeurDAO.find(rs.getInt("idProf"));
                Cours cours = new Cours(
                        rs.getInt("idCours"),
                        rs.getString("libelleCours"),
                        rs.getString("descriptionCours"),
                        section,
                        professeur,
                        rs.getInt("volumeHoraire")
                );
                coursList.add(cours);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coursList;
    }

}
