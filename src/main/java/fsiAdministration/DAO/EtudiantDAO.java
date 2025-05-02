package fsiAdministration.DAO;

import fsiAdministration.BO.Etudiant;
import fsiAdministration.BO.Section;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDAO extends DAO<Etudiant> {

    @Override
    public boolean create(Etudiant obj) {
        boolean controle = false;
        try {
            int id = lastId();
            id++;
            obj.setIdEtudiant(id);
            Class.forName("org.postgresql.Driver");
            Connection connect = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/FSI_GestionAdmin", "postgres", "zakariyya69"
            );

            String sql = "INSERT INTO Etudiant(idEtudiant, nomEtudiant, prenomEtudiant, idSection, dateNaissEtudiant) VALUES (?,?,?,?,?)";
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

            connect.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return controle;
    }

    public int lastId() {
        int controle = 1;

        try {
            Class.forName("org.postgresql.Driver");
            Connection connect = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/FSI_GestionAdmin", "postgres", "zakariyya69"
            );

            ResultSet result = connect.createStatement().executeQuery("SELECT max(idEtudiant) FROM Etudiant");
            if (result.next()) {
                controle = result.getInt(1);
            }

            connect.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return controle;
    }

    @Override
    public boolean delete(Etudiant obj) {
        boolean controle = false;
        try {
            // Charger le driver JDBC
            Class.forName("org.postgresql.Driver");

            // Etablir la connexion à la base de données
            Connection connect = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/FSI_GestionAdmin", "postgres", "zakariyya69"
            );

            // SQL pour supprimer un étudiant en fonction de son ID
            String sql = "DELETE FROM Etudiant WHERE idEtudiant = ?";

            // Préparer la requête
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setInt(1, obj.getIdEtudiant());  // Remplacer ? par l'ID de l'étudiant

            // Exécuter la requête
            int rowsDeleted = statement.executeUpdate();

            // Si au moins une ligne est affectée, la suppression a réussi
            if (rowsDeleted > 0) {
                controle = true;
            }

            // Fermer la connexion
            connect.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Retourner true si la suppression a réussi, sinon false
        return controle;
    }


    @Override
    public boolean update(Etudiant obj) {
        boolean controle = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connect = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/FSI_GestionAdmin", "postgres", "zakariyya69"
            );

            String sql = "UPDATE Etudiant SET nomEtudiant = ?, prenomEtudiant = ?, idSection = ?, dateNaissEtudiant = ? WHERE idEtudiant = ?";
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

            connect.close();
        } catch (SQLException | ClassNotFoundException e) {
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

        try {
            Class.forName("org.postgresql.Driver");
            Connection connect = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/FSI_GestionAdmin", "postgres", "zakariyya69"
            );

            String sql = "SELECT * FROM etudiant";
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
}
