package cstjean.mobile.damier.jeu;

/**
 * Classe pour les pions de dames.
 *
 * @author Xavier Gagnon
 * @author Michaël Mercier
 */
public class Dame extends Pion {
    /**
     * Constructeur.
     *
     * @param couleur la couleur de la dame.
     */
    public Dame(CouleurPion couleur) {
        super(couleur);
    }

    /**
     * Constructeur si la couleur n'est pas spécifier.
     */
    public Dame() {
        super();
    }

    /**
     * Donne la représentation de dame.
     *
     * @return la représentation du la dame selon ça couleur.
     */
    @Override
    public char getRepresentation() {
        if (super.estNoir()) {
            return 'D';
        }
        return 'd';
    }

    /**
     * Getter pour le type des dames.
     *
     * @return Enum de TypePion pour les dames
     */
    @Override
    public TypePion getType() {
        return TypePion.DAME;
    }
}
