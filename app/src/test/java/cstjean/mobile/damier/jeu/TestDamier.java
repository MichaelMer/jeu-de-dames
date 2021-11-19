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
        ArrayList<Integer> resultat = new ArrayList<>();
        damier.viderDamier();
        assertEquals(0,damier.getNombrePion());

        damier.ajouterPion(17, new Pion(CouleurPion.NOIR));
        resultat.add(21);
        resultat.add(22);
        assertEquals(resultat, damier.selectionnerPion(17));

        damier.viderDamier();
        resultat.clear();
        assertEquals(0, damier.getNombrePion());
        damier.ajouterPion(1, new Pion(CouleurPion.NOIR));
        resultat.add(6);
        resultat.add(7);
        assertEquals(resultat, damier.selectionnerPion(1));

        damier.viderDamier();
        resultat.clear();
        assertEquals(0, damier.getNombrePion());
        damier.ajouterPion(44, new Pion(CouleurPion.BLANC));
        resultat.add(39);
        resultat.add(40);
        assertEquals(resultat, damier.selectionnerPion(44));

        damier.viderDamier();
        resultat.clear();
        assertEquals(0, damier.getNombrePion());
        damier.ajouterPion(25, new Pion(CouleurPion.BLANC));
        resultat.add(20);
        assertEquals(resultat, damier.selectionnerPion(25));

        damier.viderDamier();
        resultat.clear();
        assertEquals(0, damier.getNombrePion());
        damier.ajouterPion(45, new Pion(CouleurPion.BLANC));
        resultat.add(40);
        assertEquals(resultat, damier.selectionnerPion(45));

    }
}
