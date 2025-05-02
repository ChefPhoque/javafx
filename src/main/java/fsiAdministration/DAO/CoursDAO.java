package fsiAdministration.DAO;

import fsiAdministration.BO.Cours;
import fsiAdministration.BO.Section;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursDAO extends DAO<Cours> {

    @Override
    public boolean create(Cours obj) {
        boolean controle = false;
        try {
            int id = lastId();
            id++;
            obj.setIdCours(id);
            Class.forName("org.postgresql.Driver");
            Connection connect = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/FSI_GestionAdmin", "postgres", "zakariyya69"
            );

            String sql = "INSERT INTO cours(idCours, libelleCours, descriptionCours, idSection) VALUES (?,?,?,?)";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setInt(1, obj.getIdCours());
            statement.setString(2, obj.getLibelleCours());
            statement.setString(3, obj.getDescriptionCours());
            statement.setInt(4, obj.getLibelleSection().getIdSection());

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

            ResultSet result = connect.createStatement().executeQuery("SELECT max(idCours) FROM cours");
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
    public boolean delete(Cours obj) {
        boolean controle = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connect = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/FSI_GestionAdmin", "postgres", "zakariyya69"
            );

            String sql = "DELETE FROM cours WHERE idCours = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setInt(1, obj.getIdCours());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                controle = true;
            }

            connect.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return controle;
    }

    @Override
    public boolean update(Cours obj) {
        boolean controle = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connect = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/FSI_GestionAdmin", "postgres", "zakariyya69"
            );

            String sql = "UPDATE cours SET libelleCours = ?, descriptionCours = ?, idSection = ? WHERE idCours = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, obj.getLibelleCours());
            statement.setString(2, obj.getDescriptionCours());
            statement.setInt(3, obj.getLibelleSection().getIdSection());
            statement.setInt(4, obj.getIdCours());

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
    public Cours find(int id) {
        Cours cours = null;
        SectionDAO sectionDAO = new SectionDAO();
        try {
            Class.forName("org.postgresql.Driver");
            Connection connect = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/FSI_GestionAdmin", "postgres", "zakariyya69"
            );

            String sql = "SELECT * FROM cours WHERE idCours = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                // Récupérer la section associée
                Section section = sectionDAO.find(rs.getInt("idSection"));

                cours = new Cours(
                        rs.getInt("idCours"),
                        rs.getString("libelleCours"),
                        rs.getString("descriptionCours"),
                        section
                );
            }

            connect.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cours;
    }

    @Override
    public List<Cours> findAll() {
        List<Cours> coursList = new ArrayList<>();
        SectionDAO sectionDAO = new SectionDAO();

        try {
            Class.forName("org.postgresql.Driver");
            Connection connect = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/FSI_GestionAdmin", "postgres", "zakariyya69"
            );

            String sql = "SELECT * FROM cours";
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Récupérer la section associée
                Section section = sectionDAO.find(rs.getInt("idSection"));

                Cours c = new Cours(
                        rs.getInt("idCours"),
                        rs.getString("libelleCours"),
                        rs.getString("descriptionCours"),
                        section
                );
                coursList.add(c);
            }

            connect.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return coursList;
    }
}
