package View;

import Controller.AcceuilController;
import Controller.FormulaireController;
import Exception.DaoException;
import Exception.FormException;
import Job.Client;
import Job.Prospect;
import Service.LogWritter;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.logging.Level;

/**
 * Cette classe représente la fenêtre de formulaire pour la création/modification/suppression
 * des clients et des prospects.
 */
public class Formulaire extends JDialog {
    private JPanel contentPane;
    private JTextField textId;
    private JTextField textNumRue;
    private JTextField textCrossField1;
    private JTextField textMail;
    private JTextField textVille;
    private JTextField textCrossField2;
    private JButton revenirAcceuilButton;
    private JButton enregistrerButton;
    private JTextArea textCommentaire;
    private JTextField textNomRue;
    private JTextField textCodePostale;
    private JTextField textTelephone;
    private JLabel Label1;
    private JLabel Label2;
    private JLabel TitleLabel;
    private JTextField textRs;
    private JLabel LabelId;
    private JButton deleteButton;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Constructeur de la classe Formulaire.
     * Initialise la fenêtre de formulaire en fonction du type (client ou prospect) et de l'action
     * (création, mise à jour ou suppression).
     *
     * @param type      Le type du formulaire (client ou prospect).
     * @param queryType Le type de la requête (création, mise à jour ou suppression).
     */
    public Formulaire(String type, String queryType) {
        // Initialisation de la fenêtre
        setContentPane(contentPane);
        this.setSize(500, 800);
        textId.disable();
        deleteButton.setVisible(false);

        // Configuration de la fenêtre en fonction du type de requête
        if (Objects.equals(queryType, "delete")) {
            textRs.disable();
            textNumRue.disable();
            textNomRue.disable();
            textCodePostale.disable();
            textVille.disable();
            textTelephone.disable();
            textMail.disable();
            textCrossField1.disable();
            textCrossField2.disable();
            textCommentaire.disable();

            enregistrerButton.setVisible(false);
            deleteButton.setVisible(true);
        }

        // Gestion de la fermeture de la fenêtre
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // Gestion de la touche ESC
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // Gestion des événements sur les boutons
        revenirAcceuilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AcceuilController.returnAcceuil();
            }
        });

        // Configuration des champs et des libellés en fonction du type
        if (Objects.equals(type, "client")) {
            TitleLabel.setText("Client");
            Label1.setText("Chiffre d'affaire");
            Label2.setText("Nombre d'employés");
        } else {
            TitleLabel.setText("Prospect");
            Label1.setText("Date de prospection");
            Label2.setText("Interret");
        }

        // Gestion de l'action d'enregistrement des données
        enregistrerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Vérification des champs obligatoires
                    if (textRs.getText().isEmpty() ||
                            textNumRue.getText().isEmpty() ||
                            textNomRue.getText().isEmpty() ||
                            textCodePostale.getText().isEmpty() ||
                            textVille.getText().isEmpty() ||
                            textTelephone.getText().isEmpty() ||
                            textMail.getText().isEmpty() ||
                            textCrossField1.getText().isEmpty() ||
                            textCrossField2.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Vous devez remplir tous les champs");
                    }

                    // Récupération des données du formulaire
                    String raisonSocial = textRs.getText();
                    String numeroRue = textNumRue.getText();
                    String nomRue = textNomRue.getText();
                    String codePostal = textCodePostale.getText();
                    String ville = textVille.getText();
                    String tel = textTelephone.getText();
                    String mail = textMail.getText();
                    String commentaire = textCommentaire.getText();

                    // Création d'un client ou d'un prospect en fonction du type de formulaire
                    if (Objects.equals(queryType, "create")) {
                        if (Objects.equals(type, "client")) {
                            double chiffreAffaire = Double.parseDouble(textCrossField1.getText());
                            int nbEmployes = Integer.parseInt(textCrossField2.getText());

                            FormulaireController.createClient(raisonSocial, numeroRue, nomRue, codePostal, ville, tel, mail, commentaire, chiffreAffaire, nbEmployes);
                            JOptionPane.showMessageDialog(null, "Le client a été créé avec succès.");
                            dispose();
                            AcceuilController.returnAcceuil();
                        } else {
                            LocalDate date = LocalDate.parse(textCrossField1.getText(), formatter);
                            String interret = textCrossField2.getText();

                            FormulaireController.createProspect(raisonSocial, numeroRue, nomRue, codePostal, ville, tel, mail, commentaire, date, interret);
                            JOptionPane.showMessageDialog(null, "Le prospect a été créé avec succès.");
                            dispose();
                            AcceuilController.returnAcceuil();
                        }
                    } else if (Objects.equals(queryType, "update")) {
                        if (Objects.equals(type, "client")) {
                            double chiffreAffaire = Double.parseDouble(textCrossField1.getText());
                            int nbEmployes = Integer.parseInt(textCrossField2.getText());

                            FormulaireController.updateClient(Integer.parseInt(textId.getText()), raisonSocial, numeroRue, nomRue, codePostal, ville, tel, mail, commentaire, chiffreAffaire, nbEmployes);
                            JOptionPane.showMessageDialog(null, "Le client a été modifié avec succès");
                            dispose();
                            AcceuilController.returnAcceuil();
                        } else {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate date = LocalDate.parse(textCrossField1.getText(), formatter);
                            String interret = textCrossField2.getText();

                            FormulaireController.updateProspect(Integer.parseInt(textId.getText()), raisonSocial, numeroRue, nomRue, codePostal, ville, tel, mail, commentaire, date, interret);
                            JOptionPane.showMessageDialog(null, "Le prospect a été modifié avec succès");
                            dispose();
                            AcceuilController.returnAcceuil();
                        }
                    }
                } catch (DateTimeParseException dtpe) {
                    JOptionPane.showMessageDialog(null, "La date doit avoir le format 'jj/mm/aaaa'");
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Le champs 'Nombre d'employés' doit être un entier");
                } catch (FormException fe) {
                    JOptionPane.showMessageDialog(null, "Erreur de saisie : " + fe.getMessage());
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

        // Gestion de l'action de suppression
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    FormulaireController.delete(type, Integer.parseInt(textId.getText()));
                    if (Objects.equals(type, "client")) {
                        JOptionPane.showMessageDialog(null, "Le client a été supprimé avec succès.");
                        AcceuilController.returnAcceuil();
                    } else {
                        JOptionPane.showMessageDialog(null, "Le prospect a été supprimé avec succès.");
                        AcceuilController.returnAcceuil();
                    }
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
     * Remplit les champs du formulaire avec les données du client.
     *
     * @param client Les données du client à afficher dans le formulaire.
     */
    public void fillFormClient(Client client) {
        textId.setText(String.valueOf(client.getId()));
        textRs.setText(String.valueOf(client.getRaisonSociale()));
        textNumRue.setText(client.getNumRue());
        textNomRue.setText(client.getNomRue());
        textCodePostale.setText(client.getCodePostale());
        textVille.setText(client.getVille());
        textTelephone.setText(client.getTelephone());
        textMail.setText(client.getMail());
        textCommentaire.setText(client.getCommentaire());
        textCrossField1.setText(String.valueOf(client.getChiffreAffaire()));
        textCrossField2.setText(String.valueOf(client.getNbEmployes()));
    }

    /**
     * Remplit les champs du formulaire avec les données du prospect.
     *
     * @param prospect Les données du prospect à afficher dans le formulaire.
     */
    public void fillFormProspect(Prospect prospect) {
        textId.setText(String.valueOf(prospect.getId()));
        textRs.setText(String.valueOf(prospect.getRaisonSociale()));
        textNumRue.setText(prospect.getNumRue());
        textNomRue.setText(prospect.getNomRue());
        textCodePostale.setText(prospect.getCodePostale());
        textVille.setText(prospect.getVille());
        textTelephone.setText(prospect.getTelephone());
        textMail.setText(prospect.getMail());
        textCommentaire.setText(prospect.getCommentaire());
        textCrossField1.setText(prospect.getDateProspect().format(formatter));
        textCrossField2.setText(String.valueOf(prospect.getInterret()));
    }
}
