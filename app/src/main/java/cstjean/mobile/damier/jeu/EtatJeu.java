package cstjean.mobile.damier.jeu;

/**
 * Enum pour les differente etat possible du jeu.
 *
 * @author Xavier Gagnon
 * @author Michaël Mercier
 */
public enum EtatJeu {
    /**
     * Quand les blanc on gagner.
     */
    VICTOIREBLANC,
    /**
     * Quand les noir on gagner.
     */
    VICTOIRENOIR,
    /**
     * Lorsque la partie n'est pas fini.
     */
    ENCOURS
}
