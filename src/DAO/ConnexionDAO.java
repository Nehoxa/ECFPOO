package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static Service.LogWritter.LOGGER;

public class ConnexionDAO {

    static Connection connexion = null;

    private ConnexionDAO() throws SQLException, IOException {
        final Properties dataProperties = new Properties();
        File fichier = new File("dataProperties");
        FileInputStream input = new FileInputStream(fichier);
        dataProperties.load(input);

        connexion = DriverManager.getConnection(
                dataProperties.getProperty("url"),
                dataProperties.getProperty("login"),
                dataProperties.getProperty("password")
        );
    }

    public static Connection DAOConnexion() throws SQLException, IOException {
        if (connexion == null) {
            new ConnexionDAO();
        }
        return connexion;
    }

    public void shutdown() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if (connexion != null) {
                    try {
                        LOGGER.info("Database fermée");
                        connexion.close();

                    } catch (SQLException ex) {
                        LOGGER.severe(ex.getMessage());
                    }
                }
            }
        });
    }
}
