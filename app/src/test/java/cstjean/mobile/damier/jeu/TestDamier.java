package cstjean.mobile.damier.jeu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
        damier.viderDamier();
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
    public void testSelectionnerPionCouleur() {
        Damier damier = Damier.getInstance();
        ArrayList<Integer> resultat = new ArrayList<>();
        damier.viderDamier();
        assertEquals(0,damier.getNombrePion());

        damier.ajouterPion(17, new Pion(CouleurPion.NOIR));
        resultat.add(21);
        resultat.add(22);
        assertEquals(resultat, damier.selectionnerPion(17));
        damier.enleverSelection();

        damier.viderDamier();
        resultat.clear();
        assertEquals(0, damier.getNombrePion());
        damier.ajouterPion(44, new Pion(CouleurPion.BLANC));
        resultat.add(39);
        resultat.add(40);
        assertEquals(resultat, damier.selectionnerPion(44));
        damier.enleverSelection();


    }

    @Test
    public void testSelectionnerPionLimiteJeu() {
        Damier damier = Damier.getInstance();
        ArrayList<Integer> resultat = new ArrayList<>();
        damier.viderDamier();

        damier.viderDamier();
        assertEquals(0, damier.getNombrePion());
        damier.ajouterPion(25, new Pion(CouleurPion.BLANC));
        resultat.add(20);
        assertEquals(resultat, damier.selectionnerPion(25));
        damier.enleverSelection();

        damier.viderDamier();
        resultat.clear();
        assertEquals(0, damier.getNombrePion());
        damier.ajouterPion(45, new Pion(CouleurPion.BLANC));
        resultat.add(40);
        assertEquals(resultat, damier.selectionnerPion(45));
        damier.enleverSelection();

        damier.viderDamier();
        resultat.clear();
        assertEquals(0, damier.getNombrePion());
        damier.ajouterPion(49, new Pion(CouleurPion.NOIR));
        assertEquals(resultat, damier.selectionnerPion(49));
        damier.enleverSelection();

        damier.viderDamier();
        resultat.clear();
        assertEquals(0, damier.getNombrePion());
        damier.ajouterPion(2, new Pion(CouleurPion.BLANC));
        assertEquals(resultat, damier.selectionnerPion(2));
        damier.enleverSelection();
    }

    @Test
    public void testSelectionnerPionPrise() {
        Damier damier = Damier.getInstance();
        ArrayList<Integer> resultat = new ArrayList<>();
        damier.viderDamier();

       damier.viderDamier();
       damier.ajouterPion(12,new Pion(CouleurPion.NOIR));
       damier.ajouterPion(17, new Pion(CouleurPion.BLANC));
       resultat.add(21);
       resultat.add(18);
       assertEquals(resultat, damier.selectionnerPion(12));
        damier.enleverSelection();
       resultat.clear();
        resultat.add(11);
        resultat.add(8);
        assertEquals(resultat, damier.selectionnerPion(17));
        damier.enleverSelection();

       damier.viderDamier();
       resultat.clear();
       damier.ajouterPion(32, new Pion(CouleurPion.BLANC));
       damier.ajouterPion(27, new Pion(CouleurPion.NOIR));
       resultat.add(21);
       resultat.add(28);
        assertEquals(resultat, damier.selectionnerPion(32));
        damier.enleverSelection();
        resultat.clear();
        resultat.add(31);
        resultat.add(38);
        assertEquals(resultat, damier.selectionnerPion(27));
        damier.enleverSelection();
    }

    @Test
    public void testSelectionnerPionPriseAuLimiteJeu() {
        Damier damier = Damier.getInstance();
        ArrayList<Integer> resultat = new ArrayList<>();
        damier.viderDamier();

        damier.viderDamier();
        damier.ajouterPion(3,new Pion(CouleurPion.NOIR));
        damier.ajouterPion(8, new Pion(CouleurPion.BLANC));
        resultat.add(12);
        resultat.add(9);
        assertEquals(resultat, damier.selectionnerPion(3));
        damier.enleverSelection();
        resultat.clear();
        resultat.add(2);
        assertEquals(resultat, damier.selectionnerPion(8));
        damier.enleverSelection();

        damier.viderDamier();
        resultat.clear();
        damier.ajouterPion(6, new Pion(CouleurPion.NOIR));
        damier.ajouterPion(11, new Pion(CouleurPion.BLANC));
        resultat.add(17);
        assertEquals(resultat, damier.selectionnerPion(6));
        damier.enleverSelection();
        resultat.clear();
        resultat.add(7);
        assertEquals(resultat, damier.selectionnerPion(11));
        damier.enleverSelection();

        damier.viderDamier();
        resultat.clear();
        damier.ajouterPion(15, new Pion(CouleurPion.NOIR));
        damier.ajouterPion(20, new Pion(CouleurPion.BLANC));
        resultat.add(24);
        assertEquals(resultat, damier.selectionnerPion(15));
        damier.enleverSelection();
        resultat.clear();
        resultat.add(14);
        assertEquals(resultat, damier.selectionnerPion(20));
        damier.enleverSelection();
    }

    @Test
    public void testSelectionPionArriere() {
        Damier damier = Damier.getInstance();
        ArrayList<Integer> resultat = new ArrayList<>();
        damier.viderDamier();

        damier.viderDamier();
        damier.ajouterPion(12,new Pion(CouleurPion.NOIR));
        damier.ajouterPion(8, new Pion(CouleurPion.BLANC));
        resultat.add(3);
        resultat.add(17);
        resultat.add(18);
        assertEquals(resultat, damier.selectionnerPion(12));
        damier.enleverSelection();
        resultat.clear();
        resultat.add(2);
        resultat.add(3);
        resultat.add(17);
        assertEquals(resultat, damier.selectionnerPion(8));
        damier.enleverSelection();
    }

    @Test
    public void testBougerPion() {
        Damier damier = Damier.getInstance();
        damier.viderDamier();
        damier.ajouterPion(18, new Pion(CouleurPion.NOIR));
        damier.selectionnerPion(18);
        damier.bougerPionSelectionner(22);
        assertNull(damier.getPion(18));
        assertNotNull(damier.getPion(22));
        assertEquals(CouleurPion.NOIR, damier.getPion(22).getCouleur());

        damier.viderDamier();
        damier.ajouterPion(18, new Pion(CouleurPion.BLANC));
        damier.selectionnerPion(18);
        damier.bougerPionSelectionner(22);
        assertNull(damier.getPion(18));
        assertNotNull(damier.getPion(22));
        assertEquals(CouleurPion.BLANC, damier.getPion(22).getCouleur());
    }

    @Test
    public void testPrisePion() {
        Damier damier = Damier.getInstance();
        damier.viderDamier();
        damier.ajouterPion(18,new Pion(CouleurPion.NOIR));
        damier.ajouterPion(22, new Pion(CouleurPion.BLANC));
        damier.selectionnerPion(18);
        damier.bougerPionSelectionner(27);
        assertNull(damier.getPion(18));
        assertNull(damier.getPion(22));
        assertNotNull(damier.getPion(27));
        assertEquals(CouleurPion.NOIR, damier.getPion(27).getCouleur());

        damier.viderDamier();
        damier.ajouterPion(23,new Pion(CouleurPion.NOIR));
        damier.ajouterPion(28, new Pion(CouleurPion.BLANC));
        damier.selectionnerPion(28);
        damier.bougerPionSelectionner(19);
        assertNull(damier.getPion(28));
        assertNull(damier.getPion(23));
        assertNotNull(damier.getPion(19));
        assertEquals(CouleurPion.BLANC, damier.getPion(19).getCouleur());

    }

    @Test
    public void testSelectionDame(){
        Damier damier = Damier.getInstance();
        ArrayList<Integer> resultat = new ArrayList<>();
        damier.viderDamier();
        damier.ajouterPion(28, new Dame(CouleurPion.NOIR));
        damier.ajouterPion(22, new Pion(CouleurPion.NOIR));
        damier.ajouterPion(44, new Pion(CouleurPion.BLANC));
        damier.ajouterPion(32, new Pion(CouleurPion.BLANC));
        damier.ajouterPion(37, new Pion(CouleurPion.BLANC));
        resultat.add(5);
        resultat.add(10);
        resultat.add(14);
        resultat.add(19);
        resultat.add(23);
        resultat.add(50);
        resultat.add(39);
        resultat.add(33);
        assertEquals(resultat, damier.selectionnerPion(28));
        damier.enleverSelection();
    }

    @Test
    public void testSelectionDameDeuxBlanc(){
        Damier damier = Damier.getInstance();
        ArrayList<Integer> resultat = new ArrayList<>();
        damier.viderDamier();
        damier.ajouterPion(28, new Dame(CouleurPion.NOIR));
        damier.ajouterPion(17, new Pion(CouleurPion.BLANC));
        damier.ajouterPion(23, new Pion(CouleurPion.BLANC));
        damier.ajouterPion(19, new Pion(CouleurPion.BLANC));
        damier.ajouterPion(32, new Pion(CouleurPion.BLANC));
        damier.ajouterPion(37, new Pion(CouleurPion.BLANC));
        damier.ajouterPion(39, new Pion(CouleurPion.BLANC));
        resultat.add(6);
        resultat.add(11);
        resultat.add(22);
        resultat.add(50);
        resultat.add(44);
        resultat.add(33);
        assertEquals(resultat, damier.selectionnerPion(28));
        damier.enleverSelection();
    }
}

