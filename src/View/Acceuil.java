package View;

import Controller.AcceuilController;
import Controller.AffichageController;
import Controller.FormulaireController;
import Job.Client;
import Job.Prospect;
import Exception.FormException;
import Service.LogWritter;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;

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

                    SelectPanel.setVisible(true);
                    ActionPanel.setVisible(true);
                } catch (SQLException se) {
                    JOptionPane.showMessageDialog(null, "Une erreur s'est produite");
                    LogWritter.LOGGER.log(Level.SEVERE, se.getMessage());
                } catch (IOException ie) {
                    JOptionPane.showMessageDialog(null, "Une erreur s'est produite.");
                    LogWritter.LOGGER.log(Level.SEVERE, "Error IO : " + ie.getMessage());
                } catch (FormException fe) {
                    JOptionPane.showMessageDialog(null, fe.getMessage());
                } catch (Exception ex) {
                    LogWritter.LOGGER.log(Level.SEVERE, "Error IO : " + ex.getMessage());
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

                    SelectPanel.setVisible(true);
                    ActionPanel.setVisible(true);
                } catch (SQLException se) {
                    JOptionPane.showMessageDialog(null, "Une erreur s'est produite");
                    LogWritter.LOGGER.log(Level.SEVERE, se.getMessage());
                } catch (IOException ie) {
                    JOptionPane.showMessageDialog(null, "Une erreur s'est produite.");
                    LogWritter.LOGGER.log(Level.SEVERE, "Error IO : " + ie.getMessage());
                } catch (FormException fe) {
                    JOptionPane.showMessageDialog(null, fe.getMessage());
                } catch (Exception ex) {
                    LogWritter.LOGGER.log(Level.SEVERE, "Error IO : " + ex.getMessage());
                }
            }
        });

        afficherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    AffichageController.show(selected);
                } catch (SQLException se) {
                    JOptionPane.showMessageDialog(null, "Une erreur s'est produite");
                    LogWritter.LOGGER.log(Level.SEVERE, se.getMessage());
                } catch (IOException ie) {
                    JOptionPane.showMessageDialog(null, "Une erreur s'est produite.");
                    LogWritter.LOGGER.log(Level.SEVERE, "Error IO : " + ie.getMessage());
                } catch (FormException fe) {
                    JOptionPane.showMessageDialog(null, fe.getMessage());
                } catch (Exception ex) {
                    LogWritter.LOGGER.log(Level.SEVERE, "Error IO : " + ex.getMessage());
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
                } catch (SQLException se) {
                    JOptionPane.showMessageDialog(null, "Une erreur s'est produite");
                    LogWritter.LOGGER.log(Level.SEVERE, se.getMessage());
                } catch (IOException ie) {
                    JOptionPane.showMessageDialog(null, "Une erreur s'est produite.");
                    LogWritter.LOGGER.log(Level.SEVERE, "Error IO : " + ie.getMessage());
                } catch (FormException fe) {
                    JOptionPane.showMessageDialog(null, fe.getMessage());
                } catch (Exception ex) {
                    LogWritter.LOGGER.log(Level.SEVERE, "Error IO : " + ex.getMessage());
                }
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    FormulaireController.deleteForm(selected, comboBox1.getSelectedItem().toString());
                } catch (SQLException se) {
                    JOptionPane.showMessageDialog(null, "Une erreur s'est produite");
                    LogWritter.LOGGER.log(Level.SEVERE, se.getMessage());
                } catch (IOException ie) {
                    JOptionPane.showMessageDialog(null, "Une erreur s'est produite.");
                    LogWritter.LOGGER.log(Level.SEVERE, "Error IO : " + ie.getMessage());
                } catch (FormException fe) {
                    JOptionPane.showMessageDialog(null, fe.getMessage());
                } catch (Exception ex) {
                    LogWritter.LOGGER.log(Level.SEVERE, "Error IO : " + ex.getMessage());
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
     * @throws SQLException si une erreur SQL survient.
     * @throws IOException si une erreur d'entrée/sortie survient.
     * @throws FormException si une exception de formulaire est rencontrée.
     */
    private void fillComboBox() throws SQLException, IOException, FormException{
        if (Objects.equals(selected, "client")) {
            ArrayList<Client> clients = AcceuilController.addClientOnComboBox();
            for (Client client : clients) {
                comboBox1.addItem(client.toString());
            }
        } else if (Objects.equals(selected, "prospect")) {
            ArrayList<Prospect> prospects = AcceuilController.addProspectOnComboBox();
            for (Prospect prospect : prospects) {
                comboBox1.addItem(prospect.toString());
            }
        }
    }
}
