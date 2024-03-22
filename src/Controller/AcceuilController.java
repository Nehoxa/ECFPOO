package Controller;

import DAO.ClientDAO;
import DAO.ProspectDAO;
import Job.Client;
import Job.Prospect;
import View.Acceuil;
import java.util.ArrayList;

/**
 * Cette classe est responsable de la logique métier de la vue Acceuil.
 * Elle communique avec les classes DAO pour récupérer et manipuler les données des clients et des prospects.
 */
public class AcceuilController {
    /**
     * Récupère tous les clients depuis la base de données et les renvoie sous forme d'une liste.
     *
     * @return Une liste contenant tous les clients.
     * @throws Exception   Une exception
     */
    public static ArrayList<Client> addClientOnComboBox() throws Exception {
        return ClientDAO.findAll();
    }

    /**
     * Récupère tous les prospects depuis la base de données et les renvoie sous forme d'une liste.
     *
     * @return Une liste contenant tous les prospects.
     * @throws Exception   Une exception
     */
    public static ArrayList<Prospect> addProspectOnComboBox() throws Exception {
        return ProspectDAO.findAll();
    }

    /**
     * Retourne à la fenêtre d'accueil principale.
     */
    public static void returnAcceuil() {
        Acceuil acc = new Acceuil();
        acc.setVisible(true);
    }
}
