package fsiAdministration.DAO;

import fsiAdministration.BO.Section;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectionDAO extends DAO<Section> {

    @Override
    public boolean create(Section obj) {
        boolean controle = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FSI_GestionAdmin", "postgres", "zakariyya69");

            String sql = "INSERT INTO section(libelleSection) VALUES (?);";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, obj.getLibelleSection());

            int rowsInserer = statement.executeUpdate();
            controle = rowsInserer > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return controle;
    }

    @Override
    public boolean delete(Section obj) {
        boolean controle = false;
        try {
            // Charger le driver JDBC
            Class.forName("org.postgresql.Driver");

            // Connexion à la base
            Connection connect = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/FSI_GestionAdmin",
                    "postgres",
                    "zakariyya69"
            );

            // SQL pour supprimer une section
            String sql = "DELETE FROM section WHERE idSection = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setInt(1, obj.getIdSection());

            // Exécution de la requête
            int rowsDeleted = statement.executeUpdate();

            // Vérification si suppression effectuée
            if (rowsDeleted > 0) {
                controle = true;
            }

            // Fermer connexion
            connect.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return controle;
    }

    @Override
    public boolean update(Section obj) {
        boolean controle = false;
        try {
            // Charger le driver JDBC
            Class.forName("org.postgresql.Driver");

            // Connexion à la base
            Connection connect = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/FSI_GestionAdmin",
                    "postgres",
                    "zakariyya69"
            );

            // SQL pour mettre à jour une section
            String sql = "UPDATE section SET libelleSection = ? WHERE idSection = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, obj.getLibelleSection());
            statement.setInt(2, obj.getIdSection());

            // Exécution de la requête
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                controle = true;
            }

            // Fermer connexion
            connect.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return controle;
    }

    @Override
    public Section find(int id) {
        Section section = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connect = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/FSI_GestionAdmin",
                    "postgres",
                    "zakariyya69"
            );

            String sql = "SELECT * FROM section WHERE idSection = ?";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                section = new Section(
                        rs.getInt("idSection"),
                        rs.getString("libelleSection")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return section;
    }

    @Override
    public List<Section> findAll() {
        List<Section> sections = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FSI_GestionAdmin", "postgres", "zakariyya69");

            String sql = "SELECT * FROM section";
            Statement ps = connect.createStatement();
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                Section s = new Section(
                        rs.getInt("idSection"),
                        rs.getString("libelleSection")
                );
                sections.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sections;
    }
    public List<String> findEtudiantsBySection(int idSection) {
        List<String> etudiants = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connect = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/FSI_GestionAdmin",
                    "postgres",
                    "zakariyya69"
            );

            String sql = "SELECT nomEtudiant, prenomEtudiant FROM etudiant WHERE idSection = ?";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, idSection);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String etudiant = rs.getString("nomEtudiant") + " " + rs.getString("prenomEtudiant");
                etudiants.add(etudiant);
            }

            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return etudiants;
    }
}
