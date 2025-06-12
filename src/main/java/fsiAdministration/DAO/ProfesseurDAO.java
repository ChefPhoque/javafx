package fsiAdministration.DAO;

import fsiAdministration.BO.Professeur;
import fsiAdministration.ConnexionPGSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfesseurDAO {

    public Professeur find(int id) {
        Professeur prof = null;
        try (Connection connect = ConnexionPGSQL.getConnection()) {
            String sql = "SELECT * FROM prof WHERE id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                prof = new Professeur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("courriel")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prof;
    }

    public List<Professeur> findAll() {
        List<Professeur> profs = new ArrayList<>();
        String sql = "SELECT * FROM prof";

        try (Connection connect = ConnexionPGSQL.getConnection();
             PreparedStatement statement = connect.prepareStatement(sql)) {

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Professeur prof = new Professeur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("courriel")
                );
                profs.add(prof);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profs;
    }
}
