package cstjean.mobile.damier.jeu;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test pour les dames.
 *
 * @author Xavier Gagnon
 * @author Michaël Mercier
 */
public class TestDame extends TestPion{

    /**
     * Méthode qui crée une dame selon la couleur donné.
     *
     * @param couleur couleur de la dame
     * @return la dame
     */
    @Override
    protected Pion creerPion(CouleurPion couleur) {
        return new Dame(couleur);
    }

    /**
     * Méthode qui crée une dame.
     *
     * @return la dame
     */
    @Override
    protected Pion creerPion() {
        return new Dame();
    }

    /**
     * Test la représentation des dames.
     */
    @Test
    @Override
    public void testRepresentation() {
        Pion dameNoir = creerPion(CouleurPion.NOIR);
        Assert.assertEquals('D', dameNoir.getRepresentation());

        Pion dameBlanche = creerPion();
        Assert.assertEquals('d', dameBlanche.getRepresentation());
    }

    @Test
    @Override
    public void testPionType() {
        Pion pion = creerPion();
        assertEquals(TypePion.DAME, pion.getType());
    }
}
