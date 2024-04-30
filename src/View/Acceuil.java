package View;

import Controller.AcceuilController;
import Controller.AffichageController;
import Controller.FormulaireController;
import Job.Client;
import Job.Prospect;
import Service.LogWritter;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import Exception.DaoException;

/**
 * Cette classe représente la fenêtre principale de l'application.
 */
public class Acceuil extends JDialog {
    private JPanel contentPane;
    private JRadioButton clientsRadioButton;
    private JRadioButton prospectsRadioButton;
    private JButton afficherButton;
    private JButton supprimerButton;
    private JButton creerButton;
    private JButton modifierButton;
    private JComboBox comboBox1;
    private JPanel HeaderPanel;
    private JPanel SelectPanel;
    private JPanel ActionPanel;
    private String selected;

    /**
     * Constructeur de la classe Acceuil.
     * Initialise la fenêtre et les éléments de l'interface utilisateur.
     */
    public Acceuil() {
        setContentPane(contentPane);
        this.setSize(600, 300);
        SelectPanel.setVisible(false);
        ActionPanel.setVisible(false);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(clientsRadioButton);
        buttonGroup.add(prospectsRadioButton);

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

        clientsRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    selected = "client";
                    comboBox1.removeAllItems();
                    fillComboBox();
                    managedButton();

                    SelectPanel.setVisible(true);
                    ActionPanel.setVisible(true);
                } catch (DaoException daoe) {
                    if (daoe.getLevel() == Level.SEVERE) {
                JOptionPane.showMessageDialog(null, "L'application a rencontrée un problème va se fermer");
                        System.exit(1);
                    }
                    JOptionPane.showMessageDialog(null, daoe.getMessage());
                } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "L'application a rencontrée un problème va se fermer");
                    System.exit(1);
                } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "L'application a rencontrée un problème va se fermer");
                    LogWritter.LOGGER.log(Level.SEVERE, "Error : " + ex.getMessage());
                    System.exit(1);
                }
            }
        });

        prospectsRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    selected = "prospect";
                    comboBox1.removeAllItems();
                    fillComboBox();
                    managedButton();

                    SelectPanel.setVisible(true);
                    ActionPanel.setVisible(true);
                } catch (DaoException daoe) {
                    if (daoe.getLevel() == Level.SEVERE) {
                JOptionPane.showMessageDialog(null, "L'application a rencontrée un problème va se fermer");
                        System.exit(1);
                    }
                    JOptionPane.showMessageDialog(null, daoe.getMessage());
                } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "L'application a rencontrée un problème va se fermer");
                    LogWritter.LOGGER.log(Level.SEVERE, "Error : " + ex.getMessage());
                    System.exit(1);
                }
            }
        });

        afficherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    AffichageController.show(selected);
                } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "L'application a rencontrée un problème va se fermer");
                    LogWritter.LOGGER.log(Level.SEVERE, "Error : " + ex.getMessage());
                    System.exit(1);
                }
            }
        });

        creerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                FormulaireController.showCreateForm(selected);
            }
        });

        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    FormulaireController.showUpdateForm(selected, comboBox1.getSelectedItem().toString());
                } catch (DaoException daoe) {
                    if (daoe.getLevel() == Level.SEVERE) {
                JOptionPane.showMessageDialog(null, "L'application a rencontrée un problème va se fermer");
                        System.exit(1);
                    }
                    JOptionPane.showMessageDialog(null, daoe.getMessage());
                } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "L'application a rencontrée un problème va se fermer");
                    LogWritter.LOGGER.log(Level.SEVERE, "Error : " + ex.getMessage());
                    System.exit(1);
                }
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    FormulaireController.deleteForm(selected, comboBox1.getSelectedItem().toString());
                } catch (DaoException daoe) {
                    if (daoe.getLevel() == Level.SEVERE) {
                JOptionPane.showMessageDialog(null, "L'application a rencontrée un problème va se fermer");
                        System.exit(1);
                    }
                    JOptionPane.showMessageDialog(null, daoe.getMessage());
                } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "L'application a rencontrée un problème va se fermer");
                    LogWritter.LOGGER.log(Level.SEVERE, "Error : " + ex.getMessage());
                    System.exit(1);
                }
            }
        });
    }

    /**
     * Méthode appelée lorsque l'utilisateur ferme la fenêtre.
     */
    private void onCancel() {
        dispose();
    }

    /**
     * Méthode pour remplir la JComboBox avec les éléments récupérés de la base de données.
     *
     * @throws Exception   Une exception
     */
    private void fillComboBox() throws Exception {
        if (Objects.equals(selected, "client")) {
            ArrayList<Client> clients = AcceuilController.addClientOnComboBox();
            for (Client client : clients) {
                comboBox1.addItem(client.getRaisonSociale());
            }
        } else if (Objects.equals(selected, "prospect")) {
            ArrayList<Prospect> prospects = AcceuilController.addProspectOnComboBox();
            for (Prospect prospect : prospects) {
                comboBox1.addItem(prospect.getRaisonSociale());
            }
        }
    }

    /**
     * Méthode pour afficher ou cacher les boutons de affichage, modification et suppression en fonction de la comboBox.
     */
    private void managedButton() {
        if (comboBox1.getItemCount() == 0) {
            afficherButton.setVisible(false);
            modifierButton.setVisible(false);
            supprimerButton.setVisible(false);
        } else {
            afficherButton.setVisible(true);
            modifierButton.setVisible(true);
            supprimerButton.setVisible(true);
        }
    }
}
