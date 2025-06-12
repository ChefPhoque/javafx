package fsiAdministration.DAO;

import fsiAdministration.BO.Etudiant;
import fsiAdministration.BO.Section;
import fsiAdministration.ConnexionPGSQL;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDAO extends DAO<Etudiant> {

    public boolean create(Etudiant obj) {
        boolean controle = false;
        try {
            int id = lastId();
            id++;
            obj.setIdEtudiant(id);

            try (Connection connect = ConnexionPGSQL.getConnection()) {
                String sql = "INSERT INTO Etudiant(idEtudiant, nomEtudiant, prenomEtudiant, idSection, \"dateNaissEtudiant\") VALUES (?,?,?,?,?)";
                PreparedStatement statement = connect.prepareStatement(sql);
                statement.setInt(1, obj.getIdEtudiant());
                statement.setString(2, obj.getNomEtudiant());
                statement.setString(3, obj.getPrenomEtudiant());
                statement.setInt(4, obj.getSection().getIdSection());
                statement.setDate(5, Date.valueOf(obj.getDateNaissEtudiant()));

                int rowsInserer = statement.executeUpdate();
                if (rowsInserer > 0) {
                    controle = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return controle;
    }

    public int lastId() {
        int controle = 1;
        try (Connection connect = ConnexionPGSQL.getConnection();
             Statement stmt = connect.createStatement();
             ResultSet result = stmt.executeQuery("SELECT max(idEtudiant) FROM Etudiant")) {

            if (result.next()) {
                controle = result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return controle;
    }

    @Override
    public boolean delete(Etudiant obj) {
        boolean controle = false;
        try (Connection connect = ConnexionPGSQL.getConnection()) {
            String sql = "DELETE FROM Etudiant WHERE idEtudiant = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setInt(1, obj.getIdEtudiant());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                controle = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return controle;
    }

    @Override
    public boolean update(Etudiant obj) {
        boolean controle = false;
        try (Connection connect = ConnexionPGSQL.getConnection()) {
            String sql = "UPDATE Etudiant SET nomEtudiant = ?, prenomEtudiant = ?, idSection = ?, \"dateNaissEtudiant\" = ? WHERE idEtudiant = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, obj.getNomEtudiant());
            statement.setString(2, obj.getPrenomEtudiant());
            statement.setInt(3, obj.getSection().getIdSection());
            statement.setDate(4, Date.valueOf(obj.getDateNaissEtudiant()));
            statement.setInt(5, obj.getIdEtudiant());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                controle = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return controle;
    }

    @Override
    public Etudiant find(int id) {
        return null;
    }

    @Override
    public List<Etudiant> findAll() {
        List<Etudiant> mesEtud = new ArrayList<>();
        SectionDAO sectionDAO = new SectionDAO();
        String sql = "SELECT * FROM etudiant";
        try

                (Connection connect = ConnexionPGSQL.getConnection();
             PreparedStatement statement = connect.prepareStatement(sql)) {


            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int idSection = rs.getInt("idSection");
                Section section = sectionDAO.find(idSection);

                LocalDate dateNaiss = null;
                Date sqlDate = rs.getDate("dateNaissEtudiant");
                if (sqlDate != null) {
                    dateNaiss = sqlDate.toLocalDate();
                }

                Etudiant etud = new Etudiant(
                        rs.getInt("idEtudiant"),
                        rs.getString("nomEtudiant"),
                        rs.getString("prenomEtudiant"),
                        section,
                        dateNaiss
                );

                mesEtud.add(etud);
            }

            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mesEtud;
    }
    public int getNombreEtudiantsDansSection(int idSection) {
        int nombreEtudiants = 0;
        String sql = "SELECT COUNT(idEtudiant) AS total FROM Etudiant WHERE idSection = ?";

        try (Connection connect = ConnexionPGSQL.getConnection();
             PreparedStatement statement = connect.prepareStatement(sql)) {

            statement.setInt(1, idSection);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    nombreEtudiants = rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombreEtudiants;
    }
}