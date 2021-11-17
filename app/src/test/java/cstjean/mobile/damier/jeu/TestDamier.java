package cstjean.mobile.damier.jeu;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test pour les damier.
 *
 * @author Xavier Gagnon
 * @author Michaël Mercier
 */
public class TestDamier {
    /**
     * Test pour la création du damier.
     */
    @Test
    public void testCreer() {
        Damier damier = Damier.getInstance();
        assertEquals(0, damier.getNombrePion());
    }

    /**
     * Test pour l'ajout d'un pion.
     */
    @Test
    public void testAjoutPion() {
        Damier damier = Damier.getInstance();
        Pion pion = new Pion(CouleurPion.NOIR);

        damier.ajouterPion(38, pion);
        assertEquals(pion, damier.getPion(38));

        damier.ajouterPion(40, pion);
        assertEquals(pion, damier.getPion(40));

        assertEquals(2, damier.getNombrePion());
    }

    /**
     * Test qui s'assure que les pion son ajouter dans la liste des pions.
     */
    @Test
    public void testInitialiserPions() {
        Damier damier = Damier.getInstance();
        assertEquals(0, damier.getNombrePion());
        damier.initialiser();
        assertEquals(40, damier.getNombrePion());

        assertEquals(CouleurPion.NOIR, damier.getPion(1).getCouleur());
        assertEquals(CouleurPion.NOIR, damier.getPion(20).getCouleur());

        assertEquals(CouleurPion.BLANC, damier.getPion(31).getCouleur());
        assertEquals(CouleurPion.BLANC, damier.getPion(50).getCouleur());
    }

    /**
     * Test qui vérifie la méthode qui donne le nombre de pions d'une certaine couleur.
     */
    @Test
    public void testNbPion() {
        Damier damier = Damier.getInstance();

        damier.ajouterPion(1, new Pion());
        assertEquals(1, damier.getNbPionParCouleur(CouleurPion.BLANC));

        damier.ajouterPion(2, new Pion(CouleurPion.NOIR));
        assertEquals(1, damier.getNbPionParCouleur(CouleurPion.NOIR));

        damier.ajouterPion(1, new Pion());
        assertEquals(1, damier.getNbPionParCouleur(CouleurPion.BLANC));

        assertEquals(1, damier.getNbPionParCouleur(CouleurPion.NOIR));

        damier.initialiser();

        assertEquals(20, damier.getNbPionParCouleur(CouleurPion.BLANC));

        assertEquals(20, damier.getNbPionParCouleur(CouleurPion.NOIR));
    }
}
