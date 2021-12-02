package cstjean.mobile.damier.jeu;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Test pour la notation manoury
 */
public class TestNotationManoury {
    /**
     * Methode appeler avant chaque test.
     */
    @Before
    public void setup() {
        NotationManoury.getInstance().viderNotations();
        Damier.getInstance().viderDamier();
    }

    /**
     * test pour recupere l'instance des notations
     */
    @Test
    public void testCreer() {
        NotationManoury notations = NotationManoury.getInstance();
        assertEquals(0, notations.getNbNotation());
        notations.ajouterNotation(true, 8, 12, false);
        assertEquals(1, notations.getNbNotation());

        NotationManoury notations2 = NotationManoury.getInstance();
        assertEquals(notations2.getNbNotation(), notations.getNbNotation());
        assertEquals(notations.getNotation(1), notations2.getNotation(1));

        notations.viderNotations();
    }

    /**
     * Test pour l'ajout d'une notation
     */
    @Test
    public void testAjouter() {
        NotationManoury notations = NotationManoury.getInstance();
        notations.ajouterNotation(true, 8, 12, false);
        assertEquals("(08-12)", notations.getNotation(1));
        notations.viderNotations();
        notations.ajouterNotation(false, 33, 28, true);
        assertEquals("33x28", notations.getNotation(1));
        notations.viderNotations();
    }

    /**
     * Test pour retourner en arriere a un etas specifier du jeu selon les notations.
     */
    @Test
    public void testRetourArriere() {
        NotationManoury notations = NotationManoury.getInstance();
        notations.ajouterNotation(true, 8, 12, false);
        notations.ajouterNotation(false, 33, 28, true);
        assertEquals(2, notations.getNbNotation());
        notations.retournerArriere(1);
        assertEquals(1, notations.getNbNotation());
        assertEquals("(08-12)", notations.getNotation(1));
        notations.viderNotations();
    }

    @Test
    public void testNotationDansJeuBlanc() {
        Damier damier = Damier.getInstance();

        damier.ajouterPion(33, new Dame(CouleurPion.BLANC));

        damier.selectionnerPion(33);
        damier.bougerPionSelectionner(17);

        assertEquals(1, NotationManoury.getInstance().getNbNotation());
        assertEquals("33-17", NotationManoury.getInstance().getNotation(1));
    }

    @Test
    public void testNotationDansJeuNoir() {
        Damier damier = Damier.getInstance();

        damier.ajouterPion(33, new Dame(CouleurPion.BLANC));
        damier.ajouterPion(19, new Dame(CouleurPion.NOIR));

        damier.selectionnerPion(33);
        damier.bougerPionSelectionner(50);

        damier.selectionnerPion(19);
        damier.bougerPionSelectionner(5);
        assertEquals(2, NotationManoury.getInstance().getNbNotation());
        assertEquals("33-50", NotationManoury.getInstance().getNotation(1));
        assertEquals("(19-05)", NotationManoury.getInstance().getNotation(2));
    }

    /**
     * Test pour la notation pour les prises.
     */
    @Test
    public void testNotationPriseDansJeu() {
        Damier damier = Damier.getInstance();

        damier.ajouterPion(33, new Dame(CouleurPion.BLANC));
        damier.ajouterPion(22, new Dame(CouleurPion.NOIR));

        damier.selectionnerPion(33);
        damier.bougerPionSelectionner(17);

        assertEquals(1, NotationManoury.getInstance().getNbNotation());
        assertEquals("33x17", NotationManoury.getInstance().getNotation(1));
    }
}
