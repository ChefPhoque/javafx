package fsiAdministration.DAO;

import fsiAdministration.BO.Utilisateur;
import fsiAdministration.ConnexionPGSQL;

import java.sql.*;
import java.util.List;

public class UtilisateurDAO extends DAO<Utilisateur> {

    @Override
    public boolean create(Utilisateur obj) {
        // Tu peux implémenter cette méthode plus tard si nécessaire
        return false;
    }

    @Override
    public boolean delete(Utilisateur obj) {
        // À compléter si besoin
        return false;
    }

    @Override
    public boolean update(Utilisateur obj) {
        // À compléter si besoin
        return false;
    }

    @Override
    public Utilisateur find(int id) {
        // À compléter si besoin
        return null;
    }

    @Override
    public List<Utilisateur> findAll() {
        return List.of();
    }

    public Utilisateur find(String login, String password) {
        Utilisateur user = null;

        try (Connection connect = ConnexionPGSQL.getConnection()) {
            String sql = "SELECT * FROM utilisateur WHERE loginUtilisateur = ? AND mdpUtilisateur = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                user = new Utilisateur(
                        result.getInt("idUtilisateur"),
                        result.getString("loginUtilisateur"),
                        result.getString("mdpUtilisateur")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user; // Peut être null si aucun utilisateur trouvé
    }
}