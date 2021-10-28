package cstjean.mobile.damier.jeu;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test pour l'affichage du damier.
 */
public class TestAffichageDamier {
    /**
     * Test affichage pour le damier.
     */
    @Test
    public void testAffichageDamier() {
        String representationDamier =
                "-P-P-P-P-P" + Util.SAUT_LIGNE +
                        "P-P-P-P-P-" + Util.SAUT_LIGNE +
                        "-P-P-P-P-P" + Util.SAUT_LIGNE +
                        "P-P-P-P-P-" + Util.SAUT_LIGNE +
                        "----------" + Util.SAUT_LIGNE +
                        "----------" + Util.SAUT_LIGNE +
                        "-p-p-p-p-p" + Util.SAUT_LIGNE +
                        "p-p-p-p-p-" + Util.SAUT_LIGNE +
                        "-p-p-p-p-p" + Util.SAUT_LIGNE +
                        "p-p-p-p-p-" + Util.SAUT_LIGNE;

        Damier damier = new Damier();
        damier.initialiser();
        assertEquals(representationDamier, AffichageDamier.afficherDamier(damier));
    }
}
