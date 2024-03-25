package Controller;

import DAO.ClientDAO;
import DAO.ProspectDAO;
import Job.Client;
import Job.Prospect;
import View.Formulaire;

import Exception.FormException;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Cette classe est responsable de la logique métier pour la gestion des formulaires (création, mise à jour, suppression).
 * Elle communique avec les classes DAO pour interagir avec la base de données et les classes de vue pour l'affichage des formulaires.
 */
public class FormulaireController {
    /**
     * Affiche le formulaire de création pour le type spécifié.
     *
     * @param type  Le type de formulaire à afficher (client ou prospect).
     */
    public static void showCreateForm(String type) {
        String queryType = "create";
        Formulaire form = new Formulaire(type, queryType);
        form.setVisible(true);
    }

    /**
     * Crée un nouveau client avec les données fournies.
     *
     * @param raisonSociale     La raison sociale du client.
     * @param numRue            Le numéro de rue du client.
     * @param nomRue            Le nom de rue du client.
     * @param codePostale       Le code postal du client.
     * @param ville             La ville du client.
     * @param tel               Le numéro de téléphone du client.
     * @param mail              L'adresse e-mail du client.
     * @param commentaire       Les commentaires sur le client.
     * @param chiffreAffaire    Le chiffre d'affaires du client.
     * @param nbEmployes        Le nombre d'employés du client.
     * @throws Exception   Une exception.
     */
    public static void createClient(
            String raisonSociale,
            String numRue,
            String nomRue,
            String codePostale,
            String ville,
            String tel,
            String mail,
            String commentaire,
            double chiffreAffaire,
            int nbEmployes
    ) throws Exception {
        Client client = new Client(raisonSociale, numRue, nomRue, codePostale, ville, tel, mail, commentaire, chiffreAffaire, nbEmployes);
        ClientDAO.insertClient(client);
    }

    /**
     * Crée un nouveau prospect avec les données fournies.
     *
     * @param raisonSociale     La raison sociale du prospect.
     * @param numRue            Le numéro de rue du prospect.
     * @param nomRue            Le nom de rue du prospect.
     * @param codePostale       Le code postal du prospect.
     * @param ville             La ville du prospect.
     * @param tel               Le numéro de téléphone du prospect.
     * @param mail              L'adresse e-mail du prospect.
     * @param commentaire       Les commentaires sur le prospect.
     * @param date              La date de prospection du prospect.
     * @param interret          L'intérêt du prospect.
     * @throws Exception   Une exception.
     */
    public static void createProspect(
            String raisonSociale,
            String numRue,
            String nomRue,
            String codePostale,
            String ville,
            String tel,
            String mail,
            String commentaire,
            LocalDate date,
            String interret
    ) throws Exception {
        Prospect prospect = new Prospect(raisonSociale, numRue, nomRue, codePostale, ville, tel, mail, commentaire, date, interret);
        ProspectDAO.insertProspect(prospect);
    }

    /**
     * Affiche le formulaire de mise à jour pour le type et la raison sociale spécifiés.
     *
     * @param type          Le type de formulaire à afficher (client ou prospect).
     * @param raisonSociale La raison sociale du client ou du prospect à mettre à jour.
     * @throws Exception   Une exception.
     */
    public static void showUpdateForm(String type, String raisonSociale) throws Exception {
        String queryType = "update";
        if (Objects.equals(type, "client")) {
            Client client = ClientDAO.findByName(raisonSociale);
            Formulaire formulaire = new Formulaire(type, queryType);
            formulaire.fillFormClient(client);
            formulaire.setVisible(true);
        } else {
            Prospect prospect = ProspectDAO.findByName(raisonSociale);
            Formulaire formulaire = new Formulaire(type, queryType);
            formulaire.fillFormProspect(prospect);
            formulaire.setVisible(true);
        }
    }

    /**
     * Met à jour un client avec les données fournies.
     *
     * @param id                L'identifiant du client à mettre à jour.
     * @param raisonSociale     La raison sociale du client.
     * @param numRue            Le numéro de rue du client.
     * @param nomRue            Le nom de rue du client.
     * @param codePostale       Le code postal du client.
     * @param ville             La ville du client.
     * @param tel               Le numéro de téléphone du client.
     * @param mail              L'adresse e-mail du client.
     * @param commentaire       Les commentaires sur le client.
     * @param chiffreAffaire    Le chiffre d'affaires du client.
     * @param nbEmployes        Le nombre d'employés du client.
     * @throws Exception   Une exception.
     */
    public static void updateClient(
            int id,
            String raisonSociale,
            String numRue,
            String nomRue,
            String codePostale,
            String ville,
            String tel,
            String mail,
            String commentaire,
            double chiffreAffaire,
            int nbEmployes
    ) throws Exception {
        Client client = new Client(raisonSociale, numRue, nomRue, codePostale, ville, tel, mail, commentaire, chiffreAffaire, nbEmployes);
        ClientDAO.updateClient(client, id);
    }

    /**
     * Met à jour les informations d'un prospect dans la base de données.
     *
     * @param id               L'identifiant du prospect à mettre à jour.
     * @param raisonSociale    La raison sociale du prospect.
     * @param numRue           Le numéro de rue du prospect.
     * @param nomRue           Le nom de rue du prospect.
     * @param codePostale      Le code postal du prospect.
     * @param ville            La ville du prospect.
     * @param tel              Le numéro de téléphone du prospect.
     * @param mail             L'adresse e-mail du prospect.
     * @param commentaire      Le commentaire sur le prospect.
     * @param date             La date de prospection du prospect.
     * @param interret         L'intérêt du prospect.
     * @throws Exception   Une exception.
     */
    public static void updateProspect(
            int id,
            String raisonSociale,
            String numRue,
            String nomRue,
            String codePostale,
            String ville,
            String tel,
            String mail,
            String commentaire,
            LocalDate date,
            String interret
    ) throws Exception {
        Prospect prospect = new Prospect(raisonSociale, numRue, nomRue, codePostale, ville, tel, mail, commentaire, date, interret);
        ProspectDAO.updateProspect(prospect, id);
    }

    /**
     * Affiche le formulaire de suppression pour un type spécifié (client ou prospect).
     *
     * @param type           Le type de formulaire à afficher (client ou prospect).
     * @param raisonSociale  La raison sociale du client ou du prospect à supprimer.
     * @throws Exception   Une exception.
     */
    public static void deleteForm(String type, String raisonSociale) throws Exception {
        String queryType = "delete";
        if (Objects.equals(type, "client")) {
            Client client = ClientDAO.findByName(raisonSociale);
            Formulaire formulaire = new Formulaire(type, queryType);
            formulaire.fillFormClient(client);
            formulaire.setVisible(true);
        } else {
            Prospect prospect = ProspectDAO.findByName(raisonSociale);
            Formulaire formulaire = new Formulaire(type, queryType);
            formulaire.fillFormProspect(prospect);
            formulaire.setVisible(true);
        }
    }

    /**
     * Supprime un client ou un prospect de la base de données en fonction de son identifiant.
     *
     * @param type  Le type de données à supprimer (client ou prospect).
     * @param id    L'identifiant du client ou du prospect à supprimer.
     * @throws Exception   Une exception
     */
    public static void delete(String type, int id) throws Exception {
        if (Objects.equals(type, "client")) {
            ClientDAO.deleteClient(id);
        } else {
            ProspectDAO.deleteProspect(id);
        }
    }
}