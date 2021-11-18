package cstjean.mobile.damier.jeu;

/**
 * Classe pour les pions du jeu de dame.
 *
 * @author Xavier Gagnon
 * @author Michaël Mercier
 */
public class Pion {

    /**
     * La couleur du pion.
     */
    private final CouleurPion couleur;

    /**
     * Constructeur.
     *
     * @param couleur la couleur du pion.
     */
    public Pion(CouleurPion couleur) {
        this.couleur = couleur;
    }

    /**
     * Constructeur si la couleur n'est pas spécifier.
     */
    public Pion() {
        this.couleur = CouleurPion.BLANC;
    }

    /**
     * Getter pour la couleur.
     *
     * @return la couleur du pion
     */
    public CouleurPion getCouleur() {
        return couleur;
    }

    /**
     * regarde si la couleur du pion est noir.
     *
     * @return true si le pion est noir
     */
    public Boolean estNoir() {
        return this.couleur.equals(CouleurPion.NOIR);
    }

    /**
     * Getter pour la représentation dans la console du pion.
     *
     * @return la repésentation du pion
     */
    public char getRepresentation() {
        if (this.estNoir()) {
            return 'P';
        }
        return 'p';
    }

    public TypePion getType () {
        return TypePion.PION;
    }
}