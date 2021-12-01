package cstjean.mobile.damier.jeu;

/**
 * Enum pour les differente etat possible du jeu.
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
     * Quand ses une partie nulle
     */
    PARTIENULLE,
    /**
     * Lorsque la partie n'est pas fini.
     */
    ENCOURS
}
