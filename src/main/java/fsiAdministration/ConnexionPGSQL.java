package fsiAdministration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionPGSQL {

    private static final String URL = "jdbc:postgresql://172.20.102.201:5432/P2025_FSI_G6";
    private static final String USER = "groupe1";
    private static final String PASSWORD = "2SIO_ORT";

    // ✅ Méthode de connexion
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}