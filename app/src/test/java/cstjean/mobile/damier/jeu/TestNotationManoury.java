package cstjean.mobile.damier.jeu;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Test pour la notation manoury
 */
public class TestNotationManoury {
    /**
     * test pour recupere l'instance des notations
     */
    @Test
    public void testCreer() {
        NotationManoury notations = NotationManoury.getInstance();
        assertEquals(0,notations.getNbNotation());
        notations.ajouterNotation(true,8,12,false);
        assertEquals(1,notations.getNbNotation());

        NotationManoury notations2 = NotationManoury.getInstance();
        assertEquals(notations2.getNbNotation(),notations.getNbNotation());
        assertEquals(notations.getNotation(1),notations2.getNotation(1));

        notations.viderNotations();
    }

    /**
     * Test pour l'ajout d'une notation
     */
    @Test
    public void testAjouter(){
        NotationManoury notations = NotationManoury.getInstance();
        notations.ajouterNotation(true,8,12,false);
        assertEquals("(08-12)", notations.getNotation(1));
        notations.viderNotations();
        notations.ajouterNotation(false,33,28,true);
        assertEquals("33x28", notations.getNotation(1));
        notations.viderNotations();
    }

    @Test
    public void testRetourArriere() {
        NotationManoury notations = NotationManoury.getInstance();
        notations.ajouterNotation(true,8,12,false);
        notations.ajouterNotation(false,33,28,true);
        assertEquals(2, notations.getNbNotation());
        notations.retournerArriere(1);
        assertEquals(1,notations.getNbNotation());
        assertEquals("(08-12)", notations.getNotation(1));
        notations.viderNotations();
    }
}
