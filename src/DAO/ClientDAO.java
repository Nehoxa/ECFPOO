package DAO;

import Exception.DaoException;
import Exception.FormException;

import Job.Client;
import Service.LogWritter;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Cette classe fournit des méthodes pour accéder et manipuler les données des clients dans la base de données.
 */
public class ClientDAO {

    /**
     * Méthode pour récupérer tous les clients de la base de données.
     *
     * @return Une liste contenant tous les clients de la base de données.
     * @throws Exception   Une exception.
     */
    public static ArrayList<Client> findAll() throws Exception {
        // Connexion à la base de données
        Connection con = ConnexionDAO.DAOConnexion();
        Statement stmt = null;
        ArrayList<Client> clients = new ArrayList<>();
        String query = "select ID_CLIENT, " +
                "RAISON_SOCIALE, " +
                "NUM_RUE, " +
                "NOM_RUE, " +
                "CODE_POSTAL, " +
                "VILLE, " +
                "TELEPHONE, " +
                "MAIL, " +
                "COMMENTAIRE, " +
                "CHIFFRE_AFFAIRE, " +
                "NB_EMPLOYES " +
                "from CLIENT";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("ID_CLIENT"));
                client.setRaisonSociale(rs.getString("RAISON_SOCIALE"));
                client.setNumRue(rs.getString("NUM_RUE"));
                client.setNomRue(rs.getString("NOM_RUE"));
                client.setCodePostale(rs.getString("CODE_POSTAL"));
                client.setVille(rs.getString("VILLE"));
                client.setTelephone(rs.getString("TELEPHONE"));
                client.setMail(rs.getString("MAIL"));
                client.setCommentaire(rs.getString("COMMENTAIRE"));
                client.setChiffreAffaire(rs.getDouble("CHIFFRE_AFFAIRE"));
                client.setNbEmployes(rs.getInt("NB_EMPLOYES"));
                clients.add(client);
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getErrorCode());
            LogWritter.LOGGER.log(Level.SEVERE, "Problème de connexion " + sqle.getMessage());
            throw new DaoException("Un problème de connexion est survenu l'application va donc s'arrêter", Level.SEVERE);
        } catch (FormException fe) {
            throw new DaoException(fe.getMessage(), Level.WARNING);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return clients;
    }

    /**
     * Méthode pour trouver un client par son nom dans la base de données.
     *
     * @param name Le nom du client à rechercher.
     * @return Le client trouvé dans la base de données.
     * @throws Exception   Une exception.
     */
    public static Client findByName(String name) throws Exception {
        // Connexion à la base de données
        Connection con = ConnexionDAO.DAOConnexion();
        PreparedStatement stmt = null;
        String query = "select ID_CLIENT, " +
                "RAISON_SOCIALE, " +
                "NUM_RUE, " +
                "NOM_RUE, " +
                "CODE_POSTAL, " +
                "VILLE, " +
                "TELEPHONE, " +
                "MAIL, " +
                "COMMENTAIRE, " +
                "CHIFFRE_AFFAIRE, " +
                "NB_EMPLOYES " +
                "from CLIENT WHERE RAISON_SOCIALE = ?";
        Client client = new Client();
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                client.setId(rs.getInt("ID_CLIENT"));
                client.setRaisonSociale(rs.getString("RAISON_SOCIALE"));
                client.setNumRue(rs.getString("NUM_RUE"));
                client.setNomRue(rs.getString("NOM_RUE"));
                client.setCodePostale(rs.getString("CODE_POSTAL"));
                client.setVille(rs.getString("VILLE"));
                client.setTelephone(rs.getString("TELEPHONE"));
                client.setMail(rs.getString("MAIL"));
                client.setCommentaire(rs.getString("COMMENTAIRE"));
                client.setChiffreAffaire(rs.getDouble("CHIFFRE_AFFAIRE"));
                client.setNbEmployes(rs.getInt("NB_EMPLOYES"));
            }
        } catch (SQLException sqle) {
            LogWritter.LOGGER.log(Level.SEVERE, "Problème de connexion " + sqle.getMessage());
            throw new DaoException("Un problème de connexion est survenu l'application va donc s'arrêter", Level.SEVERE);
        } catch (FormException fe) {
            throw new DaoException(fe.getMessage(), Level.WARNING);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return client;
    }

    /**
     * Méthode pour insérer un nouveau client dans la base de données.
     *
     * @param client Le client à insérer.
     * @throws Exception   Une exception.
     */
    public static void insertClient(Client client) throws Exception {
        // Connexion à la base de données
        Connection con = ConnexionDAO.DAOConnexion();
        PreparedStatement pstmt = null;
        String query = "INSERT INTO CLIENT " +
                "(RAISON_SOCIALE, " +
                "NUM_RUE, " +
                "NOM_RUE, " +
                "CODE_POSTAL, " +
                "VILLE, " +
                "TELEPHONE, " +
                "MAIL, " +
                "COMMENTAIRE, " +
                "CHIFFRE_AFFAIRE, " +
                "NB_EMPLOYES) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, client.getRaisonSociale());
            pstmt.setString(2, client.getNumRue());
            pstmt.setString(3, client.getNomRue());
            pstmt.setString(4, client.getCodePostale());
            pstmt.setString(5, client.getVille());
            pstmt.setString(6, client.getTelephone());
            pstmt.setString(7, client.getMail());
            pstmt.setString(8, client.getCommentaire());
            pstmt.setDouble(9, client.getChiffreAffaire());
            pstmt.setInt(10, client.getNbEmployes());

            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            if (sqle.getErrorCode() == 1062) {
                throw new DaoException("La raison sociale existe déja", Level.WARNING);
            } else if (sqle.getErrorCode() == 1406) {
                throw new DaoException("Valeur trop longue", Level.WARNING);
            }
            LogWritter.LOGGER.log(Level.SEVERE, "Problème de connexion " + sqle.getMessage());
            throw new DaoException("Un problème de connexion est survenu l'application va donc s'arrêter", Level.SEVERE);
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    /**
     * Méthode pour mettre à jour les informations d'un client dans la base de données.
     *
     * @param client Le client avec les informations mises à jour.
     * @param id L'identifiant du client à mettre à jour.
     * @throws Exception   Une exception.
     */
    public static void updateClient(Client client, int id) throws Exception {
        // Connexion à la base de données
        Connection con = ConnexionDAO.DAOConnexion();
        PreparedStatement pstmt = null;
        String query = "UPDATE CLIENT " +
                "SET RAISON_SOCIALE=?, " +
                "NUM_RUE=?, " +
                "NOM_RUE=?, " +
                "CODE_POSTAL=?, " +
                "VILLE=?, " +
                "TELEPHONE=?, " +
                "MAIL=?, " +
                "COMMENTAIRE=?, " +
                "CHIFFRE_AFFAIRE=?, " +
                "NB_EMPLOYES=? " +
                "WHERE ID_CLIENT=" + id;
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, client.getRaisonSociale());
            pstmt.setString(2, client.getNumRue());
            pstmt.setString(3, client.getNomRue());
            pstmt.setString(4, client.getCodePostale());
            pstmt.setString(5, client.getVille());
            pstmt.setString(6, client.getTelephone());
            pstmt.setString(7, client.getMail());
            pstmt.setString(8, client.getCommentaire());
            pstmt.setDouble(9, client.getChiffreAffaire());
            pstmt.setInt(10, client.getNbEmployes());

            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            if (sqle.getErrorCode() == 1062) {
                throw new DaoException("La raison sociale existe déja", Level.WARNING);
            } else if (sqle.getErrorCode() == 1406) {
                throw new DaoException("Valeur trop longue", Level.WARNING);
            }
            LogWritter.LOGGER.log(Level.SEVERE, "Problème de connexion " + sqle.getMessage());
            throw new DaoException("Un problème de connexion est survenu l'application va donc s'arrêter", Level.SEVERE);
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    /**
     * Méthode pour supprimer un client de la base de données.
     *
     * @param id L'identifiant du client à supprimer.
     * @throws Exception   Une exception.
     */
    public static void deleteClient(int id) throws Exception {
        // Connexion à la base de données
        Connection con = ConnexionDAO.DAOConnexion();
        PreparedStatement pstmt = null;
        String query = "DELETE FROM CLIENT WHERE ID_CLIENT = ?";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException sqle) {
            LogWritter.LOGGER.log(Level.SEVERE, "Problème de connexion " + sqle.getMessage());
            throw new DaoException("Un problème de connexion est survenu l'application va donc s'arrêter", Level.SEVERE);
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
}
