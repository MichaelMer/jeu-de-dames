package cstjean.mobile.damier.jeu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test pour les pions.
 *
 * @author Xavier Gagnon
 * @author Michaël Mercier
 */
public class TestPion {

    /**
     * Test pour creer les pions.
     */
    @Test
    public void testCreer() {
        Pion pionBlanc = creerPion(CouleurPion.BLANC);
        assertEquals(CouleurPion.BLANC, pionBlanc.getCouleur());

        Pion pionNoir = creerPion(CouleurPion.NOIR);
        assertEquals(CouleurPion.NOIR, pionNoir.getCouleur());

        Pion pionSansCouleur = creerPion();
        assertEquals(CouleurPion.BLANC, pionSansCouleur.getCouleur());
    }

    /**
     * Test pour vérifier si la fonction estNoir fonctionne.
     */
    @Test
    public void testEstNoir() {
        Pion pionNoir = creerPion(CouleurPion.NOIR);
        assertTrue(pionNoir.estNoir());

        Pion pionBlanc = creerPion(CouleurPion.BLANC);
        assertFalse(pionBlanc.estNoir());

    }

    /**
     * Test pour regarder si les pions on la bonne représentation dans a console.
     */
    @Test
    public void testRepresentation() {
        Pion pionNoir = creerPion(CouleurPion.NOIR);
        assertEquals('P', pionNoir.getRepresentation());

        Pion pionBlanc = creerPion(CouleurPion.BLANC);
        assertEquals('p', pionBlanc.getRepresentation());
    }

    /**
     * Méthode qui crée un pion selon la couleur donné.
     *
     * @param couleur couleur de la dame
     * @return la dame
     */
    protected Pion creerPion(CouleurPion couleur) {
        return new Pion(couleur);
    }

    /**
     * Méthode qui crée un pion.
     *
     * @return la dame
     */
    protected Pion creerPion() {
        return new Pion();
    }
}
