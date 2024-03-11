package DAO;

import Controller.AcceuilController;
import Job.Prospect;
import Exception.FormException;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Cette classe fournit des méthodes pour accéder et manipuler les données des prospects dans la base de données.
 */
public class ProspectDAO {

    /**
     * Méthode pour récupérer tous les prospects de la base de données.
     *
     * @return Une liste contenant tous les prospects de la base de données.
     * @throws SQLException si une erreur SQL survient.
     * @throws IOException si une erreur d'entrée/sortie survient.
     * @throws FormException si une exception de formulaire est rencontrée.
     */
    public static ArrayList<Prospect> findAll() throws SQLException, IOException, FormException {
        // Connexion à la base de données
        Connection con = ConnexionDAO.DAOConnexion();
        PreparedStatement pstmt = null;
        ArrayList<Prospect> prospects = new ArrayList<>();
        String query = "SELECT ID_PROSPECT, " +
                "RAISON_SOCIALE, " +
                "NUM_RUE, " +
                "NOM_RUE, " +
                "CODE_POSTAL, " +
                "VILLE, " +
                "TELEPHONE, " +
                "MAIL, " +
                "COMMENTAIRE, " +
                "DATE_PROSPECT, " +
                "INTERRET " +
                "FROM PROSPECT";
        try {
            pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Prospect prospect = new Prospect();
                prospect.setId(rs.getInt("ID_PROSPECT"));
                prospect.setRaisonSociale(rs.getString("RAISON_SOCIALE"));
                prospect.setNumRue(rs.getString("NUM_RUE"));
                prospect.setNomRue(rs.getString("NOM_RUE"));
                prospect.setCodePostale(rs.getString("CODE_POSTAL"));
                prospect.setVille(rs.getString("VILLE"));
                prospect.setTelephone(rs.getString("TELEPHONE"));
                prospect.setMail(rs.getString("MAIL"));
                prospect.setCommentaire(rs.getString("COMMENTAIRE"));
                prospect.setDateProspect(rs.getDate("DATE_PROSPECT").toLocalDate());
                prospect.setInterret(rs.getString("INTERRET"));
                prospects.add(prospect);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return prospects;
    }

    /**
     * Méthode pour trouver un prospect par son nom dans la base de données.
     *
     * @param name Le nom du prospect à rechercher.
     * @return Le prospect trouvé dans la base de données.
     * @throws SQLException si une erreur SQL survient.
     * @throws IOException si une erreur d'entrée/sortie survient.
     * @throws FormException si une exception de formulaire est rencontrée.
     */
    public static Prospect findByName(String name) throws SQLException, IOException, FormException {
        // Connexion à la base de données
        Connection con = ConnexionDAO.DAOConnexion();
        PreparedStatement stmt = null;
        String query = "SELECT ID_PROSPECT, " +
                "RAISON_SOCIALE, " +
                "NUM_RUE, " +
                "NOM_RUE, " +
                "CODE_POSTAL, " +
                "VILLE, " +
                "TELEPHONE, " +
                "MAIL, " +
                "COMMENTAIRE, " +
                "DATE_PROSPECT, " +
                "INTERRET " +
                "FROM PROSPECT WHERE RAISON_SOCIALE = ?";
        Prospect prospect = new Prospect();
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                prospect.setId(rs.getInt("ID_PROSPECT"));
                prospect.setRaisonSociale(rs.getString("RAISON_SOCIALE"));
                prospect.setNumRue(rs.getString("NUM_RUE"));
                prospect.setNomRue(rs.getString("NOM_RUE"));
                prospect.setCodePostale(rs.getString("CODE_POSTAL"));
                prospect.setVille(rs.getString("VILLE"));
                prospect.setTelephone(rs.getString("TELEPHONE"));
                prospect.setMail(rs.getString("MAIL"));
                prospect.setCommentaire(rs.getString("COMMENTAIRE"));
                prospect.setDateProspect(rs.getDate("DATE_PROSPECT").toLocalDate());
                prospect.setInterret(rs.getString("INTERRET"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return prospect;
    }

    /**
     * Méthode pour insérer un nouveau prospect dans la base de données.
     *
     * @param prospect Le prospect à insérer.
     * @throws SQLException si une erreur SQL survient.
     * @throws IOException si une erreur d'entrée/sortie survient.
     */
    public static void insertProspect(Prospect prospect) throws SQLException, IOException {
        // Connexion à la base de données
        Connection con = ConnexionDAO.DAOConnexion();
        PreparedStatement pstmt = null;
        String query = "INSERT INTO PROSPECT " +
                "(RAISON_SOCIALE, " +
                "NUM_RUE, " +
                "NOM_RUE, " +
                "CODE_POSTAL, " +
                "VILLE, " +
                "TELEPHONE, " +
                "MAIL, " +
                "COMMENTAIRE, " +
                "DATE_PROSPECT, " +
                "INTERRET) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, prospect.getRaisonSociale());
            pstmt.setString(2, prospect.getNumRue());
            pstmt.setString(3, prospect.getNomRue());
            pstmt.setString(4, prospect.getCodePostale());
            pstmt.setString(5, prospect.getVille());
            pstmt.setString(6, prospect.getTelephone());
            pstmt.setString(7, prospect.getMail());
            pstmt.setString(8, prospect.getCommentaire());
            pstmt.setDate(9, Date.valueOf(prospect.getDateProspect()));
            pstmt.setString(10, prospect.getInterret());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    /**
     * Méthode pour mettre à jour les informations d'un prospect dans la base de données.
     *
     * @param prospect Le prospect avec les informations mises à jour.
     * @param id L'identifiant du prospect à mettre à jour.
     * @throws SQLException si une erreur SQL survient.
     * @throws IOException si une erreur d'entrée/sortie survient.
     */
    public static void updateProspect(Prospect prospect, int id) throws SQLException, IOException {
        // Connexion à la base de données
        Connection con = ConnexionDAO.DAOConnexion();
        PreparedStatement pstmt = null;
        String query = "UPDATE PROSPECT SET " +
                "RAISON_SOCIALE=?, " +
                "NUM_RUE=?, " +
                "NOM_RUE=?, " +
                "CODE_POSTAL=?, " +
                "VILLE=?, " +
                "TELEPHONE=?, " +
                "MAIL=?, " +
                "COMMENTAIRE=?, " +
                "DATE_PROSPECT=?, " +
                "INTERRET=? " +
                "WHERE ID_PROSPECT=" + id;
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, prospect.getRaisonSociale());
            pstmt.setString(2, prospect.getNumRue());
            pstmt.setString(3, prospect.getNomRue());
            pstmt.setString(4, prospect.getCodePostale());
            pstmt.setString(5, prospect.getVille());
            pstmt.setString(6, prospect.getTelephone());
            pstmt.setString(7, prospect.getMail());
            pstmt.setString(8, prospect.getCommentaire());
            pstmt.setDate(9, Date.valueOf(prospect.getDateProspect()));
            pstmt.setString(10, prospect.getInterret());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    /**
     * Méthode pour supprimer un prospect de la base de données.
     *
     * @param id L'identifiant du prospect à supprimer.
     * @throws SQLException si une erreur SQL survient.
     * @throws IOException si une erreur d'entrée/sortie survient.
     */
    public static void deleteProspect(int id) throws SQLException, IOException {
        // Connexion à la base de données
        Connection con = ConnexionDAO.DAOConnexion();
        PreparedStatement pstmt = null;
        String query = "DELETE FROM PROSPECT WHERE ID_PROSPECT = ?";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
}
