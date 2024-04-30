package View;

import Controller.AcceuilController;
import Controller.AffichageController;
import Job.Client;
import Job.Prospect;
import Exception.FormException;
import Service.LogWritter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;

/**
 * Cette classe représente une fenêtre d'affichage des données des clients ou des prospects.
 */
public class Show extends JDialog {
    private JPanel contentPane;
    private JTable table1;
    private JButton ReturnButton;

    /**
     * Constructeur de la classe Show.
     * Affiche les données des clients ou des prospects dans un tableau.
     *
     * @param type Le type de données à afficher (client ou prospect).
     */
    public Show(String type) {
        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);
        setModal(true);
        this.setSize(1400, 800);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // Création du modèle de tableau
        DefaultTableModel model = new DefaultTableModel();
        table1 = new JTable(model);

        // Ajout des colonnes au modèle de tableau
        model.addColumn("Id");
        model.addColumn("Raison sociale");
        model.addColumn("Numéro de rue");
        model.addColumn("Nom de rue");
        model.addColumn("Code postal");
        model.addColumn("Ville");
        model.addColumn("Téléphone");
        model.addColumn("Email");
        model.addColumn("Commentaire");

        try {
            // Affichage des données en fonction du type de données à afficher
            if (Objects.equals(type, "client")) {
                model.addColumn("Chiffre d'affaire");
                model.addColumn("Nombre d'employées");

                ArrayList<Client> clients = AffichageController.getData(type);

                // Remplissage du tableau avec les données des clients
                for (Client client : clients) {
                    Object[] enregistrements = {
                            client.getId(),
                            client.getRaisonSociale(),
                            client.getNumRue(),
                            client.getNomRue(),
                            client.getCodePostale(),
                            client.getVille(),
                            client.getTelephone(),
                            client.getMail(),
                            client.getCommentaire(),
                            client.getChiffreAffaire(),
                            client.getNbEmployes(),
                    };
                    model.addRow(enregistrements);
                }
            } else {
                model.addColumn("Date de prospection");
                model.addColumn("Intérêt");

                ArrayList<Prospect> prospects = AffichageController.getData(type);

                // Remplissage du tableau avec les données des prospects
                for (Prospect prospect : prospects) {
                    Object[] enregistrements = {
                            prospect.getId(),
                            prospect.getRaisonSociale(),
                            prospect.getNumRue(),
                            prospect.getNomRue(),
                            prospect.getCodePostale(),
                            prospect.getVille(),
                            prospect.getTelephone(),
                            prospect.getMail(),
                            prospect.getCommentaire(),
                            prospect.getDateProspect(),
                            prospect.getInterret(),
                    };
                    model.addRow(enregistrements);
                }
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, sqle.getMessage());
            System.exit(1);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "L'application a rencontrée un problème va se fermer");
            LogWritter.LOGGER.log(Level.SEVERE, "Error : " + ex.getMessage());
            System.exit(1);
        }


        // Ajout du bouton de retour
        contentPane.add(ReturnButton, BorderLayout.SOUTH);

        // Gestion de l'action du bouton de retour
        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AcceuilController.returnAcceuil();
            }
        });

        // Ajout du tableau dans un JScrollPane et ajout du JScrollPane dans la fenêtre
        JScrollPane scrollPane = new JScrollPane(table1);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Méthode appelée lorsque l'utilisateur ferme la fenêtre.
     */
    private void onCancel() {
        dispose();
    }
}
