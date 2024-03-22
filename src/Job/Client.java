package Job;

import Exception.FormException;

import java.util.logging.Level;

/**
 * Cette classe représente un client qui est une société.
 */
public class Client extends Societe {

    /**
     * Chiffre d'affaires du client.
     */
    private Double chiffreAffaire;

    /**
     * Nombre d'employés du client.
     */
    private Integer nbEmployes;

    /**
     * Constructeur avec paramètres pour initialiser un client.
     *
     * @param raisonSociale Raison sociale du client.
     * @param numRue        Numéro de rue de l'adresse du client.
     * @param nomRue        Nom de la rue de l'adresse du client.
     * @param codePostale   Code postal de l'adresse du client.
     * @param ville         Ville de l'adresse du client.
     * @param telephone     Numéro de téléphone du client.
     * @param mail          Adresse email du client.
     * @param commentaire   Commentaire éventuel sur le client.
     * @param chiffreAffaire Chiffre d'affaires du client.
     * @param nbEmployes    Nombre d'employés du client.
     * @throws FormException si une exception de formulaire est rencontrée.
     */
    public Client(
            String raisonSociale,
            String numRue,
            String nomRue,
            String codePostale,
            String ville,
            String telephone,
            String mail,
            String commentaire,
            double chiffreAffaire,
            Integer nbEmployes
    ) throws FormException {
        super(raisonSociale, numRue, nomRue, codePostale, ville, telephone, mail, commentaire);
        setChiffreAffaire(chiffreAffaire);
        setNbEmployes(nbEmployes);
    }

    /**
     * Constructeur par défaut.
     */
    public Client() {
    }

    /**
     * Getter pour le chiffre d'affaires du client.
     *
     * @return Le chiffre d'affaires du client.
     */
    public double getChiffreAffaire() {
        return chiffreAffaire;
    }

    /**
     * Setter pour le chiffre d'affaires du client.
     *
     * @param chiffreAffaire Le chiffre d'affaires du client.
     * @throws FormException si une exception de formulaire est rencontrée.
     */
    public void setChiffreAffaire(Double chiffreAffaire) throws FormException {
        if (chiffreAffaire == null) {
            throw new FormException("Le champ 'Chiffre d'affaire' ne doit pas être vide");
        } else if (chiffreAffaire < 200) {
            throw new FormException("Le champ 'Chiffre d'affaire' doit être supérieur à 200");
        }
        this.chiffreAffaire = chiffreAffaire;
    }

    /**
     * Getter pour le nombre d'employés du client.
     *
     * @return Le nombre d'employés du client.
     */
    public int getNbEmployes() {
        return nbEmployes;
    }

    /**
     * Setter pour le nombre d'employés du client.
     *
     * @param nbEmployes Le nombre d'employés du client.
     * @throws FormException si une exception de formulaire est rencontrée.
     */
    public void setNbEmployes(Integer nbEmployes) throws FormException {
        if (nbEmployes == null) {
            throw new FormException("Le champ 'Nombre d'employés' ne doit pas être vide");
        } else if (nbEmployes < 0) {
            throw new FormException("Le champ 'Chiffre d'affaire' doit être supérieur à 0");
        }
        this.nbEmployes = nbEmployes;
    }

    /**
     * Méthode toString pour obtenir une représentation sous forme de chaîne de caractères du client.
     *
     * @return Raison sociale du client.
     */
    public String toString() {
        return super.toString() +
                "Chiffre d'affaires : " + getChiffreAffaire() + "\n" +
                "Nombre d'employés : " + getNbEmployes() + "\n";
    }
}
