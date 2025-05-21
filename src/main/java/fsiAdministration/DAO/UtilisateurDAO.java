package fsiAdministration.DAO;

import fsiAdministration.BO.Utilisateur;

import java.sql.*;
import java.util.List;

public class UtilisateurDAO extends DAO<Utilisateur>{


    @Override
    public boolean create(Utilisateur obj) {
        return false;
    }

    @Override
    public boolean delete(Utilisateur obj) {
        return false;
    }

    @Override
    public boolean update(Utilisateur obj) {
        return false;
    }

    @Override
    public Utilisateur find(int id) {
        return null;
    }

    @Override
    public List<Utilisateur> findAll() {
        return List.of();
    }

    public Utilisateur find(String login, String password) {
        Utilisateur user = new Utilisateur();

        try {
            Class.forName("org.postgresql.Driver");
            Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FSI_GestionAdmin","postgres","zakariyya69");
            if(connect == null){
                System.out.println("Connection non établie");
            }else{
                System.out.println("Connection established");
            }

            String sql = "SELECT * FROM utilisateur WHERE loginUtilisateur =? and mdpUtilisateur=?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet result = ps.executeQuery();
            if(result.next()) {
                user = new Utilisateur(
                        result.getInt("idutilisateur"),
                        result.getString("loginutilisateur"),
                        result.getString("mdputilisateur"));
            }

        } catch (SQLException e) {
            return null;
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }
}
