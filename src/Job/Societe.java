package Job;

import Exception.FormException;

import static Utility.RegexEmail.PATTERN_EMAIL;

/**
 * Cette classe abstraite représente une société.
 */
public abstract class Societe {

    /**
     * Identifiant de la société.
     */
    private int id;

    /**
     * Raison sociale de la société.
     */
    private String raisonSociale;

    /**
     * Numéro de rue de l'adresse de la société.
     */
    private String numRue;

    /**
     * Nom de la rue de l'adresse de la société.
     */
    private String nomRue;

    /**
     * Code postal de l'adresse de la société.
     */
    private String codePostale;

    /**
     * Ville de l'adresse de la société.
     */
    private String ville;

    /**
     * Numéro de téléphone de la société.
     */
    private String telephone;

    /**
     * Adresse email de la société.
     */
    private String mail;

    /**
     * Commentaire éventuel sur la société.
     */
    private String commentaire;

    /**
     * Constructeur avec paramètres pour initialiser une société.
     *
     * @param raisonSociale Raison sociale de la société.
     * @param numRue        Numéro de rue de l'adresse de la société.
     * @param nomRue        Nom de la rue de l'adresse de la société.
     * @param codePostale   Code postal de l'adresse de la société.
     * @param ville         Ville de l'adresse de la société.
     * @param telephone     Numéro de téléphone de la société.
     * @param mail          Adresse email de la société.
     * @param commentaire   Commentaire éventuel sur la société.
     * @throws FormException si une exception de formulaire est rencontrée.
     */
    public Societe(
            String raisonSociale,
            String numRue,
            String nomRue,
            String codePostale,
            String ville,
            String telephone,
            String mail,
            String commentaire
    ) throws FormException {
        setRaisonSociale(raisonSociale);
        setNumRue(numRue);
        setNomRue(nomRue);
        setCodePostale(codePostale);
        setVille(ville);
        setTelephone(telephone);
        setMail(mail);
        setCommentaire(commentaire);
    }

    /**
     * Constructeur par défaut.
     */
    public Societe() {
    }

    /**
     * Getter pour l'identifiant de la société.
     *
     * @return L'identifiant de la société.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter pour l'identifiant de la société.
     *
     * @param id L'identifiant de la société.
     * @throws FormException si une exception de formulaire est rencontrée.
     */
    public void setId(Integer id) throws FormException {
        if (id == null) {
            throw new FormException("l'id ne doit pas être null");
        }
        this.id = id;
    }

    /**
     * Getter pour la raison sociale de la société.
     *
     * @return La raison sociale de la société.
     */
    public String getRaisonSociale() {
        return raisonSociale;
    }

    /**
     * Setter pour la raison sociale de la société.
     *
     * @param raisonSociale La raison sociale de la société.
     * @throws FormException si une exception de formulaire est rencontrée.
     */
    public void setRaisonSociale(String raisonSociale) throws FormException {
        if (raisonSociale == null || raisonSociale.isEmpty()) {
            throw new FormException("Le champ 'Raison sociale' ne doit pas être vide");
        }
        this.raisonSociale = raisonSociale;
    }

    /**
     * Getter pour le numéro de rue de l'adresse de la société.
     *
     * @return Le numéro de rue de l'adresse de la société.
     */
    public String getNumRue() {
        return numRue;
    }

    /**
     * Setter pour le numéro de rue de l'adresse de la société.
     *
     * @param numRue Le numéro de rue de l'adresse de la société.
     * @throws FormException si une exception de formulaire est rencontrée.
     */
    public void setNumRue(String numRue) throws FormException {
        if (numRue == null || numRue.isEmpty()) {
            throw new FormException("Le champ 'Numéro de rue' ne doit pas être vide");
        } else if (numRue.length() > 10) {
            throw new FormException("Le champ 'Numéro de rue' doit être inférieur à 10 caractères");
        }
        this.numRue = numRue;
    }

    /**
     * Getter pour le nom de rue de l'adresse de la société.
     *
     * @return Le nom de rue de l'adresse de la société.
     */
    public String getNomRue() {
        return nomRue;
    }

    /**
     * Setter pour le nom de rue de l'adresse de la société.
     *
     * @param nomRue Le nom de rue de l'adresse de la société.
     * @throws FormException si une exception de formulaire est rencontrée.
     */
    public void setNomRue(String nomRue) throws FormException {
        if (nomRue == null || nomRue.isEmpty()) {
            throw new FormException("Le champ 'Nom de rue' ne doit pas être vide");
        }
        this.nomRue = nomRue;
    }

    /**
     * Getter pour le code postal de l'adresse de la société.
     *
     * @return Le code postal de l'adresse de la société.
     */
    public String getCodePostale() {
        return codePostale;
    }

    /**
     * Setter pour le code postal de l'adresse de la société.
     *
     * @param codePostale Le code postal de l'adresse de la société.
     * @throws FormException si une exception de formulaire est rencontrée.
     */
    public void setCodePostale(String codePostale) throws FormException {
        if (codePostale == null || codePostale.isEmpty()) {
            throw new FormException("Le champ 'Code postale' ne doit pas être vide");
        } else if (codePostale.length() > 10) {
            throw new FormException("Le champ 'Code postale' doit être inférieur à 10 caractères");
        }
        this.codePostale = codePostale;
    }

    /**
     * Getter pour la ville de l'adresse de la société.
     *
     * @return La ville de l'adresse de la société.
     */
    public String getVille() {
        return ville;
    }

    /**
     * Setter pour la ville de l'adresse de la société.
     *
     * @param ville La ville de l'adresse de la société.
     * @throws FormException si une exception de formulaire est rencontrée.
     */
    public void setVille(String ville) throws FormException {
        if (ville == null || ville.isEmpty()) {
            throw new FormException("Le champ 'Ville' ne doit pas être vide");
        }
        this.ville = ville;
    }

    /**
     * Getter pour le numéro de téléphone de la société.
     *
     * @return Le numéro de téléphone de la société.
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Setter pour le numéro de téléphone de la société.
     *
     * @param telephone Le numéro de téléphone de la société.
     * @throws FormException si une exception de formulaire est rencontrée.
     */
    public void setTelephone(String telephone) throws FormException {
        if (telephone == null || telephone.isEmpty()) {
            throw new FormException("Le champ 'Téléphone' ne doit pas être vide");
        } else if (telephone.length() < 10) {
            throw new FormException("Le champ 'Téléphone' doit être supérieur à 10 chiffres");
        } else if (telephone.length() > 13) {
            throw new FormException("Le champ 'Téléphone' doit être inférieur à 14 chiffres");
        }
        this.telephone = telephone;
    }

    /**
     * Getter pour l'adresse email de la société.
     *
     * @return L'adresse email de la société.
     */
    public String getMail() {
        return mail;
    }

    /**
     * Setter pour l'adresse email de la société.
     *
     * @param mail L'adresse email de la société.
     * @throws FormException si une exception de formulaire est rencontrée.
     */
    public void setMail(String mail) throws FormException {
        if (mail == null || mail.isEmpty() || !PATTERN_EMAIL.matcher(mail).matches()) {
            throw new FormException("L'adresse email n'est pas valide");
        }
        this.mail = mail;
    }

    /**
     * Getter pour le commentaire sur la société.
     *
     * @return Le commentaire sur la société.
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * Setter pour le commentaire sur la société.
     *
     * @param commentaire Le commentaire sur la société.
     * @throws FormException si une exception de formulaire est rencontrée.
     */
    public void setCommentaire(String commentaire) throws FormException {
        this.commentaire = commentaire;
    }

    public String toString() {
        return "id : " + getId() + "\n" +
                "Raison social : " + getRaisonSociale() + "\n" +
                "Numéro de rue : " + getNumRue() + "\n" +
                "Nom de rue : " + getNomRue() + "\n" +
                "Code Postale : " + getCodePostale() + "\n" +
                "Ville : " + getVille() + "\n" +
                "Téléphone : " + getTelephone() + "\n" +
                "Mail : " + getMail() + "\n";
    }
}
