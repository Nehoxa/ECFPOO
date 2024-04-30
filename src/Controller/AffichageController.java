package Controller;

import DAO.ClientDAO;
import DAO.ProspectDAO;
import View.Show;

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
     * @param type  Le type des données à afficher (client ou prospect).
     */
    public static void show(String type) {
        Show show = new Show(type);
        show.setVisible(true);
    }

    /**
     * Récupère toutes les données des clients ou des prospects en fonction de la raison sociale.
     *
     * @param type  Lz type des données à récupérer (client ou prospect).
     * @return Une liste contenant les données des clients ou des prospects.
     * @throws Exception   Une exception.
     */
    public static ArrayList getData(String type) throws Exception {
        if (Objects.equals(type, "client")) {
            return ClientDAO.findAll();
        } else {
            return ProspectDAO.findAll();
        }
    }
}
