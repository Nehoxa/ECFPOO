package Controller;

import DAO.ClientDAO;
import DAO.ProspectDAO;
import Job.Client;
import Job.Prospect;
import View.Acceuil;

import Exception.FormException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Cette classe est responsable de la logique métier de la vue Acceuil.
 * Elle communique avec les classes DAO pour récupérer et manipuler les données des clients et des prospects.
 */
public class AcceuilController {
    /**
     * Récupère tous les clients depuis la base de données et les renvoie sous forme d'une liste.
     *
     * @return Une liste contenant tous les clients.
     * @throws SQLException   Une exception de base de données.
     * @throws IOException    Une exception d'entrée/sortie.
     * @throws FormException  Une exception de formulaire.
     */
    public static ArrayList<Client> addClientOnComboBox() throws SQLException, IOException, FormException {
        return ClientDAO.findAll();
    }

    /**
     * Récupère tous les prospects depuis la base de données et les renvoie sous forme d'une liste.
     *
     * @return Une liste contenant tous les prospects.
     * @throws SQLException   Une exception de base de données.
     * @throws IOException    Une exception d'entrée/sortie.
     * @throws FormException  Une exception de formulaire.
     */
    public static ArrayList<Prospect> addProspectOnComboBox() throws SQLException, IOException, FormException {
        return ProspectDAO.findAll();
    }

    /**
     * Retourne à la fenêtre d'accueil principale.
     */
    public static void returnAcceuil() {
        Acceuil acc = new Acceuil();
        acc.setVisible(true);
    }

    /**
     * Supprime un client ou un prospect de la base de données en fonction du type spécifié.
     *
     * @param type           Le type de donnée à supprimer (client ou prospect).
     * @param raisonSociale  La raison sociale du client ou du prospect à supprimer.
     * @throws SQLException   Une exception de base de données.
     * @throws IOException    Une exception d'entrée/sortie.
     * @throws FormException  Une exception de formulaire.
     */
    public static void delete(String type, String raisonSociale) throws SQLException, IOException, FormException {
        if (Objects.equals(type, "client")) {
            Client client = ClientDAO.findByName(raisonSociale);
            ClientDAO.deleteClient(client.getId());
        } else {
            Prospect prospect = ProspectDAO.findByName(raisonSociale);
            ProspectDAO.deleteProspect(prospect.getId());
        }
    }
}
