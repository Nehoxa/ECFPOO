package Controller;

import DAO.ClientDAO;
import DAO.ProspectDAO;
import View.Show;

import Exception.FormException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Cette classe est responsable de la logique métier pour l'affichage des clients et des prospects.
 * Elle communique avec les classes DAO pour récupérer les données nécessaires à l'affichage.
 */
public class AffichageController {
    /**
     * Affiche une fenêtre pour visualiser les données des clients ou des prospects en fonction de la raison sociale.
     *
     * @param raisonSociale  La raison sociale des données à afficher (client ou prospect).
     * @throws SQLException   Une exception de base de données.
     * @throws IOException    Une exception d'entrée/sortie.
     * @throws FormException  Une exception de formulaire.
     */
    public static void show(String raisonSociale) throws SQLException, IOException, FormException {
        Show show = new Show(raisonSociale);
        show.setVisible(true);
    }

    /**
     * Récupère toutes les données des clients ou des prospects en fonction de la raison sociale.
     *
     * @param raisonSociale  La raison sociale des données à récupérer (client ou prospect).
     * @return Une liste contenant les données des clients ou des prospects.
     * @throws SQLException   Une exception de base de données.
     * @throws IOException    Une exception d'entrée/sortie.
     * @throws FormException  Une exception de formulaire.
     */
    public static ArrayList getData(String raisonSociale) throws SQLException, IOException, FormException {
        if (Objects.equals(raisonSociale, "client")) {
            return ClientDAO.findAll();
        } else {
            return ProspectDAO.findAll();
        }
    }
}
