package cstjean.mobile.damier.jeu;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;

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
     * Test qui s'assure que les pion son ajouter dans la liste des pions.
     */
    @Test
    public void testInitialiserPions() {
        Damier damier = Damier.getInstance();
        damier.viderDamier();
        assertEquals(0, damier.getNombrePion());
        damier.initialiser();
        assertEquals(40, damier.getNombrePion());

        assertEquals(CouleurPion.NOIR, damier.getPion(1).getCouleur());
        assertEquals(CouleurPion.NOIR, damier.getPion(20).getCouleur());

        assertEquals(CouleurPion.BLANC, damier.getPion(31).getCouleur());
        assertEquals(CouleurPion.BLANC, damier.getPion(50).getCouleur());
        damier.viderDamier();
    }

    /**
     * Test qui vérifie la méthode qui donne le nombre de pions d'une certaine couleur.
     */
    @Test
    public void testNbPion() {
        Damier damier = Damier.getInstance();

        damier.initialiser();

        assertEquals(20, damier.getNbPionParCouleur(CouleurPion.BLANC));

        assertEquals(20, damier.getNbPionParCouleur(CouleurPion.NOIR));
        damier.viderDamier();
    }

    @Test
    public void testSelectionnerPion() {
        Damier damier = Damier.getInstance();

        damier.initialiser();
        ArrayList<Integer> resultat = new ArrayList<>();
        resultat.add(21);
        resultat.add(22);
        assertEquals(resultat, damier.selectionnerPion(17));

        damier.initialiser();
        resultat = new ArrayList<>();
        resultat.add(27);
        resultat.add(28);
        ArrayList<Integer> resultatDonner = damier.selectionnerPion(32);
        assertEquals(resultat, resultatDonner);
        damier.viderDamier();
    }
}
