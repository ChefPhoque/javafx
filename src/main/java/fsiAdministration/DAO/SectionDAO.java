package fsiAdministration.DAO;

import fsiAdministration.BO.Section;
import fsiAdministration.ConnexionPGSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectionDAO extends DAO<Section> {

    @Override
    public boolean create(Section obj) {
        boolean controle = false;
        String sql = "INSERT INTO section(libelleSection) VALUES (?);";

        try (Connection connect = ConnexionPGSQL.getConnection();
             PreparedStatement statement = connect.prepareStatement(sql)) {

            statement.setString(1, obj.getLibelleSection());
            controle = statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return controle;
    }

    @Override
    public boolean delete(Section obj) {
        boolean controle = false;
        String sql = "DELETE FROM section WHERE idSection = ?";

        try (Connection connect = ConnexionPGSQL.getConnection();
             PreparedStatement statement = connect.prepareStatement(sql)) {

            statement.setInt(1, obj.getIdSection());
            controle = statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return controle;
    }

    @Override
    public boolean update(Section obj) {
        boolean controle = false;
        String sql = "UPDATE section SET libelleSection = ? WHERE idSection = ?";

        try (Connection connect = ConnexionPGSQL.getConnection();
             PreparedStatement statement = connect.prepareStatement(sql)) {

            statement.setString(1, obj.getLibelleSection());
            statement.setInt(2, obj.getIdSection());
            controle = statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return controle;
    }

    @Override
    public Section find(int id) {
        Section section = null;
        String sql = "SELECT * FROM section WHERE idSection = ?";

        try (Connection connect = ConnexionPGSQL.getConnection();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    section = new Section(
                            rs.getInt("idSection"),
                            rs.getString("libelleSection")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return section;
    }

    @Override
    public List<Section> findAll() {
        List<Section> sections = new ArrayList<>();
        String sql = "SELECT * FROM section";

        try (Connection connect = ConnexionPGSQL.getConnection();
             Statement stmt = connect.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Section s = new Section(
                        rs.getInt("idSection"),
                        rs.getString("libelleSection")
                );
                sections.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sections;
    }

    public List<String> findEtudiantsBySection(int idSection) {
        List<String> etudiants = new ArrayList<>();
        String sql = "SELECT nomEtudiant, prenomEtudiant FROM etudiant WHERE idSection = ?";

        try (Connection connect = ConnexionPGSQL.getConnection();
             PreparedStatement stmt = connect.prepareStatement(sql)) {

            stmt.setInt(1, idSection);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String etudiant = rs.getString("nomEtudiant") + " " + rs.getString("prenomEtudiant");
                    etudiants.add(etudiant);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return etudiants;
    }

    public List<String> findCoursBySection(int idSection) {
        List<String> coursList = new ArrayList<>();
        String sql = "SELECT libelleCours FROM cours WHERE idSection = ?";

        try (Connection connect = ConnexionPGSQL.getConnection();
             PreparedStatement statement = connect.prepareStatement(sql)) {

            statement.setInt(1, idSection);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    coursList.add(rs.getString("libelleCours"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coursList;
    }
}
