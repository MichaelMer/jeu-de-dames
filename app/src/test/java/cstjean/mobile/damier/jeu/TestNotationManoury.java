package cstjean.mobile.damier.jeu;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;

public class TestNotationManoury {
    @Test
    public void testRepresentationManoury() {
        NotationManoury notation = NotationManoury.getInstance();
        Pion pion = new Pion(CouleurPion.NOIR);
        notation.ajouterNotation(pion, 31, 27, true);

        ArrayList<String> notationArray = notation.getListeNotation();

        assertEquals("(31x27)", notationArray.get(0));
    }
}
