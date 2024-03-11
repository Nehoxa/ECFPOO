package Job;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Exception.FormException;

/**
 * Cette classe représente un prospect qui est une société.
 */
public class Prospect extends Societe {

    /**
     * Date de prospection du prospect.
     */
    private LocalDate dateProspect;

    /**
     * Intérêt du prospect.
     */
    private String interret;

    /**
     * Formatter pour la conversion de dates.
     */
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/yyyy");

    /**
     * Constructeur avec paramètres pour initialiser un prospect.
     *
     * @param raisonSociale Raison sociale du prospect.
     * @param numRue        Numéro de rue de l'adresse du prospect.
     * @param nomRue        Nom de la rue de l'adresse du prospect.
     * @param codePostale   Code postal de l'adresse du prospect.
     * @param ville         Ville de l'adresse du prospect.
     * @param telephone     Numéro de téléphone du prospect.
     * @param mail          Adresse email du prospect.
     * @param commentaire   Commentaire éventuel sur le prospect.
     * @param dateProspect  Date de prospection du prospect.
     * @param interret      Intérêt du prospect.
     * @throws FormException si une exception de formulaire est rencontrée.
     */
    public Prospect(
            String raisonSociale,
            String numRue,
            String nomRue,
            String codePostale,
            String ville,
            String telephone,
            String mail,
            String commentaire,
            LocalDate dateProspect,
            String interret
    ) throws FormException {
        super(raisonSociale, numRue, nomRue, codePostale, ville, telephone, mail, commentaire);
        setDateProspect(dateProspect);
        setInterret(interret);
    }

    /**
     * Constructeur par défaut.
     */
    public Prospect() {
    }

    /**
     * Getter pour la date de prospection du prospect.
     *
     * @return La date de prospection du prospect.
     */
    public LocalDate getDateProspect() {
        return dateProspect;
    }

    /**
     * Setter pour la date de prospection du prospect.
     *
     * @param dateProspect La date de prospection du prospect.
     * @throws FormException si une exception de formulaire est rencontrée.
     */
    public void setDateProspect(LocalDate dateProspect) throws FormException {
        if (dateProspect == null) {
            throw new FormException("Le champ 'Date' ne doit pas être vide");
        }
        this.dateProspect = dateProspect;
    }

    /**
     * Getter pour l'intérêt du prospect.
     *
     * @return L'intérêt du prospect.
     */
    public String getInterret() {
        return interret;
    }

    /**
     * Setter pour l'intérêt du prospect.
     *
     * @param interret L'intérêt du prospect.
     * @throws FormException si une exception de formulaire est rencontrée.
     */
    public void setInterret(String interret) throws FormException {
        if (interret == null || interret.isEmpty()) {
            throw new FormException("Le champ 'Intérêt du prospect' ne doit pas être vide");
        }
        this.interret = interret;
    }

    /**
     * Méthode toString pour obtenir une représentation sous forme de chaîne de caractères du prospect.
     *
     * @return Raison sociale du prospect.
     */
    public String toString() {
        return getRaisonSociale();
    }
}
