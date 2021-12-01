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

    /**
     * Test la selection selon la couleur.
     */
    @Test
    public void testSelectionnerPionCouleur() {
        Damier damier = Damier.getInstance();
        ArrayList<Integer> resultat = new ArrayList<>();
        damier.viderDamier();

        assertEquals(0, damier.getNombrePion());
        damier.ajouterPion(44, new Pion(CouleurPion.BLANC));
        resultat.add(39);
        resultat.add(40);
        damier.selectionnerPion(44);
        assertEquals(resultat, damier.getMouvementDispoPion());
        damier.enleverSelection();

        damier.viderDamier();
        resultat.clear();
        assertEquals(0,damier.getNombrePion());
        damier.ajouterPion(17, new Pion(CouleurPion.NOIR));
        resultat.add(21);
        resultat.add(22);
        damier.selectionnerPion(17);
        assertEquals(resultat, damier.getMouvementDispoPion());
        damier.enleverSelection();
        damier.viderDamier();
    }

    /**
     * Test la selection pour les pions sur les limites du jeu.
     */
    @Test
    public void testSelectionnerPionLimiteJeu() {
        Damier damier = Damier.getInstance();
        ArrayList<Integer> resultat = new ArrayList<>();
        damier.viderDamier();

        damier.viderDamier();
        assertEquals(0, damier.getNombrePion());
        damier.ajouterPion(25, new Pion(CouleurPion.BLANC));
        resultat.add(20);
        damier.selectionnerPion(25);
        assertEquals(resultat, damier.getMouvementDispoPion());
        damier.enleverSelection();

        damier.viderDamier();
        resultat.clear();
        assertEquals(0, damier.getNombrePion());
        damier.ajouterPion(45, new Pion(CouleurPion.BLANC));
        resultat.add(40);
        damier.selectionnerPion(45);
        assertEquals(resultat, damier.getMouvementDispoPion());
        damier.enleverSelection();

        damier.viderDamier();
        resultat.clear();
        assertEquals(0, damier.getNombrePion());
        damier.ajouterPion(49, new Pion(CouleurPion.NOIR));
        damier.selectionnerPion(49);
        assertEquals(resultat, damier.getMouvementDispoPion());
        damier.enleverSelection();

        damier.viderDamier();
        resultat.clear();
        assertEquals(0, damier.getNombrePion());
        damier.ajouterPion(2, new Pion(CouleurPion.BLANC));
        damier.selectionnerPion(2);
        assertEquals(resultat, damier.getMouvementDispoPion());
        damier.enleverSelection();
        damier.viderDamier();
    }

    /**
     * Test pour la selection d'un pion sur une prise disponible.
     */
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
        damier.selectionnerPion(12);
       assertEquals(resultat, damier.getMouvementDispoPion());
        damier.enleverSelection();
       resultat.clear();
        resultat.add(11);
        resultat.add(8);
        damier.selectionnerPion(17);
        assertEquals(resultat, damier.getMouvementDispoPion());
        damier.enleverSelection();

       damier.viderDamier();
       resultat.clear();
       damier.ajouterPion(32, new Pion(CouleurPion.BLANC));
       damier.ajouterPion(27, new Pion(CouleurPion.NOIR));
       resultat.add(21);
       resultat.add(28);
       damier.selectionnerPion(32);
        assertEquals(resultat, damier.getMouvementDispoPion());
        damier.enleverSelection();
        resultat.clear();
        resultat.add(31);
        resultat.add(38);
        damier.selectionnerPion(27);
        assertEquals(resultat, damier.getMouvementDispoPion());
        damier.enleverSelection();
        damier.viderDamier();
    }

    /**
     * Test selection pour les prise sur les limites du jeu.
     */
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
        damier.selectionnerPion(3);
        assertEquals(resultat, damier.getMouvementDispoPion());
        damier.enleverSelection();
        resultat.clear();
        resultat.add(2);
        damier.selectionnerPion(8);
        assertEquals(resultat, damier.getMouvementDispoPion());
        damier.enleverSelection();

        damier.viderDamier();
        resultat.clear();
        damier.ajouterPion(6, new Pion(CouleurPion.NOIR));
        damier.ajouterPion(11, new Pion(CouleurPion.BLANC));
        resultat.add(17);
        damier.selectionnerPion(6);
        assertEquals(resultat, damier.getMouvementDispoPion());
        damier.enleverSelection();
        resultat.clear();
        resultat.add(7);
        damier.selectionnerPion(11);
        assertEquals(resultat, damier.getMouvementDispoPion());
        damier.enleverSelection();

        damier.viderDamier();
        resultat.clear();
        damier.ajouterPion(15, new Pion(CouleurPion.NOIR));
        damier.ajouterPion(20, new Pion(CouleurPion.BLANC));
        resultat.add(24);
        damier.selectionnerPion(15);
        assertEquals(resultat, damier.getMouvementDispoPion());
        damier.enleverSelection();
        resultat.clear();
        resultat.add(14);
        damier.selectionnerPion(20);
        assertEquals(resultat, damier.getMouvementDispoPion());
        damier.enleverSelection();
        damier.viderDamier();
    }

    /**
     * Test pour les selection pour les pion qui on des prise par arriere.
     */
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
        damier.selectionnerPion(12);
        assertEquals(resultat, damier.getMouvementDispoPion());
        damier.enleverSelection();
        resultat.clear();
        resultat.add(2);
        resultat.add(3);
        resultat.add(17);
        damier.selectionnerPion(8);
        assertEquals(resultat, damier.getMouvementDispoPion());
        damier.enleverSelection();
        damier.viderDamier();
    }

    /**
     * Test pour bouger un pion.
     */
    @Test
    public void testBougerPion() {
        Damier damier = Damier.getInstance();
        damier.viderDamier();

        damier.ajouterPion(22, new Pion(CouleurPion.BLANC));
        damier.ajouterPion(44, new Pion(CouleurPion.NOIR));
        damier.selectionnerPion(22);
        damier.bougerPionSelectionner(18);
        assertNotNull(damier.getPion(18));
        assertNull(damier.getPion(22));

        damier.selectionnerPion(44);
        damier.bougerPionSelectionner(49);

        assertNotNull(damier.getPion(49));
        assertNull(damier.getPion(44));
        damier.viderDamier();
    }

    /**
     * Test pour les prise d'un pion
     */
    @Test
    public void testPrisePion() {
        Damier damier = Damier.getInstance();
       damier.viderDamier();
       //refaire test

    }

    /**
     * Test pour la selection d'une dame
     */
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
        damier.selectionnerPion(28);
        assertEquals(resultat, damier.getMouvementDispoPion());
        damier.enleverSelection();
        damier.viderDamier();
    }

    /**
     * Test pour la selection d'une dame si il y a 2 pion de suite.
     */
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
        damier.selectionnerPion(28);
        assertEquals(resultat, damier.getMouvementDispoPion());
        damier.enleverSelection();
        damier.viderDamier();
    }

    /**
     * Test pour les noms par defaut des joueurs.
     */
    @Test
    public void testGetNomDefaut() {
        Damier damier = Damier.getInstance();
        damier.viderDamier();
        assertEquals("Noir", damier.getNomNoir());
        assertEquals("Blanc", damier.getNomBlanc());
        damier.viderDamier();
    }

    /**
     * Test pour les setter pour changer les nom
     */
    @Test
    public void testSetNom() {
        Damier damier = Damier.getInstance();
        damier.viderDamier();
        damier.setNomBlanc("1");
        damier.setNomNoir("2");
        assertEquals("2", damier.getNomNoir());
        assertEquals("1", damier.getNomBlanc());
        damier.viderDamier();
        assertEquals("Noir", damier.getNomNoir());
        assertEquals("Blanc", damier.getNomBlanc());
        damier.viderDamier();
    }

    /**
     * Test pour les victoire pour les blancs.
     */
    @Test
    public void testVictoireBlanc() {
        Damier damier = Damier.getInstance();
        ArrayList<Integer> resultat = new ArrayList<>();
        damier.viderDamier();
        assertEquals(EtatJeu.ENCOURS, damier.getEtatJeu());
        damier.ajouterPion(18, new Pion(CouleurPion.BLANC));
        damier.ajouterPion(12, new Pion(CouleurPion.NOIR));
        damier.selectionnerPion(18);
        resultat.add(7);
        resultat.add(13);
        assertEquals(resultat,damier.getMouvementDispoPion());
        damier.bougerPionSelectionner(7);
        assertEquals(EtatJeu.VICTOIREBLANC, damier.getEtatJeu());
        damier.viderDamier();
    }

    /**
     * Test pour la victoire des noirs.
     */
    @Test
    public void testVictoireNoir() {
        Damier damier = Damier.getInstance();
        damier.viderDamier();
        assertEquals(EtatJeu.ENCOURS, damier.getEtatJeu());
        damier.ajouterPion(33, new Pion(CouleurPion.BLANC));
        damier.ajouterPion(11, new Pion(CouleurPion.NOIR));
        damier.selectionnerPion(33);
        damier.bougerPionSelectionner(28);
        damier.selectionnerPion(11);
        damier.bougerPionSelectionner(17);
        damier.selectionnerPion(28);
        damier.bougerPionSelectionner(22);
        damier.selectionnerPion(17);
        damier.bougerPionSelectionner(28);
        assertEquals(EtatJeu.VICTOIRENOIR, damier.getEtatJeu());
        damier.viderDamier();
    }
}

